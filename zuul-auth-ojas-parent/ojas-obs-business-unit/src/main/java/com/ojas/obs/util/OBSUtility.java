package com.ojas.obs.util;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
public class OBSUtility {
	public static HttpHeaders createHeaders() {
		@SuppressWarnings("unused")
		String timeStamp = String.valueOf(new Date().getTime());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}

