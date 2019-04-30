package com.ojas.obs.emp_edu.facadeImpl;

import static com.ojas.obs.emp_edu.utility.Constants.DELETE;
import static com.ojas.obs.emp_edu.utility.Constants.GETALL;
import static com.ojas.obs.emp_edu.utility.Constants.ID_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.SAVE;
import static com.ojas.obs.emp_edu.utility.Constants.UPDATE;
import static com.ojas.obs.emp_edu.utility.Constants.VALID_TRANSACTIONTYPE;
import static com.ojas.obs.emp_edu.utility.Constants.TRANSACTIONTYPE_NULL;
import static com.ojas.obs.emp_edu.utility.Constants.LIST_OBTAINED;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.ojas.obs.emp_edu.dao.EmployeeEducationDetailsDao;
import com.ojas.obs.emp_edu.facade.EmployeeEducationFacade;
import com.ojas.obs.emp_edu.model.EmployeeEducationDetails;
import com.ojas.obs.emp_edu.model.EmployeeEducationDetailsRequest;
import com.ojas.obs.emp_edu.model.EmployeeEducationResponse;
import com.ojas.obs.emp_edu.model.ErrorResponse;

/**
 * @author vraghuram
 *
 */
@Component
public class EmployeeEducationDetailsFacadeImpl implements EmployeeEducationFacade {
	@Autowired
	EmployeeEducationDetailsDao employeeEducationDetailsDaoImpl;
	ResponseEntity<Object> responseEntity = null;

// set cluster transaction for EmployeeEducationDetails
	@Override
	public ResponseEntity<Object> setEmployeeEducationDetails(EmployeeEducationDetailsRequest emplEduDetailsRequestObj)
			throws SQLException {
		if (null != emplEduDetailsRequestObj.getTransaactionType()) {
			if (emplEduDetailsRequestObj.getTransaactionType().equalsIgnoreCase(SAVE)) {
				return this.saveTransaction(emplEduDetailsRequestObj);
			} else if (emplEduDetailsRequestObj.getTransaactionType().equalsIgnoreCase(UPDATE)) {
				return this.updateTransaction(emplEduDetailsRequestObj);
			} else if (emplEduDetailsRequestObj.getTransaactionType().equalsIgnoreCase(DELETE)) {
				return this.deleteTransaction(emplEduDetailsRequestObj);
			} else {
				ErrorResponse error = new ErrorResponse();
				error.setStatuscode("422");
				error.setStatusMessage(VALID_TRANSACTIONTYPE);
				return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} else {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(TRANSACTIONTYPE_NULL);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// get cluster transaction for EmployeeEducationDetails
	@Override
	public ResponseEntity<Object> getEmployeeEducationDetails(EmployeeEducationDetailsRequest emplEduDetailsRequestObj)
			throws SQLException {
		List<EmployeeEducationDetails> employeeEducationDetailsList = null;
		EmployeeEducationResponse employeeEducationResponse = null;
		List<EmployeeEducationDetails> idValidation = null;
		List<EmployeeEducationDetails> empIdValidation = null;

		if (emplEduDetailsRequestObj.getTransaactionType().equalsIgnoreCase(GETALL)) {
			if (null != emplEduDetailsRequestObj.getEmployeeEducationDetailsList()
					&& emplEduDetailsRequestObj.getEmployeeEducationDetailsList().size() > 0) {
				idValidation = this.getIdValidation(emplEduDetailsRequestObj);
				empIdValidation = this.getEmpIdValidation(emplEduDetailsRequestObj);

				if ((null != idValidation && idValidation.isEmpty())
						|| (null != empIdValidation && empIdValidation.isEmpty())) {
					employeeEducationDetailsList = employeeEducationDetailsDaoImpl
							.getEmployeeEducationDetailsById(emplEduDetailsRequestObj);
				} else {
					employeeEducationResponse = new EmployeeEducationResponse();
					employeeEducationResponse.setEmployeeEducationDetailsList(idValidation);
					employeeEducationResponse.setStatusCode("422");
					employeeEducationResponse.setStatusMessage(ID_NULL);
					return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
				}

			} else {
				employeeEducationDetailsList = employeeEducationDetailsDaoImpl
						.getEmployeeEducationDetails(emplEduDetailsRequestObj);
			}

			if (employeeEducationDetailsList.size() > 0) {
				employeeEducationResponse = new EmployeeEducationResponse();
				employeeEducationResponse.setEmployeeEducationDetailsList(employeeEducationDetailsList);
				employeeEducationResponse.setStatusCode("200");
				employeeEducationResponse.setStatusMessage(LIST_OBTAINED);
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
			} else {
				employeeEducationResponse = new EmployeeEducationResponse();
				employeeEducationResponse.setEmployeeEducationDetailsList(employeeEducationDetailsList);
				employeeEducationResponse.setStatusCode("200");
				employeeEducationResponse.setStatusMessage("There are no records");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
			}

		} else {
			ErrorResponse error = new ErrorResponse();
			error.setStatuscode("422");
			error.setStatusMessage(VALID_TRANSACTIONTYPE);
			return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

// id validations for update and delete and getbyid
	public List<EmployeeEducationDetails> getIdValidation(
			EmployeeEducationDetailsRequest employeeEducationDetailsRequest) {
		List<EmployeeEducationDetails> employeeEducationDetailslist = new ArrayList<>();
		for (EmployeeEducationDetails employeeEducationDetails : employeeEducationDetailsRequest
				.getEmployeeEducationDetailsList()) {
			if (null == employeeEducationDetails.getId()) {
				employeeEducationDetailslist.add(employeeEducationDetails);
			}
		}
		return employeeEducationDetailslist;
	}

	public List<EmployeeEducationDetails> getEmpIdValidation(
			EmployeeEducationDetailsRequest employeeEducationDetailsRequest) {
		List<EmployeeEducationDetails> employeeEducationDetailslist = new ArrayList<>();
		for (EmployeeEducationDetails employeeEducationDetails : employeeEducationDetailsRequest
				.getEmployeeEducationDetailsList()) {
			if (null == employeeEducationDetails.getEmployeeId()) {
				employeeEducationDetailslist.add(employeeEducationDetails);
			}
		}
		return employeeEducationDetailslist;
	}

	// save transaction for EmployeeEducationDetails
	public ResponseEntity<Object> saveTransaction(EmployeeEducationDetailsRequest emplEduDetailsRequestObj)
			throws SQLException {

		for (EmployeeEducationDetails employeeEducationDetails : emplEduDetailsRequestObj
				.getEmployeeEducationDetailsList()) {
			employeeEducationDetails.setFlag(true);
		}
		int[] saveEmployeeEducationDetails = employeeEducationDetailsDaoImpl
				.saveEmployeeEducationDetails(emplEduDetailsRequestObj);
		int b = 0;
		for (int a : saveEmployeeEducationDetails) {
			if (a > 0) {
				b++;
			}
		}
		if (b == emplEduDetailsRequestObj.getEmployeeEducationDetailsList().size()) {
			EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
			employeeEducationResponse.setStatusCode("200");
			employeeEducationResponse.setStatusMessage("Employee Education Details have been saved");
			return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
		} else {
			EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
			employeeEducationResponse.setStatusCode("409");
			employeeEducationResponse.setStatusMessage("Employee Education Details has not saved with id is"
					+ emplEduDetailsRequestObj.getEmployeeEducationDetailsList().get(b + 1));
			return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
		}

	}

	// update transaction for EmployeeEducationDetails
	public ResponseEntity<Object> updateTransaction(EmployeeEducationDetailsRequest emplEduDetailsRequestObj)
			throws SQLException {

		List<EmployeeEducationDetails> list = new ArrayList<>();
		List<EmployeeEducationDetails> idValidation = this.getIdValidation(emplEduDetailsRequestObj);
		if (idValidation.size() == 0) {
			int[] updateEmployeeEducationDetails = employeeEducationDetailsDaoImpl
					.updateEmployeeEducationDetails(emplEduDetailsRequestObj);

			for (int a : updateEmployeeEducationDetails) {
				if (a <= 0) {
					list.add(emplEduDetailsRequestObj.getEmployeeEducationDetailsList().get(a));
				}
			}
			if (list.size() == 0) {
				EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
				employeeEducationResponse.setStatusCode("200");
				employeeEducationResponse.setStatusMessage("Employee Education Details have been updated");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
			} else {
				EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
				employeeEducationResponse.setEmployeeEducationDetailsList(list);
				employeeEducationResponse.setStatusCode("409");
				employeeEducationResponse.setStatusMessage("Employee Education Details has not updated  ");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.UNPROCESSABLE_ENTITY);

			}
		} else {
			EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
			employeeEducationResponse.setEmployeeEducationDetailsList(idValidation);
			employeeEducationResponse.setStatusCode("422");
			employeeEducationResponse.setStatusMessage(ID_NULL);
			return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// delete transaction for EmployeeEducationDetails
	public ResponseEntity<Object> deleteTransaction(EmployeeEducationDetailsRequest emplEduDetailsRequestObj)
			throws SQLException {
		List<EmployeeEducationDetails> list = new ArrayList<>();
		List<EmployeeEducationDetails> idValidation = this.getIdValidation(emplEduDetailsRequestObj);
		if (idValidation.size() == 0) {
			int[] updateEmployeeEducationDetails = employeeEducationDetailsDaoImpl
					.deleteEmployeeEducationDetails(emplEduDetailsRequestObj);

			for (int a : updateEmployeeEducationDetails) {
				if (a <= 0) {
					list.add(emplEduDetailsRequestObj.getEmployeeEducationDetailsList().get(a));
				}
			}
			if (list.size() == 0) {
				EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
				employeeEducationResponse.setStatusCode("200");
				employeeEducationResponse.setStatusMessage("Employee Education Details have been deleted");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.OK);
			} else {
				EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
				employeeEducationResponse.setEmployeeEducationDetailsList(list);
				employeeEducationResponse.setStatusCode("409");
				employeeEducationResponse.setStatusMessage("Employee Education Details has not deleted  ");
				return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.UNPROCESSABLE_ENTITY);

			}
		} else {
			EmployeeEducationResponse employeeEducationResponse = new EmployeeEducationResponse();
			employeeEducationResponse.setEmployeeEducationDetailsList(idValidation);
			employeeEducationResponse.setStatusCode("422");
			employeeEducationResponse.setStatusMessage(ID_NULL);
			return new ResponseEntity<Object>(employeeEducationResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
