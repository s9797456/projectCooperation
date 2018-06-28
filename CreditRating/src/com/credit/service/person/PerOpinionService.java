package com.credit.service.person;


import java.util.Map;

import com.credit.bean.person.PerOpinion;
import com.credit.dao.DAO;

public interface PerOpinionService extends DAO<PerOpinion> {

	/**
	 * @title 新增或更新管理员意见
	 * @author  孙尚飞  2017-8-14
	 * @desc
	 */
	public void UpdateOrAdd(PerOpinion opinion, String dealStatus, String perID,
			Map<String, String> searchParams);

	
}
