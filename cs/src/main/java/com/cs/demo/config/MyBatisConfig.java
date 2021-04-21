/**
 * MyBatisMapperScannerConfig create on 2017-03-12
 * Copyright (c)
 *
 * @author <a href="wangc032138@aliyun.com">wangchuan</a>
 * @version 1.0
 */
package com.cs.demo.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ClassName : MyBatisMapperScannerConfig
 * @Description : 扫描mybatis的接口
 */
@Configuration
public class MyBatisConfig {
  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    //获取之前注入的beanName为sqlSessionFactory的对象
    //指定xml配置文件的路径
    mapperScannerConfigurer.setBasePackage("com.cs.demo.mapper");
    return mapperScannerConfigurer;
  }

  @Bean
  public PageHelper pageHelper() {
    System.out.println("MyBatisConfiguration.pageHelper()");
    PageHelper pageHelper = new PageHelper();
    Properties p = new Properties();
    p.setProperty("offsetAsPageNum", "true");
    p.setProperty("rowBoundsWithCount", "true");
    p.setProperty("reasonable", "true");
    pageHelper.setProperties(p);
    return pageHelper;
  }
}