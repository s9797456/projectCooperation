package com.credit.service.enterprise;


import com.credit.bean.enterprise.ProcessState;
import com.credit.dao.DAO;

public interface ProcessStateService extends DAO<ProcessState> {
	/**
	 * @title 级联删除数据
	 * @author  孙尚飞  2017-8-24
	 * @desc
	 */
	public void deleteCompletely(String uuid);
	
}
