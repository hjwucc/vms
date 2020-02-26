package com.wu.vms.error;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/19 17:42
 * @description：统一异常格式
 */

public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
