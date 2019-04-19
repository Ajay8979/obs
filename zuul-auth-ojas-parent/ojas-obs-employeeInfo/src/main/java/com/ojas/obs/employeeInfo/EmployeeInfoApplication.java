package com.ojas.obs.employeeInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author sdileep
 *
 */
@SpringBootApplication(scanBasePackages = "com.ojas.obs.*")
public class EmployeeInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeInfoApplication.class, args);
	}
}
