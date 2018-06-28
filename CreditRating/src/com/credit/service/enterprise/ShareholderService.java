package com.credit.service.enterprise;

import java.util.List;

import com.credit.bean.enterprise.Shareholder;
import com.credit.dao.DAO;

public interface ShareholderService extends DAO<Shareholder> {

	List<Shareholder> findAllByEntID(String entid);
	
	/*
	 * 根据企业ID获取Executives集合，根据size判断用户是否填写该内容
	 */
	int exsit(String entId);
}
