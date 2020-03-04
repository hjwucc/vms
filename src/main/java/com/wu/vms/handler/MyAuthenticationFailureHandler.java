package com.wu.vms.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 18:23
 * @description：登录失败处理
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setStatus(401);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> responseData = new HashMap<>();
        map.put("errCode","20004");
        if(e instanceof LockedException){
            map.put("errMsg","账户被锁定");
        }else if(e instanceof BadCredentialsException){
            map.put("errMsg","账户名或密码错误");
        }else if(e instanceof DisabledException){
            map.put("errMsg","账户被禁用");
        }else if(e instanceof AccountExpiredException){
            map.put("errMsg","账户已过期");
        }else if(e instanceof CredentialsExpiredException){
            map.put("errMsg","密码已过期");
        }else{
            map.put("errMsg","登录失败");
        }
        responseData.put("data",map);
        responseData.put("status","fail");
        ObjectMapper mapper = new ObjectMapper();
        out.write(mapper.writeValueAsString(responseData));
        out.flush();
        out.close();
    }
}
