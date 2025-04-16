package me.peak.server;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableCasClient  //需要配合cas服务才能使用
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
