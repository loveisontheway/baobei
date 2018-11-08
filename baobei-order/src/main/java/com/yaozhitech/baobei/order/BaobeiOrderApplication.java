package com.yaozhitech.baobei.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaobeiOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaobeiOrderApplication.class, args);
	}
}
