package com.cs.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description :
 * @author: Mark (majianyou@wxdata.cn)
 * @version:
 * @Date: 2018/10/29
 */
@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
	;

	@Override
	public void run(ApplicationArguments applicationArguments) {
		log.info("-------------->" + "项目启动，now=" + new Date());
	}

}
