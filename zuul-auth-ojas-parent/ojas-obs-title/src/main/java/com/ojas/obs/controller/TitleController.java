package com.ojas.obs.controller;

import static com.ojas.obs.constants.Constants.DELETE;
import static com.ojas.obs.constants.Constants.GET;
import static com.ojas.obs.constants.Constants.SAVE;
import static com.ojas.obs.constants.Constants.SET;
import static com.ojas.obs.constants.Constants.UPDATE;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.TitleFacade;
import com.ojas.obs.model.Model;
import com.ojas.request.Request;

/**
 * 
 * @author uyashwanth
 *
 */

@RestController
@Component
public class TitleController {

	@Autowired
	TitleFacade facade;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param Request
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 */
	@RequestMapping(SET)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> setEmployeeBUDetails(@RequestBody Request request, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		ResponseEntity<Object> responseEntity = null;
		try {
			if (null == request) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Request is I");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			List<Model> modellist = request.getModel();
			if (null == modellist) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Request is In");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			for (Model model : modellist) {
				if ((request.getTransactionType().equalsIgnoreCase(SAVE))
						&& ((model.getRole() == null || model.getRole().isEmpty())
								|| (model.getTitle() == null || model.getTitle().isEmpty())
								|| (model.getEmployeeId() == null || model.getEmployeeId().isEmpty())
								|| (model.getCreatedby() == null))) {
					ErrorResponse error = new ErrorResponse();
					error.setMessage("Request is Inv");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			
			for (Model model : modellist) {
				if ((request.getTransactionType().equalsIgnoreCase(UPDATE)
						|| request.getTransactionType().equalsIgnoreCase(DELETE)) && model.getId() == null) {
					ErrorResponse error = new ErrorResponse();
					error.setMessage("Request is Inva");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			return facade.setTitle(request);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(e.getMessage());
		}
		return responseEntity;
	}

	/**
	 * 
	 * @param courseRequest
	 * @param servletRequest
	 * @param servletResponse
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(GET)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> getTitle(@RequestBody Request buRequest, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws SQLException {
//		ResponseEntity<Object> responseEntity = null;
//		Request request = null;
//		try {
//
////			if (null == courseRequest) {
////				ErrorResponse error = new ErrorResponse();
////				error.setMessage("Data is invalid");
////				return new ResponseEntity<>(error, HttpStatus.CONFLICT);
////			}
//			if (!(buRequest.getTransactionType().equalsIgnoreCase(GETALL))) {
//				ErrorResponse error = new ErrorResponse();
//				error.setMessage("Data is invalid");
//				return new ResponseEntity<>(error, HttpStatus.CONFLICT);
//
//			}
//			
//			return facade.getTitle(request);
//		} catch (Exception e) {
//			ErrorResponse error = new ErrorResponse();
//			error.setMessage(e.getMessage());
//		}
//		return responseEntity;
//
//	} 
		ErrorResponse error = null;
		try {
			if (null == buRequest) {
				logger.debug("request is not valid");
				error = new ErrorResponse();
				error.setMessage("REQUEST_NULL");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return facade.getTitle(buRequest);
		} catch (SQLException exception) {
			error = new ErrorResponse();
			exception.printStackTrace();
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}
}
