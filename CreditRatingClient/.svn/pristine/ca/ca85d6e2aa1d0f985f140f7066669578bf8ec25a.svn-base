package com.credit.service.member;

import java.util.Map;

import com.credit.model.member.User;


public interface UserService<T> {
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
    int insertSelective(User record);
	/**
	 * @title 根据主键查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    User selectByPrimaryKey(String username);
	/**
	 * @title 更新数据
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    int updateByPrimaryKey(User record);
	/**
	 * @title 根据传入的参数进行查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
    public User selectByParam(Map<String, Object> params);

}
