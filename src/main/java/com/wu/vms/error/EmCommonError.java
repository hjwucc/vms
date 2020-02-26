package com.wu.vms.error;

/**
* @Description 自定义错误码
* @author wuba
* @date 2019-11-19
*/
public enum EmCommonError implements CommonError {
    //10000开头的为通用错误类型
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOW_ERROR(10002,"未知错误"),

    //20000开头的为用户相关错误
    UPDATE_PASSWORD_ERROR(20001,"修改密码失败"),
    GET_MENU_ERROR(20002,"加载菜单失败"),
    SELECT_SUCCESS_ERROR(20003,"获取互选成功师生列表失败"),

    //30000开头为学生相关错误
    STUDENT_NOT_SELECT(30001,"您还未选择导师..."),
    STUDENT_NOT_CONFIRM_SELECT(30002,"您还未被导师确认..."),
    UPDATE_MY_TEACHER_ERROR(30003,"选择导师失败，请稍后再试..."),
    UPDATE_STUDENT_ERROR(30004,"更新信息失败，请稍后再试..."),
    SELECT_TEACHER_INFO_ERROR(30005,"未找到该导师信息..."),

    //50000开头为老师相关错误
    HAVE_NO_STUDENT_SELECT(50001,"还没有学生选择您为导师，请稍后再试..."),
    UPDATE_TEACHER_ERROR(50002,"更新信息失败，请稍后再试..."),
    SELECT_STUDENT_INFO_ERROR(50003,"未找到该学生信息..."),
    HAVE_NO_STUDENT_CONFIRM_SELECT(50004,"还没有学生互选成功，请稍后再试..."),
    SELECT_MY_STUDENT_ERROR(50005,"选择学生失败..."),

    //60000开头的为管理员相关错误
    ALL_STUDENT_MATCHED(60001,"所有学生互选成功"),
    ADD_SINGLE_STUDENT_ERROR(60002,"添加学生失败"),
    ADD_SINGLE_TEACHER_ERROR(60003,"添加导师失败")
    ;

    private int errCode;
    private String errMsg;

    EmCommonError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
