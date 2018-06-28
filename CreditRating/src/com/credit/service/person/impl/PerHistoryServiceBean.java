package com.credit.service.person.impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.person.PerHistory;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.person.PerHistoryService;


@Service
@Transactional
public class PerHistoryServiceBean extends DaoSupport<PerHistory> implements PerHistoryService {



}
