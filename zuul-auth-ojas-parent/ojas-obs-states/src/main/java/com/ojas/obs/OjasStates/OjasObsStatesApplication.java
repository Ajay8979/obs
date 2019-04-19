package com.ojas.obs.OjasStates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.ojas.obs.*")
public class OjasObsStatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OjasObsStatesApplication.class, args); 
	}

}
