package com.credit.service.security;

import com.credit.model.security.RegistLimit;



public interface RegistLimitService<T> {
	
	public void insertSelective(RegistLimit record);
	
	public RegistLimit selectByPrimaryKey(String uuid);
	
	public int updateByPrimaryKey(RegistLimit record);
	
	public boolean IPLimit(String ip,String startDate,String endDate);
	
	public boolean PhoneLimit(String phone,String startDate,String endDate);
	
	public boolean NumLimit(String startDate, String endDate);
	
}
