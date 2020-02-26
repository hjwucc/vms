package com.wu.vms.service.model;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/18 16:49
 * @description：角色业务层模型
 */

public class RoleModel {
    private Integer id;
    private String name;
    private String namezh;

    public RoleModel() {
    }

    public RoleModel(Integer id, String name, String namezh) {
        this.id = id;
        this.name = name;
        this.namezh = namezh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamezh() {
        return namezh;
    }

    public void setNamezh(String namezh) {
        this.namezh = namezh;
    }
}
