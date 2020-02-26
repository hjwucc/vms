package com.wu.vms.controller.viewobject;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:42
 * @description：用户视图层模型
 */

public class UserVO {
    private Integer id;
    private Integer userid;
    private String password;
    private String roleName;
    private String roleNameZh;

    public UserVO() {
    }

    public UserVO(Integer id, Integer userid, String password, String roleName, String roleNameZh) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.roleName = roleName;
        this.roleNameZh = roleNameZh;
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

    public String getPassword() {
        return password;
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





