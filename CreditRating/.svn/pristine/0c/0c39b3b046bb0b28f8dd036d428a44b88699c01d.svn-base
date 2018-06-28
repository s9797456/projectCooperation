package com.credit.service.enterprise.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.ProcessState;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.ProcessStateService;


@Service
@Transactional
public class ProcessStateServiceBean extends DaoSupport<ProcessState> implements ProcessStateService {


	public void deleteCompletely(String uuid) {
		ProcessState process=this.find(uuid);
		process.setEntBaseInfo(null);
		this.update(process);
		this.delete(uuid);
	}

}
