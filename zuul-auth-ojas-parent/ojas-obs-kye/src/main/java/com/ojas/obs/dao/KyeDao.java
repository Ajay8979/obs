package com.ojas.obs.dao;

import java.sql.SQLException;
import java.util.List;

import com.ojas.obs.model.KYE;
import com.ojas.obs.request.KYERequest;
/**
 * 
 * @author tshiva
 *
 */
public interface KyeDao {
	
	boolean saveKYE(KYERequest kyeRequest) throws SQLException;

	boolean updateKYE(KYERequest kyeRequest) throws SQLException;

	boolean deleteKYE(KYERequest kyeRequest) throws SQLException;

	List<KYE> getAllKYE(KYERequest kyeRequest) throws SQLException;

	int getAllKYECount()throws SQLException;
	
	List<KYE> getCountPerPage(List<KYE> list, int pageSize, int pageNo)throws SQLException;
}