package com.example.demo.config;

import com.example.demo.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //从配置文件中读取文件路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Autowired
    UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/","/test/login.html","/js/**","/img/**","/layui/**","/test/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("/test/login.html");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/layui/**").addResourceLocations("classpath:/static/layui/");
        registry.addResourceHandler("/test/**").addResourceLocations("classpath:/static/test/");
        // registry.addResourceHandler("/template/**").addResourceLocations("classpath:/static/");
        //映射给pic
        registry.addResourceHandler(("/img/**"))
                .addResourceLocations("file:" + uploadFolder);


    }
}
