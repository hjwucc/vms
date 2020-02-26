package com.wu.vms.dataobject;

public class TeacherDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.id
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.name
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.gender
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.email
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.phone
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.direction
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private String direction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column teacher.describe
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    private String describe;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.id
     *
     * @return the value of teacher.id
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.id
     *
     * @param id the value for teacher.id
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.name
     *
     * @return the value of teacher.name
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.name
     *
     * @param name the value for teacher.name
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.gender
     *
     * @return the value of teacher.gender
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.gender
     *
     * @param gender the value for teacher.gender
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.email
     *
     * @return the value of teacher.email
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.email
     *
     * @param email the value for teacher.email
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.phone
     *
     * @return the value of teacher.phone
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.phone
     *
     * @param phone the value for teacher.phone
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.direction
     *
     * @return the value of teacher.direction
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public String getDirection() {
        return direction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.direction
     *
     * @param direction the value for teacher.direction
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column teacher.describe
     *
     * @return the value of teacher.describe
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column teacher.describe
     *
     * @param describe the value for teacher.describe
     *
     * @mbg.generated Sat Nov 23 22:03:52 CST 2019
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}