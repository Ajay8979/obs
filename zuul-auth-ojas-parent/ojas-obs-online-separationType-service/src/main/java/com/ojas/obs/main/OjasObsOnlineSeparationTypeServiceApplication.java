package com.ojas.obs.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
 * @author nsrikanth
 *
 */
@SpringBootApplication(scanBasePackages="com.ojas.obs.*")
public class OjasObsOnlineSeparationTypeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjasObsOnlineSeparationTypeServiceApplication.class, args);
	}
 
}
