package com.jicl.config;

import com.jicl.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author : xianzilei
 * @date : 2019/12/2 08:33
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**")
                .addPathPatterns("/user")
                .addPathPatterns("/admin/**")
                .addPathPatterns("/admin")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/toLoginPage")
                .excludePathPatterns("/user/toRegisterPage")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/admin/toAdminLoginPage")
                .excludePathPatterns("/admin/login");
    }
}
