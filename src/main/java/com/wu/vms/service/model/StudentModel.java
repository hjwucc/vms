package com.wu.vms.service.model;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/22 14:17
 * @description：学生业务层模型
 */

public class StudentModel {
    private Integer id;
    private String name;
    private String gender;
    private String major;
    private String politic;
    private String email;
    private String phone;
    private String describe;
    private Integer teacherid;
    //选择导师状态，未选:1   已选:2    已互选:3
    private Integer status;

    public StudentModel() {
    }

    public StudentModel(Integer id, String name, String gender, String major, String politic, String email, String phone, String describe, Integer teacherid,Integer status) {
        this.status = status;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.major = major;
        this.politic = politic;
        this.email = email;
        this.phone = phone;
        this.describe = describe;
        this.teacherid = teacherid;
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

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
