package com.credit.service.privilege.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.privilege.Log4j;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.Log4jService;



@Service
@Transactional
public class Log4jServiceBean<T> extends DaoSupport<Log4j> implements Log4jService{


}
