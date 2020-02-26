package com.wu.vms.error;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/19 18:00
 * @description：统一异常处理
 */

public class CommonException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接收EmCommonError的传参用于构造业务异常
    public CommonException(CommonError commonError){
        super();
        this.commonError=commonError;
    }

    //接受自定义errMsg方式构造业务异常
    public CommonException(CommonError commonError,String errMsg){
        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return this.setErrMsg(errMsg);
    }
}
