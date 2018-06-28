package com.credit.service.enterprise;

import java.util.List;
import java.util.Map;

import com.credit.bean.enterprise.Finance;
import com.credit.bean.vo.html2pdf.FinancialData;
import com.credit.dao.DAO;

public interface FinanceService extends DAO<Finance> {

	public List<Map<String, String>> reportFinalData(String entID);

	public List<Finance> findAllByEntID(String entID);
	
	/*
	 * 根据企业ID获取Executives集合，根据size判断用户是否填写该内容
	 */
	int exsit(String entId);
	
	public List<FinancialData> balanceSheet(String entID,int num);
}
