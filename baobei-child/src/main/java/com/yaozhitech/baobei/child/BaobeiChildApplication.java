package com.yaozhitech.baobei.child;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaobeiChildApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaobeiChildApplication.class, args);
	}
}
