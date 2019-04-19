package com.ojas.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author nsrikanth
 *
 */


@SpringBootApplication(scanBasePackages="com.ojas.*")
public class OjasObsDesignationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjasObsDesignationApplication.class, args);
	}

}
