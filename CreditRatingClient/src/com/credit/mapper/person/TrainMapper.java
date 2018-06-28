package com.credit.mapper.person;

import java.util.List;

import com.credit.model.person.Train;

public interface TrainMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Train record);

    int insertSelective(Train record);

    Train selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Train record);

    int updateByPrimaryKey(Train record);
    
	List<Train> selectByPerID(String perid);

	int countTrain(String perid);
}