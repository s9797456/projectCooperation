package com.credit.mapper.member;

import java.util.List;
import java.util.Map;

import com.credit.model.member.Customer;
import com.credit.modelvo.Industry;

public interface CustomerMapper {
    int deleteByPrimaryKey(String username);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
	public Customer selectByParam(Map<String, Object> params);

	public Customer selectByEntID(String entID);

	public List<Industry> findAllIndustry();

	List<Customer> selectAll();
}