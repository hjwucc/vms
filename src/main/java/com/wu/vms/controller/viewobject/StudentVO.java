package com.wu.vms.controller.viewobject;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/22 14:37
 * @description：学生视图层模型
 */

public class StudentVO {
    private Integer id;
    private String name;
    private String gender;
    private String major;
    private String politic;
    private String email;
    private String phone;
    private String describe;

    public StudentVO() {
    }

    public StudentVO(Integer id, String name, String gender, String major, String politic, String email, String phone, String describe) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.major = major;
        this.politic = politic;
        this.email = email;
        this.phone = phone;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPolitic() {
        return politic;
    }

    public void setPolitic(String politic) {
        this.politic = politic;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
