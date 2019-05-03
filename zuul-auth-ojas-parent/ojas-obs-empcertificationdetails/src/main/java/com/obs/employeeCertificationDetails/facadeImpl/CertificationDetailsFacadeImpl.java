package com.obs.employeeCertificationDetails.facadeImpl;


import static com.obs.employeeCertificationDetails.constants.Constants.DELETE;
import static com.obs.employeeCertificationDetails.constants.Constants.FAILED;
import static com.obs.employeeCertificationDetails.constants.Constants.GET;
import static com.obs.employeeCertificationDetails.constants.Constants.GET_COUNT;
import static com.obs.employeeCertificationDetails.constants.Constants.SAVE;
import static com.obs.employeeCertificationDetails.constants.Constants.SUCCESS;
import static com.obs.employeeCertificationDetails.constants.Constants.UPDATE;
import static com.obs.employeeCertificationDetails.constants.Constants.GETBYID;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.obs.employeeCertificationDetails.dao.EmployeeCertificationDAO;
import com.obs.employeeCertificationDetails.facade.CertificationDetailsFacade;
import com.obs.employeeCertificationDetails.model.CertificationDetails;
import com.obs.employeeCertificationDetails.request.CertificationDetailsRequest;
import com.obs.employeeCertificationDetails.response.CertificationDetailsResponse;


@Service
public class CertificationDetailsFacadeImpl implements CertificationDetailsFacade{
	@Autowired
	private EmployeeCertificationDAO employeeCertificationDAOImpl;
    Logger logger = Logger.getLogger(this.getClass());
    
