package com.coral.practice.web.service;

import com.coral.practice.web.domain.AuthUser;
import com.coral.practice.web.dto.ReturnBean;
import com.coral.practice.web.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiuhai on 2016/7/16.
 */
@Service
public class AuthUserService {

    @Autowired
    private AuthUserMapper authUserMapper;

    /**
     * 按ID取得用户
     * @param id 用户id
     * @return {@link AuthUser}
     */
    public AuthUser getUserById(Integer id){

        return authUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加用户
     * @param authUser 用户信息
     * @return {@link ReturnBean}
     */
    public ReturnBean insertNewUser(AuthUser authUser){
        int id = authUserMapper.insertSelective(authUser);
        if(id<=0) return new ReturnBean(0,"添加失败");
        return new ReturnBean(1,"");
    }


    /**
     * 更新用户
     * @param authUser 用户信息
     * @return {@link ReturnBean}
     */
    public ReturnBean updateUser(AuthUser authUser){
        int flag = authUserMapper.updateByPrimaryKeySelective(authUser);
        if(flag<=0) return new ReturnBean(0,"更新失败");
        return new ReturnBean(1,"");
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return {@link ReturnBean}
     */
    public ReturnBean deleteUser(Integer id){
        int flag = authUserMapper.deleteByPrimaryKey(id);
        if(flag<=0) return new ReturnBean(0,"删除失败");
        return new ReturnBean(1,"");
    }
}
