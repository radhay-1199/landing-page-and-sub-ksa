package com.gl.combo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootConfiguration
@EnableFeignClients(basePackages = {"com.gl.combo.feign"}) 
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages= {"com.gl.combo"})
@PropertySource("file:${HOME}/supercombo/application.properties")
public class ComboApplication extends SpringBootServletInitializer
{

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ComboApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ComboApplication.class, args);
	}

}
