package com.jmsw.framework.core.context;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JmswFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            JmswRequestHeader requestHeader = new JmswRequestHeader();
            requestHeader.setAppId(request.getHeader("appId"));
            requestHeader.setAppVersion(request.getHeader("appVersion"));
            requestHeader.setClientVersion(request.getHeader("clientVersion"));
            requestHeader.setDevId(request.getHeader("devId"));
            requestHeader.setDevName(request.getHeader("devName"));
            requestHeader.setDevType(request.getHeader("devType"));
            requestHeader.setDitchCode(request.getHeader("ditchCode"));
            requestHeader.setIp(request.getHeader("ip"));
            requestHeader.setNet(request.getHeader("net"));
            requestHeader.setSign(request.getHeader("sign"));
            requestHeader.setToken(request.getHeader("token"));
            requestHeader.setUserAgent(request.getHeader("User-Agent"));
            requestHeader.setUserId(request.getHeader("userId"));
            requestHeader.setxForwardedFor(request.getHeader("X-Forwarded-For"));

            JmswContext.setRequestHeader(requestHeader);
        } catch (Exception e) {
            // 不处理 没有request header没关系
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
