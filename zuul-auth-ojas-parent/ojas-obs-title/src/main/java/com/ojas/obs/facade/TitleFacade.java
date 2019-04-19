package com.ojas.obs.facade;

import static com.ojas.obs.constants.Constants.DELETE;
import static com.ojas.obs.constants.Constants.GETALL;
import static com.ojas.obs.constants.Constants.GETBYID;
import static com.ojas.obs.constants.Constants.FAILED;
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

import com.ojas.obs.dao.TitleDao;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.model.Model;
import com.ojas.request.Request;
import com.ojas.response.Response;

/**
 * 
 * @author uyashwanth
 *
 */
@Service
public class TitleFacade {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TitleDao titleDao;

	/**
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> setTitle(Request request) throws SQLException {
		Response response = null;
		try {
			if (request.getTransactionType().equalsIgnoreCase(SAVE)) {
				response = new Response();
				boolean saveTitle = titleDao.saveTitle(request);
				if (saveTitle) {
					int allRecordsCount = titleDao.getAllRecordsCount();
					response.setTotalCount(allRecordsCount);
					response.setStatusMessage("Success fully Education record saved");
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					response.setStatusMessage(FAILED);
					return new ResponseEntity<>(response, HttpStatus.CONFLICT);
				}
			}

			if (request.getTransactionType().equalsIgnoreCase(UPDATE)) {
				response = new Response();
				boolean updateTitle = titleDao.updateTitle(request);
				if (updateTitle) {
					response.setStatusMessage("Successfully Education record updated");
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					response.setStatusMessage(FAILED);
					return new ResponseEntity<>(response, HttpStatus.CONFLICT);
				}
			}

			if (request.getTransactionType().equalsIgnoreCase(DELETE)) {
				response = new Response();
				boolean deleteId = false;
				List<Model> modellist = request.getModel();
				for (Model model : modellist) {
					Integer id = model.getId();
					logger.debug("inside  delete condition.****** : ");
					deleteId = titleDao.deleteEmployeeRecord(id);
				}
				if (deleteId) {
					response.setStatusMessage("Success fully Education record deleted");
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					response.setStatusMessage(FAILED);
					return new ResponseEntity<>(response, HttpStatus.CONFLICT);
				}
			}

		} catch (Exception e) {
			logger.debug("inside EmployeeEducationFacde catch block.****");
			ErrorResponse error = new ErrorResponse();
			logger.debug("data is  invalid");
			error.setMessage(e.getMessage());
		}
		return null;

	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public ResponseEntity<Object> getTitle(Request request) throws SQLException {
//		Response response = new Response();
//		logger.debug("inside getAllEmployeeBU in EmployeeBUFacde.***");
//		
//		List<Model> allTitleDetails = titleDao.getAllTitle(request);
//		if (null == allTitleDetails || allTitleDetails.isEmpty()) {
//			response.setListCourse(new ArrayList<>());
//			response.setStatusMessage(FAILED);
//			response.setTotalCount(0);
//		} else {
//			int recordsCount = titleDao.getAllRecordsCount();
//			response.setStatusMessage(SUCCESS);
//			response.setListCourse(allTitleDetails);
//			response.setTotalCount(recordsCount);
//		}
//		
//		return new ResponseEntity<>(response, HttpStatus.OK);
		Response response = null;
		logger.debug("inside  getall condition.****** : " + request);
		if (request.getTransactionType().equalsIgnoreCase(GETALL)) {
			logger.debug("inside  get condition.****** : ");
			response = new Response();
			int totalCount = titleDao.getAllRecordsCount();
			List<Model> allCertificationDetails = titleDao.getAllTitle(request);

			if (allCertificationDetails == null || allCertificationDetails.isEmpty()) {
				response.setListCourse(new ArrayList<>());
				response.setStatusMessage("No records found");
				response.setTotalCount(0);
				return new ResponseEntity<>(response, HttpStatus.CONFLICT);
			} else {
				if (request.getPageNo() == 0 || request.getPageSize() == 0) {
					response.setStatusMessage(SUCCESS);
					response.setListCourse(allCertificationDetails);
					response.setTotalCount(totalCount);
				} else {
					response.setStatusMessage(SUCCESS);
					response.setTotalCount(totalCount);
				}
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}

		if (request.getTransactionType().equalsIgnoreCase(GETBYID)) {
			response = new Response();
			List<Model> list = titleDao.getById(request);
			logger.debug("inside  get_count condition.****** : ");
			if (list.size() == 0) {
				response.setStatusMessage("No record Present");
				response.setTotalCount(0);
				return new ResponseEntity<>(response, HttpStatus.CONFLICT);
			} else {
				response.setStatusMessage(SUCCESS);
				response.setListCourse(list);
				return new ResponseEntity<>(response, HttpStatus.OK);
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
	public List<Model> recordsPerPage(List<Model> allEmployeeDetails, int pageSize, int pageNo) {
		List<Model> getAllFiltered = new ArrayList<>();
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
