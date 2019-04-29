package com.ojas.obs.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.ojas.*")
public class TitleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitleApplication.class, args);
	}

}
