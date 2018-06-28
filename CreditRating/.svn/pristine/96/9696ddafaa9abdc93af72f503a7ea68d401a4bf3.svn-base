package com.credit.service.person.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.person.PerProcess;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.person.PerProcessService;


@Service
@Transactional
public class PerProcessServiceBean extends DaoSupport<PerProcess> implements PerProcessService {


	public void deleteCompletely(String uuid) {
		PerProcess process=this.find(uuid);
		process.setPerBaseInfo(null);
		this.update(process);
		this.delete(uuid);
	}

}
