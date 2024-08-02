package com.itheima.webconfig;

import com.itheima.interceptor.InterceptorDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* 配置类
* */
@Configuration//“布局，构造，配置 .n”
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private InterceptorDemo interceptorDemo;

    @Override
    //Interceptor拦截器，Registry注册  .n
    public void addInterceptors(InterceptorRegistry registry) {
        //exclude .v把.....排除在外
        // registry注册拦截器.addInterceptor(interceptorDemo)添加自定义拦截器
        //. addPathPatterns("/**")添加拦截路径模式.excludePathPatterns("/login")排除拦截路径模式;
        registry.addInterceptor(interceptorDemo).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
