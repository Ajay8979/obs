package com.ojas.obs.main;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ojas.obs.*")
public class RoleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleManagementApplication.class, args);
		BasicConfigurator.configure();
	}

}
