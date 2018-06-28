package com.credit.service.member;

import java.util.List;

import com.credit.bean.member.Customer;
import com.credit.dao.DAO;


public interface CustomerService extends DAO<Customer> {

	public void updateVisible(Boolean judgeProccess, String userName,
			Integer visible, String entID);
	
	public void updatePerVisible(Boolean judgeProccess, String userName,
			Integer visible, String entID);
	
	public boolean exist(String field, String value);
	
	public boolean exist(String field, String value, String value2);

	public boolean exist(String enterpriseID);

	public Customer selectByEntID(String uuid);

	/**
	 * @Title 根据orgid 和customer type 查询相关机构
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	public List<Customer> selectByTypeAndOrg(String orgid,Integer type);

	public Customer selectByPerID(String perID);

	/*	public TempRegistrationVO selectByEntID(String EnterpriseID);

	public TempRegistration selectTempByEnterpriseID(String EnterpriseID);

	public void updateTemp(int status,String enterpriseID);*/
	
}
