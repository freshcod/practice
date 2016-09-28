package com.coral.practice.web.controller;

import com.coral.practice.framework.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qiuhai on 2016/9/23.
 */
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping("/test_ctx")
    public String testApplication(){
        applicationService.getAnnotionMap();
        return "ok";
    }

}
