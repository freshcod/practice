package com.coral.practice.framework;

import com.coral.practice.framework.exception.TaskServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by qiuhai on 2016/9/26.
 */
@Component
public class TaskJobService {

    private static final Logger log = LoggerFactory.getLogger(TaskJobService.class);

    private static final String methodName = "excuteTask";

    public void excuteTask(String serviceName) throws TaskServiceException {
        Map<String,String> taskMap = MyApplicationContext.taskMap;
        String className = taskMap.get(serviceName);
        if(className==null) throw new TaskServiceException("未找到服务");
        try{
            Class<?> clazz = Class.forName(className);
            Object object = clazz.newInstance();
            Method method = clazz.getDeclaredMethod(methodName,new Class[0]);
            method.invoke(object,new Object[0]);
        }catch (Exception e){
            throw new TaskServiceException("处理异常"+e.getMessage());
        }


    }
}
