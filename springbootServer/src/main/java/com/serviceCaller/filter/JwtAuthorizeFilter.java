package com.serviceCaller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceCaller.config.JwtInfo;
import com.serviceCaller.tools.JwtHelper;

/*
 * 用于JWT认证的过滤器
 */
/*public class JwtAuthorizeFilter implements Filter{

    *//*
     * 注入配置文件类
     *//*
    @Autowired
    private JwtInfo jwtInfo;

    @Override
    public void destroy() {

    }

    *//**
     *@Author: Shixiong
     *@Description: Filter to verify the authorization
     *@param request
     *@param response
     *@param chain:
     *@Date: 2018/6/5
     *//*
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String auth = httpRequest.getHeader("Authorization");   //Get the Auth header
        if ((auth != null) && (auth.length() > 7))
        {
            String HeadStr = auth.substring(0, 4).toLowerCase();
            if (HeadStr.compareTo("htxa") == 0)     //The Auth values begin with htxa,according to the Interface Documentation
            {
                auth = auth.substring(5, auth.length()); //Real token for auth begins at [5]
                if (JwtHelper.parseJWT(auth, jwtInfo.getBase64Secret()) != null)
                {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        //Fail to pass the authorization
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        //Return the info of the auth failure
        //ObjectMapper mapper = new ObjectMapper();

        httpResponse.getWriter().write("Authorization Failer");
        return;
    }

    *//**
     *@Author: Shixiong
     *@Description:
     *@param filterConfig
     *@Date: 2018/6/5
     *//*
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

}*/
