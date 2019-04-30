package com.ojas.obs.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojas.obs.dao.DependentDetailsDao;
import com.ojas.obs.daoImpl.DependentDetailsDaoImpl;
import com.ojas.obs.model.DependentDetails;
import com.ojas.obs.request.DependentDetailsRequest;
import com.ojas.obs.response.DependentDetailsResponse;
import com.ojas.obs.service.DependentDetailsService;

import static com.ojas.obs.constants.DependentDetailsContants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author srinukummari
 *
 */
@Component
public class DependentDetailsServiceImpl implements DependentDetailsService {
	Logger logger = Logger.getLogger(this.getClass());
	DependentDetailsResponse dependentDetailsResponse = null; 
	@Autowired
	DependentDetailsDao dependentDetailsDaoImpl;
	
	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.service.DependentDetailsService#setDependentDetails(com.ojas.obs.request.DependentDetailsRequest)
	 */
	@Override
	public DependentDetailsResponse setDependentDetails(
			DependentDetailsRequest dependentDetailsRequestObject)throws SQLException {
		List<DependentDetails> dependentDetails = dependentDetailsRequestObject.getDependentDetails();
		dependentDetailsResponse = new DependentDetailsResponse();
		if (dependentDetailsRequestObject.getTransactionType().equalsIgnoreCase(SAVE)) {
			boolean b=false;
			for(DependentDetails details : dependentDetails) {
			if (((details.getDependent_Name() == null) 
					|| (details.getDependent_Name().equalsIgnoreCase("")))
					|| ((details.getRelation() == null)
					|| (details.getRelation().equalsIgnoreCase("")))
					|| ((details.getDate_Of_Birth() == null))
					|| ((details.getEmployee_Id() == null||details.getEmployee_Id().isEmpty())
					|| ((details.getCreated_By() == null||details.getCreated_By().isEmpty())))) {
                 
				dependentDetailsResponse.setStatusCode("422");
				dependentDetailsResponse.setStatusMessage("Improper data");
				b=true; 
				break;
			}
			}
			if(b) {}
			else {
				int saveDependentDetails = dependentDetailsDaoImpl.saveDependentDetails(dependentDetailsRequestObject);
				logger.debug("received at service by calling the saveDependentDetails is" + saveDependentDetails);
				if (saveDependentDetails != 0) {
					dependentDetailsResponse.setStatusCode("200");
					dependentDetailsResponse.setStatusMessage("DependentDetails have saved successfully");
				}
			}
			return dependentDetailsResponse;
		}
		
		
	
		else if (dependentDetailsRequestObject.getTransactionType().equalsIgnoreCase(UPDATE)) {
			boolean b = false;
			for(DependentDetails details : dependentDetails) {
					if (0 != details.getId()) {
						if (((details.getDependent_Name() == null) || (details.getDependent_Name().equalsIgnoreCase("")))
								|| ((details.getRelation() == null)|| (details.getRelation().equalsIgnoreCase("")))
								|| ((details.getDate_Of_Birth() == null))|| ((details.getEmployee_Id() == null||details.getEmployee_Id().isEmpty())
								|| ((details.getUpdated_By() == null ||details.getUpdated_By().isEmpty())))) {
	
								dependentDetailsResponse.setStatusCode("422");
								dependentDetailsResponse.setStatusMessage("Improper data");
								b=true;
								
						}
					}
			}
			if(b) {}
			else {
				int updateDependentDetails = dependentDetailsDaoImpl.updateDependentDetails(dependentDetailsRequestObject);
				dependentDetailsResponse.setStatusCode("409");
				dependentDetailsResponse.setStatusMessage("sorry DependentDetails are not updated");
				logger.debug("received at service by calling the updateDependentDetails is" + updateDependentDetails);
						if (updateDependentDetails != 0) {
							dependentDetailsResponse.setStatusCode("200");
							dependentDetailsResponse.setStatusMessage("DependentDetails are updated successfully");
						}
			}
				
			return dependentDetailsResponse;
		}
		
		else if (dependentDetailsRequestObject.getTransactionType().equalsIgnoreCase(DELETE)) {
			boolean b = false;
			for(DependentDetails details : dependentDetails) {
				if (0 == details.getId()) {
					dependentDetailsResponse.setStatusCode("422");
					dependentDetailsResponse.setStatusMessage("please provide the id");
					b= true;

				}
			}
			if(b) {}
			else {
					int deleteDependentDetails = dependentDetailsDaoImpl.deleteDependentDetails(dependentDetailsRequestObject);
					dependentDetailsResponse.setStatusCode("409");
					dependentDetailsResponse.setStatusMessage("sorry DependentDetails are not updated");
					logger.debug("received at service by calling the deleteDependentDetails is" + deleteDependentDetails);
						if (deleteDependentDetails != 0) {
							dependentDetailsResponse.setStatusCode("200");
							dependentDetailsResponse.setStatusMessage("DependentDetails are deleted successfully");
						}
				}
			}	
			else {
				dependentDetailsResponse.setStatusMessage("please provide the id");
			}	
			return dependentDetailsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ojas.obs.service.DependentDetailsService#getDependentDetails(com.ojas.obs.request.DependentDetailsRequest)
	 */
	@Override
	public DependentDetailsResponse getDependentDetails(DependentDetailsRequest dependentDetailsRequestObject)
			throws SQLException {
		dependentDetailsResponse = new DependentDetailsResponse();
		if (dependentDetailsRequestObject.getTransactionType().equalsIgnoreCase(GETALL)) {
			List<DependentDetails> allDependentDetails = dependentDetailsDaoImpl.getAll(dependentDetailsRequestObject);
			logger.debug("received at service by calling the getAllmethod is" + allDependentDetails);
			if (!allDependentDetails.isEmpty()) {
				dependentDetailsResponse.setStatusCode("200");
				dependentDetailsResponse.setStatusMessage("you got the list of DependentDetails successfully");
				dependentDetailsResponse.setGetDependentDetailsList(allDependentDetails);;
			} else {
				dependentDetailsResponse.setGetDependentDetailsList(allDependentDetails);
				dependentDetailsResponse.setStatusCode("200");
				dependentDetailsResponse.setStatusMessage("no records found");
			}
		}
		
		else if (dependentDetailsRequestObject.getTransactionType().equalsIgnoreCase(GETBYID)) {
			List<DependentDetails> all = dependentDetailsDaoImpl.getById(dependentDetailsRequestObject.getDependentDetails().get(0).getId());
			logger.debug("received at service by calling the getAllmethod is" + all);
			if (!all.isEmpty()) {
				dependentDetailsResponse.setStatusCode("200");
				dependentDetailsResponse.setStatusMessage("you got the list of DependentDetails successfully");
				dependentDetailsResponse.setGetDependentDetailsList(all);;
			} else {
				dependentDetailsResponse.setGetDependentDetailsList(all);
				dependentDetailsResponse.setStatusCode("200");
				dependentDetailsResponse.setStatusMessage("no records found");
			}
		}
		
		else {
			dependentDetailsResponse.setStatusCode("422");
			dependentDetailsResponse.setStatusMessage("please provide valid transaction type");
		}
		
		return dependentDetailsResponse;
	}


	
}
