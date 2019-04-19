package com.ojas.obs.ObsEmployeeSkills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= "com.ojas.obs.*")
public class ObsEmployeeSkillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObsEmployeeSkillsApplication.class, args);
	}

}