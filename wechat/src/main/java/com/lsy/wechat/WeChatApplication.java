package com.lsy.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.lsy.common", "com.lsy.wechat"})
@EnableJpaRepositories(basePackages = "com.lsy.common.repository")
@EntityScan(basePackages = "com.lsy.common.domain")
//@EnableJpaAuditing
public class WeChatApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeChatApplication.class, args);
	}
}
