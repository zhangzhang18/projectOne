package com.project.one.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 */
@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean encodingFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CharacterEncodingFilter());
        bean.addInitParameter("encoding", "UTF-8");
        //FIXME: https://github.com/Netflix/eureka/issues/702
        bean.addInitParameter("forceEncoding", "true");
        bean.setName("characterEncodingFilter");
        bean.addUrlPatterns("/*");
        bean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD);
        bean.setOrder(1);
        return bean;
    }

}
