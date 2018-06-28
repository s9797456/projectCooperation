package com.credit.service.privilege;

import com.credit.bean.privilege.Organization;
import com.credit.dao.DAO;


public interface OrganizationService extends DAO<Organization> {

	boolean exist(String string, String trim);
	
	boolean exist(String string, String trim, String uuid);

	void updateVisible(String uuid, int visible);
	
}
