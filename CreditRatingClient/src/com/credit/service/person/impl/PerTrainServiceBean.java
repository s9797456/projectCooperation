package com.credit.service.person.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.TrainMapper;
import com.credit.model.person.Train;
import com.credit.service.person.PerTrainService;
@Service
@Transactional
public class PerTrainServiceBean implements PerTrainService {

	private static final Logger logger = Logger.getLogger(PerTrainServiceBean.class);
	@Resource
	private TrainMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerTrainService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(Train record) {
		logger.info("PerTrainService _ insert;record:" + record);
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Train record) {
		logger.info("PerTrainService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public Train selectByPrimaryKey(String uuid) {
		logger.info("PerTrainService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(Train record) {
		logger.info("PerTrainService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Train record) {
		logger.info("PerTrainService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Train> selectByPerID(String perid) {
		logger.info("PerTrainService _ selectByPerID;perid:" + perid);
		return mapper.selectByPerID(perid);
	}

	@Override
	public int countTrain(String perid) {
		logger.info("PerTrainService _ countTrain;perid:" + perid);
		return mapper.countTrain(perid);
	}

}
