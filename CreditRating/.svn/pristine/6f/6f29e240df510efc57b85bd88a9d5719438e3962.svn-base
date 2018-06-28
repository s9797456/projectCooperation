package com.credit.service.privilege.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.privilege.SystemPrivilege;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.SystemPrivilegeService;

@Service
@Transactional
public class SystemPrivilegeServiceBean extends DaoSupport<SystemPrivilege>
		implements SystemPrivilegeService {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(SystemPrivilegeServiceBean.class);

	public void batchSave(List<SystemPrivilege> privileges) {
		logger.info("SystemPrivilegeService_batchSave;privileges:" + privileges);
		for (SystemPrivilege privilege : privileges) {
			save(privilege);
		}
	}

	public void batchDelete(List<SystemPrivilege> privileges) {
		logger.info("SystemPrivilegeService_batchDelete;privileges:"
				+ privileges);
		for (SystemPrivilege privilege : privileges) {
			delete(privilege.getId());
		}
	}

	public List<SystemPrivilege> allSPs() {
		logger.info("SystemPrivilegeService_allSPs;");
		return getScrollData().getResultlist();
	}

	public List<SystemPrivilege> allSPsOrderByModel() {
		logger.info("SystemPrivilegeService_allSPsOrderByModel;");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id.model", "desc");
		return getScrollData(-1, -1, null, null, orderby).getResultlist();
	}

}
