package com.sailing.springbootmybatis.config;

import com.sailing.springbootmybatis.filter.JWTParseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.config
 * @Description: 过滤器配置类
 * @date 2018/12/6 17：35
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new JWTParseFilter());
        //registration.addUrlPatterns("/yjbj/*");这块不能这么写，拦截的是server.context-path后面的路径
        registration.addUrlPatterns("/*");
        registration.setName("jWTParseFilter");
        registration.setOrder(1);
        return registration;
    }
}
