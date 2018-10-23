package com.xm.xmscapi.config;

import com.xm.xmscapi.interceptor.AccessInterceptor;
import com.xm.xmscapi.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    private AccessInterceptor accessInterceptor;

    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor).addPathPatterns("/**").excludePathPatterns("/pay/*");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/pay/*");
    }
}
