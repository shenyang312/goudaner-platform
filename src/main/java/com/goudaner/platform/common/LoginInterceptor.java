package com.goudaner.platform.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入LoginInterceptor拦截器==============");
        String basePath = request.getContextPath();
        String path = request.getRequestURI();
        logger.info("basePath:" + basePath);
        logger.info("path:" + path);
        logger.info("userkey:"+request.getSession().getAttribute("userkey"));
//        if(request.getSession().getAttribute("userkey") == null){
//
//            logger.info("尚未登录，跳转到登录界面");
//            response.setHeader("Content-Type", "text/html;charset=UTF-8");
//            response.sendRedirect(request.getContextPath() + "/login");
//            return false;
//        }
        logger.info("已登录，放行！");
        return true;
    }

    /*
     * Controller方法调用后，视图渲染前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

//        HandlerMethod method = (HandlerMethod) handler;
        logger.info("CustomerHandlerInterceptor postHandle, {}", "进入了");

        response.getOutputStream().write("append content".getBytes());
    }

    /*
     * 整个请求处理完，视图已渲染。如果存在异常则Exception不为空
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        logger.info("CustomerHandlerInterceptor afterCompletion, {}", "返回了");
    }

}
