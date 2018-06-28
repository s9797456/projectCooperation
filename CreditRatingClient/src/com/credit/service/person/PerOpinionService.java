package com.credit.service.person;

import com.credit.model.person.PerOpinion;

public interface PerOpinionService {
	
		int deleteByPrimaryKey(String uuid);

	    int insert(PerOpinion record);

	    int insertSelective(PerOpinion record);

	    PerOpinion selectByPrimaryKey(String uuid);

	    int updateByPrimaryKeySelective(PerOpinion record);

	    int updateByPrimaryKey(PerOpinion record);

	    /**
	     * @title 申请提交信息后,获取后台管理员驳回内容
	     * @param perid
	     * @param i
	     * @return
	     */
		PerOpinion selectByPerIDReject(String perid);

		/**
		 * @title 获取用户在初评阶段的反馈信息
		 * @param perid
		 * @return
		 */
		PerOpinion selectByPerIDAndScore(String perid);
}