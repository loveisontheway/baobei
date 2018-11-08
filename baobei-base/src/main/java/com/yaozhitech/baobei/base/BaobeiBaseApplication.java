package com.yaozhitech.baobei.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaobeiBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaobeiBaseApplication.class, args);
	}
}
