package com.coral.practice.framework;

import com.coral.practice.wheels.anno.MyAutoired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by qiuhai on 2016/9/23.
 */
@Component
public class ApplicationService {


    private static final Logger log = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationContext applicationContext;

    public void getAnnotionMap(){
        Map<String,Object> map = applicationContext.getBeansWithAnnotation(MyServiceAnno.class);
        log.info(map.toString());

    }
}
