package com.wu.vms.service.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:39
 * @description：用户业务层模型
 */

public class UserModel implements Serializable, UserDetails {

    private Integer id;
    private Integer userid;
    private String password;
    private String roleName;
    private String roleNameZh;

    public UserModel() {
    }

    public UserModel(Integer id, Integer userid, String password, String roleName, String roleNameZh) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.roleName = roleName;
        this.roleNameZh = roleNameZh;
    }

    /**
    * @Description 校验与表单输入的用户名是否一致
    * @param
    * @Return java.lang.Integer
    */
    public String getUsername(){
        return userid.toString();
    }
    
    /**
    * @Description 判断当前账户是否未过期
    * @param
    * @Return boolean
    */
    public boolean isAccountNonExpired(){
        return true;
    }
    
    /**
    * @Description 判断当前账户是否未锁定
    * @param 
    * @Return boolean
    */
    public boolean isAccountNonLocked(){
        return true;
    }
    
    /**
    * @Description 判断当前账户密码是否未过期
    * @param 
    * @Return boolean
    */
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    /**
    * @Description 判断当前账户是否可用
    * @param
    * @Return boolean
    */
    public boolean isEnabled(){
        return true;
    }

    /**
    * @Description 赋予当前用户角色
    * @param
    * @Return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleName));
        return authorities;
    }

    /**
    * @Description 校验从表单输入的密码是否正确
    * @param
    * @Return java.lang.String
    */
    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }
}
