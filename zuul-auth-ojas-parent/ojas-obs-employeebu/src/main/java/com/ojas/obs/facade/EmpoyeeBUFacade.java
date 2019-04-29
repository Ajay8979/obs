package com.ojas.obs.facade;

import static com.ojas.obs.constants.Constants.DELETE;
import static com.ojas.obs.constants.Constants.FAILED;
import static com.ojas.obs.constants.Constants.GETALL;
import static com.ojas.obs.constants.Constants.GETBYID;
import static com.ojas.obs.constants.Constants.SAVE;
import static com.ojas.obs.constants.Constants.SUCCESS;
import static com.ojas.obs.constants.Constants.UPDATE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.EmployeeBUDao;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.model.EmployeeBUDetails;
import com.ojas.obs.request.EmployeeBUDetailsRequest;
import com.ojas.obs.response.EmployeeBUDeatailsResponse;

/**
 * 
 * @author uyashwanth
 *
 */
@Service
public class EmpoyeeBUFacade {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private EmployeeBUDao employeebuDao;

	/**
	 * 
	 * @param employeeBURequest
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> setEmployeeBU(EmployeeBUDetailsRequest employeeBURequest) throws SQLException {
		logger.debug("inside setEmployeeBU method : " + employeeBURequest);
		EmployeeBUDeatailsResponse employeeburesponse = null;
		try {
			if (employeeBURequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				employeeburesponse = new EmployeeBUDeatailsResponse();
				boolean saveEmployeebu = employeebuDao.saveEmployeebu(employeeBURequest);
				logger.debug("*****inside  save condition.***** : " + saveEmployeebu);
				if (saveEmployeebu) {
					int allRecordsCount = employeebuDao.getAllRecordsCount();
					employeeburesponse.setTotalCount(allRecordsCount);
					employeeburesponse.setStatusMessage("Successfully BusinessUnit record saved");
					return new ResponseEntity<>(employeeburesponse, HttpStatus.OK);
				} else {
					logger.error("**Failed to save the record(s)***");
					employeeburesponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(employeeburesponse, HttpStatus.CONFLICT);
				}
			}
			if (employeeBURequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				employeeburesponse = new EmployeeBUDeatailsResponse();
				boolean updateemployeebu = employeebuDao.updateEmployeebu(employeeBURequest);
				logger.debug("*****inside  update condition.***** : " + updateemployeebu);
				if (updateemployeebu) {
					employeeburesponse.setStatusMessage("Successfully BusinessUnit record updated");
					return new ResponseEntity<>(employeeburesponse, HttpStatus.OK);
				} else {
					logger.error("**Failed to update the record(s)***");
					employeeburesponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(employeeburesponse, HttpStatus.CONFLICT);
				}
			}
			if (employeeBURequest.getTransactionType().equalsIgnoreCase(DELETE)) {
				employeeburesponse = new EmployeeBUDeatailsResponse();
				boolean deleteId = false;
				logger.debug("*****inside  delete condition.***** : " + deleteId);
				List<EmployeeBUDetails> budetails = employeeBURequest.getEmployeeBUDeatils();
				for (EmployeeBUDetails employeebudetails : budetails) {
					Integer id = employeebudetails.getId();
					deleteId = employeebuDao.deleteEmployeeRecord(id);
				}
				if (deleteId) {
					employeeburesponse.setStatusMessage("Successfully BusinessUnit record deleted");
					return new ResponseEntity<>(employeeburesponse, HttpStatus.OK);
				} else {
					logger.error("**Failed to delete the record(s)***");
					employeeburesponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(employeeburesponse, HttpStatus.CONFLICT);
				}
			}
		} catch (Exception exception) {
			logger.debug("inside EmployeeEducationFacde catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(exception.getMessage());
		}
		return null;

	}

	/**
	 * 
	 * @param employeeRequest
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> getEmployeeBUDetails(EmployeeBUDetailsRequest employeeRequest) throws SQLException {
//		EmployeeBUDeatailsResponse employeeburesponse = new EmployeeBUDeatailsResponse();
//		logger.debug("inside getAllEmployeeBU in EmployeeBUFacde.***");
//		List<EmployeeBUDetails> allEmployeebuDetails = employeebuDao.getAllEmployeebu(employeeRequest);
//		if (null == allEmployeebuDetails || allEmployeebuDetails.isEmpty()) {
//			employeeburesponse.setListCourse(new ArrayList<>());
//			employeeburesponse.setStatusMessage(FAILED);
//			employeeburesponse.setTotalCount(0);
//		} else {
//			int recordsCount = employeebuDao.getAllRecordsCount();
//			employeeburesponse.setStatusMessage(SUCCESS);
//			employeeburesponse.setListCourse(allEmployeebuDetails);
//			employeeburesponse.setTotalCount(recordsCount);
//		}
//		return new ResponseEntity<>(employeeburesponse, HttpStatus.OK);
//	}
		EmployeeBUDeatailsResponse employeeburesponse = null;
		logger.debug("inside  getall condition.****** : " + employeeRequest);
		if (employeeRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
			logger.debug("inside  get condition.****** : ");
			employeeburesponse = new EmployeeBUDeatailsResponse();
			int totalCount = employeebuDao.getAllRecordsCount();
			List<EmployeeBUDetails> allCertificationDetails = employeebuDao.getAllEmployeebu(employeeRequest);

			if (allCertificationDetails == null || allCertificationDetails.isEmpty()) {
				employeeburesponse.setListCourse(new ArrayList<>());
				employeeburesponse.setStatusMessage("No records found");
				employeeburesponse.setTotalCount(0);
				return new ResponseEntity<>(employeeburesponse, HttpStatus.CONFLICT);
			} else {
				if (employeeRequest.getPageNo() == 0 || employeeRequest.getPageSize() == 0) {
					employeeburesponse.setStatusMessage(SUCCESS);
					employeeburesponse.setListCourse(allCertificationDetails);
					employeeburesponse.setTotalCount(totalCount);
				} else {
					employeeburesponse.setStatusMessage(SUCCESS);
					employeeburesponse.setTotalCount(totalCount);
				}
				return new ResponseEntity<>(employeeburesponse, HttpStatus.OK);
			}
		}

		if (employeeRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			employeeburesponse = new EmployeeBUDeatailsResponse();
			List<EmployeeBUDetails> list = employeebuDao.getById(employeeRequest);
			logger.debug("inside  get_count condition.****** : ");
			if (list.size() == 0) {
				employeeburesponse.setStatusMessage("No record Present");
				employeeburesponse.setTotalCount(0);
				return new ResponseEntity<>(employeeburesponse, HttpStatus.CONFLICT);
			} else {
				employeeburesponse.setStatusMessage(SUCCESS);
				employeeburesponse.setListCourse(list);
				return new ResponseEntity<>(employeeburesponse, HttpStatus.OK);
			}
		}
		return null;

	}

	/**
	 * 
	 * @param allEmployeeDetails
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List<EmployeeBUDetails> recordsPerPage(List<EmployeeBUDetails> allEmployeeDetails, int pageSize,
			int pageNo) {
		List<EmployeeBUDetails> getAllFiltered = new ArrayList<>();
		if (allEmployeeDetails != null && !allEmployeeDetails.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
			pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < allEmployeeDetails.size() ? endIndex : allEmployeeDetails.size();
				startIndex = startIndex < allEmployeeDetails.size() ? startIndex : 0;
				getAllFiltered = allEmployeeDetails.subList(startIndex, endIndex);
			}
		}
		return getAllFiltered;

	}
}
