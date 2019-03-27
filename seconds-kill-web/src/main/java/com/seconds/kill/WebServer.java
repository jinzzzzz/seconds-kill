package com.seconds.kill;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.seconds.kill"}
							,exclude = { DataSourceAutoConfiguration.class})
public class WebServer extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebServer.class);
		app.run(args);
		System.out.println("web start ...");
	}
}
