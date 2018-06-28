package com.credit.mapper.member;

import java.util.Map;

import com.credit.model.member.User;

public interface UserMapper {
    int deleteByPrimaryKey(String username);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    public User selectByParam(Map<String, Object> params);
}