package com.ojas.obs.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;

import com.google.gson.Gson;




public class ExceptionFacade {

	public static ResponseEntity<Object> manageRestClientException(
			RestClientResponseException restClientResponseException) {
		ErrorRespponse error = new ErrorRespponse();
		ResponseEntity<Object> response = null;
		int statusCode = restClientResponseException.getRawStatusCode();
		switch (statusCode) {
		case 401:
			error.setMessage("SESSION_EXPIRED_ERROR");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
			break;
		case 599:
			error.setMessage("DB_TIMEOUT_ERROR");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.REQUEST_TIMEOUT);
			break;
		case 404:
			error.setMessage("SERVICE_NOT_FOUND");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			break;
		case 503:
			error.setMessage("SERVICE_NOT_FOUND");
			error.setCode("503");
			response = new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
			break;
		case 422:
			error.setMessage("SERVICE_NOT_FOUND");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
			break;
		case 500:
			error.setMessage("DATA_BINDING_ERROR");
			error.setCode("500");
			response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			break;
		case 403:
			error.setMessage("Forbidden Error");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
			break;
		case 504:
			error.setMessage("The Gateway session timeout");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.ACCEPTED);
			break;
		case 423:
			error.setMessage("Resource Locked");
			error.setCode(String.valueOf(statusCode));
			response = new ResponseEntity<>(error, HttpStatus.LOCKED);
			break;
		default:
			if (restClientResponseException.getResponseHeaders().getContentType().equals("text/html")) {
				error.setMessage("DATA_BINDING_ERROR");
				error.setCode("500");
				response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				Gson gson = new Gson();
				String errorString = restClientResponseException.getResponseBodyAsString();
				try {
					error = gson.fromJson(errorString, ErrorRespponse.class);
				} catch (Exception ex) {
					error = new ErrorRespponse();
					error.setMessage("DATA_BINDING_ERROR");
					if (error.getCode() != null) {
						error.setCode(error.getCode());
					} else {
						error.setCode("500");
					}
					response = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			break;
		}

		return response;
	}
}