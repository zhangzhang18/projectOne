package com.project.one.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: zhangchunmeng
 * @Date: 2019-04-24
 */

@Configuration
@MapperScan("com.project.one.mapper")
public class MybatisConfig {

}
