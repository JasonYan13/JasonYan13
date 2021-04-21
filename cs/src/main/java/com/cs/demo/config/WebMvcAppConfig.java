package com.cs.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 权限拦截器
 * @version:V1.0
 */
@Component
public class WebMvcAppConfig implements WebMvcConfigurer {
    @Autowired
    private SignInterceptor signInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> patterns = new ArrayList<String>() {{
            add("/api/v1/**");
        }};
        List<String> excludeUrls = new ArrayList<String>() {{
            add("/api/v1/login/**");
        }};
        registry.addInterceptor(signInterceptor).addPathPatterns(patterns).excludePathPatterns(excludeUrls);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}
