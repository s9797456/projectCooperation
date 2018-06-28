package com.credit.service.person;

import java.util.List;

import com.credit.model.person.PerUploadFile;

public interface PerUploadFileService {
	int deleteByPrimaryKey(String uuid);

    int insert(PerUploadFile record);

    int insertSelective(PerUploadFile record);

    PerUploadFile selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerUploadFile record);

    int updateByPrimaryKey(PerUploadFile record);
    
    /**
	 * @title 根据个人信用主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	List<PerUploadFile> selectByPerID(String personalID);
	/**
	 * @title 根据个人信用主键和附件类型主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	List<PerUploadFile> selectByPerIDAndFileID(String personalID,
			String fileID);
	/**
	 * @title 判断所有必须上传的文件是否已经上传
	 * @author  孙尚飞  2017-9-15
	 * @desc
	 */
	List<String> judgeFileAllUpload(String personalID,String isMust);
}
