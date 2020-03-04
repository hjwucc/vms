package com.wu.vms.config;

import com.wu.vms.handler.MyAuthenticationFailureHandler;
import com.wu.vms.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsUtils;


/**
 * @author ：wuba
 * @date ：Created in 2019/11/17 17:53
 * @description：spring security 配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    //加密
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                //关闭防止请求伪造
                .csrf()
                .disable()
                .authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest )
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .failureHandler(failureHandler)
                .successHandler(successHandler)
            .and()
            //注销登录配置
            .logout()
                .logoutUrl("/user/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler)
            .and()
            //授权配置
            .authorizeRequests()
                .antMatchers("/student/**").access("hasAnyRole('admin','student')")
                .antMatchers("/teacher/**").access("hasAnyRole('admin','teacher')")
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/login.html","/swagger-ui.html","/v2/**","/swagger-resources/**","/user/menu")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