	@Override
	public ResponseEntity<Object> setCertificationDetails(CertificationDetailsRequest certificationDetailsRequest) throws SQLException{
		logger.debug("inside set method : " + certificationDetailsRequest);
		CertificationDetailsResponse certificationDetailsResponse = null;
		if(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
			certificationDetailsResponse = new CertificationDetailsResponse();
			boolean saveCertificationDetails = employeeCertificationDAOImpl.saveCertificationDetails(certificationDetailsRequest);
			logger.debug("inside  save condition.****** : " + saveCertificationDetails);
			if(saveCertificationDetails) {
				int allRecordsCount = employeeCertificationDAOImpl.getAllCertificationDetailsCount();
				certificationDetailsResponse.setTotalCount(allRecordsCount);
				certificationDetailsResponse.setStatusMassage("Employee Certification detail saved successfuly");
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.CONFLICT);
			}
		}
		if(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
			certificationDetailsResponse = new CertificationDetailsResponse();
			boolean updateCertificationDetails = employeeCertificationDAOImpl.updateCertificationDetails(certificationDetailsRequest);
			logger.debug("inside  update condition.****** : " + updateCertificationDetails);
				if(updateCertificationDetails) {
					int allRecordsCount = employeeCertificationDAOImpl.getAllCertificationDetailsCount();
					certificationDetailsResponse.setTotalCount(allRecordsCount);
					certificationDetailsResponse.setStatusMassage("Employee Certification detail updated successfuly");
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.OK);
			    }else {
				certificationDetailsResponse.setStatusMassage(FAILED);
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.CONFLICT);
			}
			
		}
		if(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
			certificationDetailsResponse = new CertificationDetailsResponse();
			boolean deleteCertificationDetails = employeeCertificationDAOImpl.deleteCertificationDetails(certificationDetailsRequest);
			logger.debug("inside  update condition.****** : " + deleteCertificationDetails);
			if(deleteCertificationDetails) {
				int allRecordsCount = employeeCertificationDAOImpl.getAllCertificationDetailsCount();
				certificationDetailsResponse.setTotalCount(allRecordsCount);
				certificationDetailsResponse.setStatusMassage("Employee Certification detail deleted successfuly");
			    return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.OK);
		    }else {
			certificationDetailsResponse.setStatusMassage(FAILED);
			return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.CONFLICT);
		    }
		}
		boolean b = (certificationDetailsRequest.getTransactionType().equalsIgnoreCase(SAVE)
				|| certificationDetailsRequest.getTransactionType().equalsIgnoreCase(UPDATE) || certificationDetailsRequest.getTransactionType().equalsIgnoreCase(GETBYID));
				if (!(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(DELETE) || b)) {
					logger.debug("inside  transactionCheck condition.****** : ");
					certificationDetailsResponse = new CertificationDetailsResponse();
					certificationDetailsResponse.setStatusCode("422");
					certificationDetailsResponse.setStatusMassage("transaction type is not correct");
					return new ResponseEntity<>(certificationDetailsResponse, HttpStatus.CONFLICT);
				}
				return new ResponseEntity<>(certificationDetailsResponse, HttpStatus.CONFLICT);
				
	}
	
	

	@Override
	public ResponseEntity<Object> getCertificationDetails(CertificationDetailsRequest certificationDetailsRequest) throws SQLException{
		CertificationDetailsResponse certificationDetailsResponse = null;
		logger.debug("inside  update condition.****** : " + certificationDetailsRequest);
		if(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(GET)) {
			logger.debug("inside  get condition.****** : ");
			certificationDetailsResponse = new CertificationDetailsResponse();
			int totalCount=employeeCertificationDAOImpl.getAllCertificationDetailsCount();
			List<CertificationDetails> allCertificationDetails=employeeCertificationDAOImpl.getAllCertificationDetails();
			List<CertificationDetails> recordPerPageList=employeeCertificationDAOImpl.getCountPerPage(allCertificationDetails,certificationDetailsRequest.getPageSize(),certificationDetailsRequest.getPageNo());
			
			if(allCertificationDetails==null || allCertificationDetails.isEmpty()) {
				certificationDetailsResponse.setCertificationDetailsList(new ArrayList<>());
				certificationDetailsResponse.setStatusMassage("No records found");
				certificationDetailsResponse.setTotalCount(0);
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.CONFLICT);
			}else {
				if(certificationDetailsRequest.getPageNo()==0 || certificationDetailsRequest.getPageSize()==0) {
				certificationDetailsResponse.setStatusMassage(SUCCESS);
				certificationDetailsResponse.setCertificationDetailsList(allCertificationDetails);
				certificationDetailsResponse.setTotalCount(totalCount);
				}else {
					certificationDetailsResponse.setStatusMassage(SUCCESS);
					certificationDetailsResponse.setCertificationDetailsList(recordPerPageList);
					certificationDetailsResponse.setTotalCount(totalCount);
				}
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.OK);
			}
		}
		if(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(GET_COUNT)) {
			certificationDetailsResponse = new CertificationDetailsResponse();
			int count=employeeCertificationDAOImpl.getAllCertificationDetailsCount();
			logger.debug("inside  get_count condition.****** : ");
			if(count==0) {
				certificationDetailsResponse.setStatusMassage(FAILED);
				certificationDetailsResponse.setTotalCount(0);
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.CONFLICT);
			}else {
				certificationDetailsResponse.setStatusMassage(SUCCESS);
				certificationDetailsResponse.setTotalCount(count);
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.OK);
			}
		}
		if(certificationDetailsRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
			certificationDetailsResponse = new CertificationDetailsResponse();
			List<CertificationDetails> list=null;
			String empId=certificationDetailsRequest.getCertificationDetailsModel().get(0).getEmployee_id();
			Integer id=certificationDetailsRequest.getCertificationDetailsModel().get(0).getId();
			if(id!=null)
				list=employeeCertificationDAOImpl.getDetailById(certificationDetailsRequest);
			else if(empId!=null)
				list=employeeCertificationDAOImpl.getDetailByEmpId(certificationDetailsRequest);
			
			logger.debug("inside  get_count condition.****** : ");
			if(list.size()==0) {
				certificationDetailsResponse.setStatusMassage("No record Present");
				certificationDetailsResponse.setTotalCount(0);
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.CONFLICT);
			}else {
				certificationDetailsResponse.setStatusMassage(SUCCESS);
				certificationDetailsResponse.setStatusCode("200");
				certificationDetailsResponse.setCertificationDetailsList(list);
				return new ResponseEntity<>(certificationDetailsResponse,HttpStatus.OK);
			}
		}
		return null;
	}
    

	

}
