package com.serviceCaller.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.serviceCaller.filter.JwtAuthorizeFilter;


/*
 * 注册jwt认证过滤器
 */
/*
@Configuration
public class JwtConfig {

    */
/*
     * 注册过滤器类和过滤的url
     *//*

    @Bean
    public FilterRegistrationBean basicFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthorizeFilter filter = new JwtAuthorizeFilter();
        registrationBean.setFilter(filter);

        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/service/*");//The auth filter is just for the use of service invoke

        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}*/
