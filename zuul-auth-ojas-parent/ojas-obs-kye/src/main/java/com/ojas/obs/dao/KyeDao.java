package com.ojas.obs.dao;

import java.util.List;

import com.ojas.obs.model.KYE;
import com.ojas.obs.request.KYERequest;
/**
 * 
 * @author tshiva
 *
 */
public interface KyeDao {
	
	boolean saveKYE(KYERequest kyeRequest);

	boolean updateKYE(KYERequest kyeRequest);

	boolean deleteKYE(KYERequest kyeRequest);

	List<KYE> getAllKYE(KYERequest kyeRequest);

	int getAllKYECount();
	
	List<KYE> getCountPerPage(List<KYE> list, int pageSize, int pageNo);
}