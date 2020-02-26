package com.wu.vms.handler;

import com.wu.vms.error.CommonException;
import com.wu.vms.error.EmCommonError;
import com.wu.vms.response.CommonReturnType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/19 18:10
 * @description：全局异常处理器,处理业务异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonReturnType doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception ex) {
        ex.printStackTrace();
        Map<String,Object> responseData = new HashMap<>();
        if(ex instanceof CommonException){
            CommonException commonException = (CommonException)ex;
            System.out.println(commonException.getErrCode() + commonException.getErrMsg());
            responseData.put("errCode",commonException.getErrCode());
            responseData.put("errMsg",commonException.getErrMsg());
        }else if(ex instanceof ServletRequestBindingException){
            responseData.put("errCode", EmCommonError.UNKNOW_ERROR.getErrCode());
            responseData.put("errMsg","url绑定路由问题");
        }else{
            responseData.put("errCode", EmCommonError.UNKNOW_ERROR.getErrCode());
            responseData.put("errMsg",EmCommonError.UNKNOW_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
