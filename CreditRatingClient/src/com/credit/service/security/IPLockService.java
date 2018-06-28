package com.credit.service.security;

import java.util.List;

import com.credit.model.security.IPLock;



public interface IPLockService<T> {

    public List<IPLock> selectIPList(Integer statue);
    
    public int insert(IPLock record);
    
    public int update(IPLock record);
    
    public int delete(String uuid);
    
    public IPLock selectByPrimaryKey(String uuid);

	public List<IPLock> selectByIP(String iP);

}
