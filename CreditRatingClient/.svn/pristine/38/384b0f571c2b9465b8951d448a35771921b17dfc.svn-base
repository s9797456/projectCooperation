package com.credit.service.security;

import java.util.Date;

import com.credit.model.security.LoginLimit;




public interface LoginLimitService<T> {
	
    public int delete(String uuid);
    
    public int insert(LoginLimit record);
    
    public int update(LoginLimit record);
    
    public LoginLimit selectByPrimaryKey(String uuid);
    
    public boolean Interval(String ip,Date date);

	public boolean Verification(String ip, String loginname);

}
