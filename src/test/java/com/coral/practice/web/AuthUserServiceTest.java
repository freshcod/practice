package com.coral.practice.web;

import com.PracticeApplication;
import com.coral.practice.utils.DateUtils;
import com.coral.practice.utils.EncryptUtils;
import com.coral.practice.utils.RestUtils;
import com.coral.practice.web.domain.AuthUser;
import com.coral.practice.web.service.AuthUserService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;


import java.util.Date;

/**
 * Created by qiuhai on 2016/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeApplication.class)
@WebAppConfiguration
public class AuthUserServiceTest {

    @Autowired
    AuthUserService authUserService;



    @Test
    public void testGetAuthUser(){
        Integer id = 23;
        String url = "http://127.0.0.1:8090/user/auth_user/{id}";
        RestTemplate restTemplate = new RestTemplate();
        AuthUser authUser = restTemplate.getForObject(url,AuthUser.class,id);

        System.out.println(authUser);
    }

    @Test
    public void testPostAuthUser(){
        String url = "http://127.0.0.1:8090/user/auth_user/";
        AuthUser authUser = new AuthUser();
        authUser.setUsername("java");
        authUser.setIsActive(0);
        authUser.setIsStaff(1);
        authUser.setIsSuperuser(0);
        authUser.setEmail("java@shgt.com");
        authUser.setLastLogin(new Date());
        authUser.setDateJoined(new Date());
        String object = JSONObject.fromObject(authUser).toString();
        System.out.println(object);
        String result = RestUtils.postData(url,object,String.class);
        System.out.println(result);
    }


    @Test
    public void testPutAuthUser(){
        String url = "http://127.0.0.1:8090/user/auth_user/";
        AuthUser authUser = new AuthUser();
        authUser.setId(42);
        String password = EncryptUtils.encrypt("md5","123456");
        authUser.setUsername("java");
        authUser.setEmail("java@shgt.com");
        authUser.setPassword(password);
        String object = JSONObject.fromObject(authUser).toString();
        RestUtils.putData(url,object);

    }


    @Test
    public void testDeleteAuthUser(){
        Integer id = 42;
        String url = "http://127.0.0.1:8090/user/auth_user/{id}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url,id);
    }


}
