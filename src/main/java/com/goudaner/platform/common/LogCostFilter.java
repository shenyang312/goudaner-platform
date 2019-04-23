package com.goudaner.platform.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Configuration
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
public class LogCostFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("进入logFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println("进入Filter=" + (System.currentTimeMillis() - start));
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("完成所有请求" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}