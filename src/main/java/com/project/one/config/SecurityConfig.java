/*
package com.project.one.config;

import com.project.one.service.UserService;
import com.project.one.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

*/
/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-26
 *//*

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    private UserService userService;

    @Bean
    UserDetailsService customUserService() { //注册UserDetailsService 的bean
        return new UserServiceImpl();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("用自己定义的usersevices来验证用户");
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login/login").failureUrl("/login/login?error").defaultSuccessUrl("/index").permitAll()
                .and().logout().permitAll().logoutUrl("/login/logout");
    }

}
*/
