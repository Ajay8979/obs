package com.ojas.obs.employmentdetails.facade;

import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.DELETE;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.GETALL;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.SAVE;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.UPDATE;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ojas.obs.employmentdetails.controller.EmploymentDetailsController;
import com.ojas.obs.employmentdetails.dao.EmploymentDetailsDAO;
import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.exception.EmploymentDetailsException;
import com.ojas.obs.employmentdetails.model.EmploymentDetails;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsResponse;

/**
 * service class inserts,updates,deletes and retrieves data into/from
 * employment_details table based on transaction type
 * 
 * @author vjithin
 *
 */
@Service
public class EmploymentDetailsFacadeImpl implements EmploymentDetailsFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentDetailsController.class);

	@Autowired
	private EmploymentDetailsDAO employmentDetailsDAO;

	/**
	 * method inserts,updates,deletes data into employment_details table based on
	 * transaction type
	 * 
	 * @throws EmploymentDetailsException
	 * @throws DataNotInsertedException
	 */
	@Override
	public EmploymentDetailsResponse saveEmploymentDetails(EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException, DataNotInsertedException {
		EmploymentDetailsResponse employmentDetailsResponse = new EmploymentDetailsResponse();

		LOGGER.debug("the requested object is" + employmentDetailsRequest);

		if (StringUtils.isEmpty(employmentDetailsRequest.getTransactionType())
				|| CollectionUtils.isEmpty(employmentDetailsRequest.getEmploymentDetails())) {
			LOGGER.error("the requested object has null values" + employmentDetailsRequest);
			throw new EmploymentDetailsException("Requested object has null values");
		}
		if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
			LOGGER.debug("the response object is" + employmentDetailsRequest);
			return insertEmploymentDetails(employmentDetailsRequest.getEmploymentDetails(), employmentDetailsResponse);
		} else if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
			LOGGER.debug("the response object is" + employmentDetailsRequest);
			return updateEmploymentDetails(employmentDetailsRequest.getEmploymentDetails(), employmentDetailsResponse);
		} else if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
			LOGGER.debug("the response object is" + employmentDetailsRequest);
			return deleteEmploymentDetails(employmentDetailsRequest.getEmploymentDetails(), employmentDetailsResponse);
		}

		employmentDetailsResponse.setStatusCode("400");
		employmentDetailsResponse.setStatusMessage("Requested transaction type is wrong");
		LOGGER.debug("the response object is" + employmentDetailsRequest);

		return employmentDetailsResponse;
	}

	/**
	 * method deletes data from employment_details table
	 * 
	 * @param employmentDetails
	 * @param employementDetailsResponse
	 * @return
	 * @throws EmploymentDetailsException
	 * @throws DataNotInsertedException
	 */
	private EmploymentDetailsResponse deleteEmploymentDetails(List<EmploymentDetails> employmentDetailsList,
			EmploymentDetailsResponse employmentDetailsResponse)
			throws EmploymentDetailsException, DataNotInsertedException {
		employmentDetailsResponse.setStatusCode("201");
		employmentDetailsResponse.setStatusMessage("Data is not deleted");

		if (employmentDetailsDAO.deleteEmploymentDetails(employmentDetailsList)) {
			employmentDetailsResponse.setStatusCode("201");
			employmentDetailsResponse.setStatusMessage("Data is deleted successfully");
		}
		return employmentDetailsResponse;
	}

	/**
	 * method updates data into employment_details table
	 * 
	 * @param employmentDetails
	 * @param employementDetailsResponse
	 * @return
	 * @throws EmploymentDetailsException
	 * @throws DataNotInsertedException
	 */
	private EmploymentDetailsResponse updateEmploymentDetails(List<EmploymentDetails> employmentDetailsList,
			EmploymentDetailsResponse employmentDetailsResponse)
			throws EmploymentDetailsException, DataNotInsertedException {
		employmentDetailsResponse.setStatusCode("201");
		employmentDetailsResponse.setStatusMessage("Data is not updated");

		if (employmentDetailsDAO.updateEmploymentDetails(employmentDetailsList)) {
			employmentDetailsResponse.setStatusCode("201");
			employmentDetailsResponse.setStatusMessage("Data is updated successfully");
		}
		return employmentDetailsResponse;
	}

	/**
	 * method inserts data into employment_details table
	 * 
	 * @param employmentDetails
	 * @param employementDetailsResponse
	 * @return
	 * @throws EmploymentDetailsException
	 * @throws DataNotInsertedException
	 */
	private EmploymentDetailsResponse insertEmploymentDetails(List<EmploymentDetails> employmentDetailsList,
			EmploymentDetailsResponse employmentDetailsResponse)
			throws EmploymentDetailsException, DataNotInsertedException {
		employmentDetailsResponse.setStatusCode("400");
		employmentDetailsResponse.setStatusMessage("Data is not inserted successfully");

		if (employmentDetailsDAO.saveEmploymentDetails(employmentDetailsList)) {
			employmentDetailsResponse.setStatusCode("201");
			employmentDetailsResponse.setStatusMessage("Data is inserted successfully");
		}
		return employmentDetailsResponse;

	}

	/**
	 * method retrieves data from employment_details table
	 */
	@Override
	public EmploymentDetailsResponse viewEmploymentDetails(EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException {

		EmploymentDetailsResponse employmentDetailsResponse = new EmploymentDetailsResponse();
		employmentDetailsResponse.setStatusCode("204");
		employmentDetailsResponse.setStatusMessage("Employee details not found");

		if (StringUtils.isEmpty(employmentDetailsRequest.getTransactionType())) {
			LOGGER.error("the requested object has null values" + employmentDetailsRequest);
			throw new EmploymentDetailsException("Requested object has null values");
		}
		List<EmploymentDetails> employmentDetailsList = null;
		if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
			if (null != employmentDetailsRequest.getEmploymentDetails() &&  null != employmentDetailsRequest.getEmploymentDetails().get(0) && null != employmentDetailsRequest.getEmploymentDetails().get(0).getId()) {
				employmentDetailsList = employmentDetailsDAO
						.getEmploymentDetailsById(employmentDetailsRequest.getEmploymentDetails().get(0).getId());
			} else if (null != employmentDetailsRequest.getEmploymentDetails() &&  null != employmentDetailsRequest.getEmploymentDetails().get(0) && null != employmentDetailsRequest.getEmploymentDetails().get(0).getEmployeeId()) {
				employmentDetailsList = employmentDetailsDAO.getEmploymentDetailsByEmploymentId(
						employmentDetailsRequest.getEmploymentDetails().get(0).getEmployeeId());
			} else {
				employmentDetailsList = employmentDetailsDAO.getAllEmploymentDetails();
			}
		}

		if (!CollectionUtils.isEmpty(employmentDetailsList)) {
			employmentDetailsResponse.setEmploymentDetailsList(employmentDetailsList);
			employmentDetailsResponse.setStatusCode("200");
			employmentDetailsResponse.setStatusMessage("Employee details found");
		}
		LOGGER.debug("the response object is" + employmentDetailsResponse);
		return employmentDetailsResponse;
	}

}
