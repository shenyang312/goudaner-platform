package com.goudaner.platform.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer  {

    @Autowired
    LoginInterceptor loginInterceptor;

    /**
     * 不需要登录拦截的url
     */
    final String[] notLoginInterceptPaths = {"/static/**","/admin/login","/error/**","/login"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这里添加多个拦截器
        // 登录拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns(notLoginInterceptPaths);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

/*		@Bean
		public InternalResourceViewResolver viewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/templates/");
			resolver.setSuffix(".html");
			resolver.setViewClass(JstlView.class);
			return resolver;
		}*/

    /**
     * 配置不需要经过controller就跳转到登录页面
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");

    }

    /***
     * addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        //排除静态资源拦截
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}