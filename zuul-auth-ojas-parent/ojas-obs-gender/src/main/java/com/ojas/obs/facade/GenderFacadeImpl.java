package com.ojas.obs.facade;
import static com.ojas.obs.constants.Constants.FAILED;
import static com.ojas.obs.constants.Constants.SAVE;
import static com.ojas.obs.constants.Constants.UPDATE;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ojas.obs.dao.GenderDAO;
import com.ojas.obs.model.Genders;
import com.ojas.obs.request.GenderRequest;
import com.ojas.obs.response.GenderResponse;


@Service
public class GenderFacadeImpl implements GenderFacade {
    @Autowired
	private GenderDAO genderDAOImpl;
    Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * this method will call insert , update and delete methods of dao class based upon the transaction type
     */
	@Override
	public ResponseEntity<Object> setGender(GenderRequest genderRequest) throws SQLException {
		logger.debug("inside set method : " + genderRequest);
		GenderResponse genderResponse = null;
		
			if (genderRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				genderResponse = new GenderResponse();
				boolean saveGender = genderDAOImpl.saveGender(genderRequest);
				int count = genderDAOImpl.getAllCount(genderRequest);
				logger.debug("inside  save condition.****** : " + saveGender);
				if (saveGender) {
					genderResponse.setStatusMessage("Successfully record added");
					genderResponse.setTotalCount(count);
					return new ResponseEntity<>(genderResponse, HttpStatus.OK);
				} else {
					genderResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(genderResponse, HttpStatus.CONFLICT);
				}
			}
			if (genderRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				genderResponse = new GenderResponse();
				int count = genderDAOImpl.getAllCount(genderRequest);
				boolean updateGender = genderDAOImpl.updateGender(genderRequest);
				logger.debug("inside  update condition.****** : " + updateGender);
				if (updateGender) {
					genderResponse.setStatusMessage("Successfully record updated");
					genderResponse.setTotalCount(count);
					return new ResponseEntity<>(genderResponse, HttpStatus.OK);
				} else {
					genderResponse.setStatusMessage(FAILED);
					return new ResponseEntity<>(genderResponse, HttpStatus.CONFLICT);
				}
			}
		/*
		 * if (genderRequest.getTransactionType().equalsIgnoreCase(DELETE)) {
		 * genderResponse = new GenderResponse(); int count =
		 * genderDAOImpl.getAllCount(genderRequest); for(Genders
		 * gender:genderRequest.getGender()) { Integer id = gender.getId(); boolean
		 * deleteGender = genderDAOImpl.deleteGender(genderRequest);
		 * logger.debug("inside  delete condition.****** and id is : " + id); if
		 * (deleteGender) {
		 * genderResponse.setStatusMessage("Successfully record deleted");
		 * genderResponse.setTotalCount(count); return new
		 * ResponseEntity<>(genderResponse, HttpStatus.OK); } else {
		 * genderResponse.setStatusMessage(FAILED); return new
		 * ResponseEntity<>(genderResponse, HttpStatus.CONFLICT); } } }
		 */
			boolean b = (genderRequest.getTransactionType().equalsIgnoreCase(SAVE)
					|| genderRequest.getTransactionType().equalsIgnoreCase(UPDATE));
					if (!b) {
						genderResponse = new GenderResponse();
						genderResponse.setStatusCode("422");
						genderResponse.setStatusMessage("transaction type is not correct");
					}
					return new ResponseEntity<>(genderResponse, HttpStatus.CONFLICT);
		
	}
	 
    /**
     * this method will call select , selectAll methods of dao class based upon the transaction type
     */
	@Override
	public ResponseEntity<Object> getGender(GenderRequest genderRequest) throws SQLException {
		GenderResponse genderResponse = new GenderResponse();
		logger.debug("inside get in DesignationServiceImpl.***");
		List<Genders> genderList = genderDAOImpl.getAll(genderRequest);
		List<Genders> list=genderDAOImpl.getCountPerPage(genderList, genderRequest.getPageSize(), genderRequest.getPageNo());
		int totalCount = genderDAOImpl.getAllCount(genderRequest);
				genderRequest.getPageNo();
		if ( genderList == null) {    //removed condition    list == null ||
			genderResponse.setGenderList(new ArrayList<>());
			genderResponse.setStatusMessage("No records found");
			genderResponse.setTotalCount(0);
			genderResponse.setStatusCode("422");
			
		} else {

			if (genderRequest.getPageNo() == 0 || genderRequest.getPageSize() == 0) {
				genderResponse.setGenderList(genderList);
				genderResponse.setStatusMessage("success");
				genderResponse.setTotalCount(totalCount);
			} else {
				genderResponse.setGenderList(list);
				genderResponse.setStatusMessage("success");
				genderResponse.setTotalCount(totalCount);
			}
		}
		return new ResponseEntity<Object>(genderResponse, HttpStatus.OK);
	}
}
