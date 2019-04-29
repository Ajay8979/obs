package com.ojas.obs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ojas.obs.*")
public class SubBusinessUnitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubBusinessUnitApplication.class, args);
	}

}
