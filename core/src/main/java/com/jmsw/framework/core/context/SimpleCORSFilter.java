package com.jmsw.framework.core.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yehao
 * @version 1.0
 * @date 29/14 21:13
 * @Description 跨域过滤器支持
 */
public class SimpleCORSFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${cors.enable:false}")
    private boolean enableCorsSupport;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(enableCorsSupport){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "*");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
