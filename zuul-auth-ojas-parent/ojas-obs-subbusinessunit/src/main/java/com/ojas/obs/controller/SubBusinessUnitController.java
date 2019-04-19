package com.ojas.obs.controller;

import static com.ojas.obs.constants.SubBusinessUnitConstants.DELETE;
import static com.ojas.obs.constants.SubBusinessUnitConstants.GET;
import static com.ojas.obs.constants.SubBusinessUnitConstants.SET;
import static com.ojas.obs.constants.SubBusinessUnitConstants.UPDATE;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.facade.SubBusinessUnitFacade;
import com.ojas.obs.model.SubBusinessUnit;
import com.ojas.obs.request.SubBusinessUnitRequest;

/**
 * 
 * @author asuneel
 *
 */

@Controller
public class SubBusinessUnitController {

	@Autowired
	SubBusinessUnitFacade subBusinessUnitFacade;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param subBusinessRequest
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(SET)
	public ResponseEntity<Object> setSubBusinessUnit(@RequestBody SubBusinessUnitRequest subBusinessUnitRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)  throws SQLException {

		logger.debug("incoming requests " + subBusinessUnitRequest);

		try {
			List<SubBusinessUnit> subBusinessUnitList = subBusinessUnitRequest.getSubBusinessUnitModel();
			if (subBusinessUnitRequest.getTransactionType() == null || null == subBusinessUnitList) {
				ErrorResponse error = new ErrorResponse();
				error.setMessage("request is not valid");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			for (SubBusinessUnit subBusinessUnit : subBusinessUnitList) {

				if ((subBusinessUnitRequest.getTransactionType().equalsIgnoreCase("save")
						|| subBusinessUnitRequest.getTransactionType().equalsIgnoreCase("update"))
						&& ((null == subBusinessUnit.getName() || subBusinessUnit.getName().isEmpty())
						|| (subBusinessUnit.getCostCenterId() == null)
						|| (subBusinessUnit.getBusinessUnitId() == null))) {

					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setStatusCode("422");
					error.setMessage("Request is  invalid");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);

				}

				if ((subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(UPDATE)
						|| subBusinessUnitRequest.getTransactionType().equalsIgnoreCase(DELETE))
						&& subBusinessUnit.getId() == null) {
					ErrorResponse error = new ErrorResponse();
					logger.debug("data is  invalid");
					error.setMessage("Request is  invalid");
					error.setStatusCode("422");
					return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);

				}

			}
			return subBusinessUnitFacade.setSubBusinessUnit(subBusinessUnitRequest);

		} catch (Exception exception) {
			logger.debug("inside catch block.*******");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}

	/**
	 * 
	 * @param subBusinessRequest
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	@RequestMapping(GET)
	public ResponseEntity<Object> getSubBusinessUnit(@RequestBody SubBusinessUnitRequest subBusinessUnitRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		try {
			if (!subBusinessUnitRequest.getTransactionType().equalsIgnoreCase("get")) {
				logger.debug("request is not valid");
				ErrorResponse error = new ErrorResponse();
				error.setMessage("Data must not be null");
				error.setStatusCode("422");
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);

			}
			return subBusinessUnitFacade.getSubBusinessUnit(subBusinessUnitRequest);

		} catch (Exception exception) {

			logger.debug("inside getSubBusinessUnit catch block in controller.*******");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			error.setStatusCode("409");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}

	}
}
