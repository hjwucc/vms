package com.wu.vms.config;

import ch.qos.logback.classic.pattern.DateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }
}
