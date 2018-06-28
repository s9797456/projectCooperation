package com.credit.service.person;


import java.util.List;

import com.credit.bean.person.Skills;
import com.credit.dao.DAO;

public interface SkillsService extends DAO<Skills> {

	List<Skills> findAllByPerID(String uuid);	
}
