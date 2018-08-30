package com.springboot.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.springboot.modules.*.mapper")
public class MyBatisConfig {

}
