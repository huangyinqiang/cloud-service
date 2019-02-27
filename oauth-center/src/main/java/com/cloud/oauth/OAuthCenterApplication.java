package com.cloud.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 认证中心
 * 
 * @author 小威老师 xiaoweijiagou@163.com
 *
 */
@EnableFeignClients //启动feign
@EnableDiscoveryClient
@SpringBootApplication
public class OAuthCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthCenterApplication.class, args);
	}

}