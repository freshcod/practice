package com.coral.practice.wheels.achieve;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuhai on 2016/6/23.
 */
public class EludeGenericValidateAchieve {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<String>();
        EludeGenericValidateAchieve eludeGenericValidateAchieve = new EludeGenericValidateAchieve();
        list = eludeGenericValidateAchieve.putInt2StrList(list,3);
        System.out.println(list);

    }

    public List<String> putInt2StrList(List<String> list, int num) throws InvocationTargetException, IllegalAccessException {
        Class clazz = list.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            if(method.getName().equals("add")){
                Class<?>[] cls = method.getParameterTypes();
                if(cls.length==1){
                    method.invoke(list,2);
                }
            }
        }

        return list;
    }
}
