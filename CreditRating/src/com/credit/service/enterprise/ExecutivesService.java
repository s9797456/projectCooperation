package com.credit.service.enterprise;

import java.util.List;

import com.credit.bean.enterprise.Executives;
import com.credit.dao.DAO;

public interface ExecutivesService extends DAO<Executives> {

	List<Executives> findAllByEntID(String entid);
	/*
	 * 根据企业ID获取Executives集合，根据size判断用户是否填写该内容
	 */
	int exsit(String entId);
}
