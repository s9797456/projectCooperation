package com.credit.service.person.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.PerUploadFileMapper;
import com.credit.model.person.PerUploadFile;
import com.credit.service.person.PerUploadFileService;

@Service
@Transactional
public class PerUploadFileServiceBean implements PerUploadFileService {

	private static final Logger logger = Logger.getLogger(PerUploadFileServiceBean.class);
	@Resource
	private PerUploadFileMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerUploadFileService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(PerUploadFile record) {
		logger.info("PerUploadFileService _ insert;record:" + record);
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(PerUploadFile record) {
		logger.info("PerUploadFileService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public PerUploadFile selectByPrimaryKey(String uuid) {
		logger.info("PerUploadFileService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(PerUploadFile record) {
		logger.info("PerUploadFileService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PerUploadFile record) {
		logger.info("PerUploadFileService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PerUploadFile> selectByPerID(String personalID) {
		logger.info("PerUploadFileService _ selectByPerID;personalID:" + personalID);
		return mapper.selectByPerID(personalID);
	}

	@Override
	public List<PerUploadFile> selectByPerIDAndFileID(String personalID,String fileID) {
		logger.info("PerUploadFileService _ selectByPerIDAndFileID;personalID:" + personalID+";fileID:"+fileID);
		return mapper.selectByPerIDAndFileID(personalID,fileID);
	}

	@Override
	public List<String> judgeFileAllUpload(String personalID, String isMust) {
		logger.info("PerUploadFileService _ judgeFileAllUpload;personalID:" + personalID+" ;isMust:"+isMust);
		return mapper.judgeFileAllUpload(personalID,isMust);
	}

}
