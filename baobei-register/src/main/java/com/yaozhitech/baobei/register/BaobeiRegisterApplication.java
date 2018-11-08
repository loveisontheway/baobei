package com.yaozhitech.baobei.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BaobeiRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaobeiRegisterApplication.class, args);
	}
}
