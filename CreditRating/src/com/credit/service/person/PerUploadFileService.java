package com.credit.service.person;


import java.util.List;
import java.util.Map;

import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.person.PerUploadFile;
import com.credit.bean.vo.enterprise.UploadFileVO;
import com.credit.dao.DAO;

public interface PerUploadFileService extends DAO<PerUploadFile> {

	QueryResult<UploadFileVO> findPerUploadFiles(Map<String, String> searchpParams, String perID, int page, int limit);

	List<PerUploadFile> findAllByPerID(String uuid);

	
}
