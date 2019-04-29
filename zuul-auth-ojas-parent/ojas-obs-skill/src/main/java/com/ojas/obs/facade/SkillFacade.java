package com.ojas.obs.facade;

import org.springframework.http.ResponseEntity;

import com.ojas.obs.request.SkillRequest;



public interface SkillFacade {
     ResponseEntity<Object> setSkillInfo(SkillRequest skillRequest);
	
	ResponseEntity<Object> getSkillInfo(SkillRequest skillRequest);

}
