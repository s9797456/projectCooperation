package com.credit.service.enterprise;

import java.util.List;
import java.util.Map;

import com.credit.bean.enterprise.UploadFile;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.enterprise.UploadFileVO;
import com.credit.dao.DAO;

public interface UploadFileService extends DAO<UploadFile> {
	/**
	 * @title 按条件查询上传文件
	 * @author  孙尚飞  2017-8-11
	 * @desc
	 */
	public QueryResult<UploadFileVO> findEntUploadFiles(Map<String,String> searchpParams,String entID,int page,int limit);

	public List<UploadFile> findAllByEntID(String entID);
	
	/*
	 * 根据企业ID获取Executives集合，根据size判断用户是否填写该内容
	 */
	int exsit(String entId);

}
