package com.wu.vms.controller.viewobject;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/21 22:27
 * @description：老师视图层模型
 */

public class TeacherVO {
    private Integer id;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String direction;
    private String describe;

    public TeacherVO() {
    }

    public TeacherVO(Integer id, String name, String gender, String email, String phone, String direction, String describe) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.direction = direction;
        this.describe = describe;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
