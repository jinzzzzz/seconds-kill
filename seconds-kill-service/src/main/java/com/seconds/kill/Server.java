package com.seconds.kill;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo(scanBasePackages = "com.seconds.kill.service")
@MapperScan(basePackages = { "cn.thunderwind.ihs.dc.mapper" })
@SpringBootApplication(scanBasePackages={"com.seconds.kill"})
@EnableTransactionManagement
public class Server extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Server.class);
		app.run(args);
		System.out.println("service start ...");
	}
}
