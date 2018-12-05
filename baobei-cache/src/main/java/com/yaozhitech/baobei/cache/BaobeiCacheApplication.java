package com.yaozhitech.baobei.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaobeiCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaobeiCacheApplication.class, args);
	}
}
