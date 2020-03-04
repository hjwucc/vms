package com.wu.vms.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @date ：Created in 2019/11/23 20:28
 * @description：登录成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("data",map);
        responseData.put("status","success");
        ObjectMapper mapper = new ObjectMapper();
        out.write(mapper.writeValueAsString(responseData));
        out.flush();
        out.close();
        //response.sendRedirect("/swagger-ui.html");
    }
}
