package com.example.demo;

import com.example.demo.login.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

    //静态资源释放
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/clothes").setViewName("clothes_page");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/furniture").setViewName("furniture_page");
        registry.addViewController("/chart1").setViewName("chart1");

        super.addViewControllers(registry);
    }

//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
////        super.addInterceptors(registry);
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/","login","/register");
//    }
}
