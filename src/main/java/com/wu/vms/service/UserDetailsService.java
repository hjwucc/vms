package com.wu.vms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:55
 * @description：用户登录校验
 */
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名(即学号和工号)从数据库中查
        return userService.getUserByUserId(Integer.parseInt(s));
    }
}
