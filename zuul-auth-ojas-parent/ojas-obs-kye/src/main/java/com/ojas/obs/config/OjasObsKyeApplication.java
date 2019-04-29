package com.ojas.obs.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author tshiva
 *
 */
@SpringBootApplication(scanBasePackages = "com.ojas.obs.*")
public class OjasObsKyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjasObsKyeApplication.class, args);
	}
}