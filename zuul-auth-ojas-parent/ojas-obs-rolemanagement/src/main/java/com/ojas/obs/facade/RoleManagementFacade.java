package com.ojas.obs.facade;

import static com.ojas.obs.constants.RoleServiceConstants.FAILED;
import static com.ojas.obs.constants.RoleServiceConstants.SUCCESS;
import static com.ojas.obs.constants.RoleServiceConstants.SAVE;
import static com.ojas.obs.constants.RoleServiceConstants.UPDATE;
import static com.ojas.obs.constants.RoleServiceConstants.GETALL;
import static com.ojas.obs.constants.RoleServiceConstants.GETBYID;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.obs.dao.RoleManagementDao;
import com.ojas.obs.error.ErrorResponse;
import com.ojas.obs.model.RoleManagement;
import com.ojas.obs.request.RoleManagementRequest;
import com.ojas.obs.response.RoleManagementResponse;

/**
 * 
 * @author asuneel
 *
 */

@Service
public class RoleManagementFacade {
	@Autowired
	RoleManagementDao roleManagementDao;
	Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @param roleManagementRequest
	 * @return
	 */

	public ResponseEntity<Object> setRoleManagement(RoleManagementRequest roleManagementRequest) {

		logger.info("inside setRoleManagement method : " + roleManagementRequest);
		RoleManagementResponse roleManagementResponse = new RoleManagementResponse();

		try {

			if (roleManagementRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
				boolean saveRole = roleManagementDao.saveRoleManagement(roleManagementRequest);
				logger.debug("inside  save condition.****** : " + saveRole);
				if (saveRole) {
					roleManagementResponse.setStatusMessage("Successfully Role Management record saved");
					return new ResponseEntity<>(roleManagementResponse, HttpStatus.OK);
				} else {
					ErrorResponse error = new ErrorResponse();
					error.setMessage(FAILED);
					return new ResponseEntity<>(error, HttpStatus.CONFLICT);
				}

			}
			if (roleManagementRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
				boolean updateRole = roleManagementDao.updateRoleManagement(roleManagementRequest);
				logger.debug("inside  update condition.****** : " + updateRole);

				if (updateRole) {
					roleManagementResponse.setStatusMessage("Success fully record updated");
					return new ResponseEntity<>(roleManagementResponse, HttpStatus.OK);
				} else {
					ErrorResponse error = new ErrorResponse();
					error.setMessage(FAILED);
					return new ResponseEntity<>(error, HttpStatus.CONFLICT);
				}

			}
		}catch (DuplicateKeyException exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getCause().getLocalizedMessage());
			logger.error("data is  invalid. DuplicateKeyException");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		catch (Exception exception) {
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			logger.error("data is  invalid");
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(null, HttpStatus.CONFLICT);

	}

	/**
	 * 
	 * @param roleManagementRequest
	 * @return
	 * @throws SQLException 
	 */

	public ResponseEntity<Object> getRoleManagement(RoleManagementRequest roleManagementRequest) throws SQLException {

		
		RoleManagementResponse roleManagementResponse = new RoleManagementResponse();
		logger.info("inside getRoleManagement in RoleManagementFacade.***");

		try {
			List<RoleManagement> getAllRoleManagements = null;
			if (roleManagementRequest.getTransactionType().equalsIgnoreCase(GETBYID)) {
				Integer id = roleManagementRequest.getRoleManagement().get(0).getId();
			 getAllRoleManagements = roleManagementDao.getByIdRollManagement(id);
			}
			if (roleManagementRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
				 getAllRoleManagements = roleManagementDao.getAllRollManagements();
				}
			if (getAllRoleManagements == null || getAllRoleManagements.isEmpty()) {
				roleManagementResponse.setStatusMessage(FAILED);
				roleManagementResponse.setRoleManagementList(getAllRoleManagements);
				return new ResponseEntity<>(roleManagementResponse, HttpStatus.CONFLICT);
			} else {
				roleManagementResponse.setStatusMessage(SUCCESS);
				roleManagementResponse.setRoleManagementList(getAllRoleManagements);
				return new ResponseEntity<>(roleManagementResponse, HttpStatus.OK);
			}
		} catch (Exception exception) {
			logger.error("data is  invalid");
			ErrorResponse error = new ErrorResponse();
			error.setMessage(exception.getMessage());
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
	}

}
