package com.credit.service.member;

import java.util.List;
import java.util.Map;

import com.credit.model.member.Customer;
import com.credit.modelvo.Industry;


public interface CustomerService<T> {
	/**
	 * @title 根据主键删除
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	int deleteByPrimaryKey(String username);
	/**
	 * @title 插入数据
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    int insertSelective(Customer record);
	/**
	 * @title 根据主键查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    Customer selectByPrimaryKey(String username);
	/**
	 * @title 更新数据
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    int updateByPrimaryKey(Customer record);
	/**
	 * @title 根据传入的参数进行查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    public Customer selectByParam(Map<String, Object> params);
	/**
	 * @title 根据企业ID进行查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	public Customer selectByEntID(String entID);
	/**
	 * @title 查询行业数据
	 * @author  孙尚飞  2017-7-26
	 * @desc
	 */
	public List<Industry> findAllIndustry();
	/**
	 * @title 查询所有用户
	 * @author  孙尚飞  2017-7-26
	 * @desc
	 */
	public List<Customer> selectAll();
}
