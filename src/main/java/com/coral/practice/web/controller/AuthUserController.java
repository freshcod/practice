package com.coral.practice.web.controller;

import com.coral.practice.utils.JsonUtils;
import com.coral.practice.web.domain.AuthUser;
import com.coral.practice.web.dto.ReturnBean;
import com.coral.practice.web.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by qiuhai on 2016/7/16.
 */
@RestController
@RequestMapping(value = "/user/")
public class AuthUserController {

    private static final Logger log = LoggerFactory.getLogger(AuthUserController.class);

    @Autowired
    AuthUserService authUserService;

    /**
     * 根据id查询用户信息
     * @param id 用户id
     * @return {@link AuthUser}
     */
    @RequestMapping(value = "/auth_user/{id}",method = RequestMethod.GET)
    public AuthUser getUserById(@PathVariable Integer id){
        if(id==null) return null;
        log.info("id:"+id);
        return authUserService.getUserById(id);
    }

    /**
     * 新增用户
     * @param user 用户信息
     * @return {@link ReturnBean}
     */
    @RequestMapping(value = "/auth_user/",method = RequestMethod.POST)
    public ReturnBean insertUser(@RequestBody String user){
        log.info(user);
        AuthUser authUser = JsonUtils.jsonToBean(user,AuthUser.class);
        if(authUser == null) return new ReturnBean(0,"数据为空");
        log.info("user:"+authUser.toString());
        return authUserService.insertNewUser(authUser);
    }

    /**
     * 更新用户
     * @param authUser 用户信息
     * @return {@link ReturnBean}
     */
    @RequestMapping(value = "/auth_user/",method = RequestMethod.PUT)
    public ReturnBean updateUser(@RequestBody AuthUser authUser){
        log.info(authUser.toString());
        if(authUser == null) return new ReturnBean(0,"数据为空");
        log.info("user:"+authUser.toString());
        return authUserService.updateUser(authUser);
    }


    /**
     * 删除用户
     * @param id 用户id
     * @return {@link ReturnBean}
     */
    @RequestMapping(value = "/auth_user/{id}",method = RequestMethod.DELETE)
    public ReturnBean deleteUser(@PathVariable Integer id){
        if(id == null) return new ReturnBean(0,"数据为空");
        log.info("id:"+id);
        return authUserService.deleteUser(id);
    }
}
