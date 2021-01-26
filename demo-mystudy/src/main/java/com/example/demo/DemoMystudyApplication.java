package com.example.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName: DemoMystudyApplication
 * @Description: DemoMystudyApplication 项目 启动类
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/11/19 03:23
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class DemoMystudyApplication {

	public static void main(String[] args) {
		System.out.println("main_args : " + JSON.toJSONString(args));
		ApplicationContext context = SpringApplication.run(DemoMystudyApplication.class, args);
		System.out.println("ApplicationName : " + context.getApplicationName());
	}
}
