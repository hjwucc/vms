package com.wu.vms.response;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/19 17:40
 * @description：返回统一UI格式类
 */

public class CommonReturnType {
    //返回给UI的数据
    private Object data;
    //返回给UI的状态
    private String status;

    public static CommonReturnType create(Object result){
        //默认返回状态为success
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result,String status){
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setStatus(status);
        return type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
