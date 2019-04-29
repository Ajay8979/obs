package com.ojas.obs.genders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.ojas.obs.*"})
public class OjasObsGenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjasObsGenderApplication.class, args);
	}

}
