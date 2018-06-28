package com.credit.mapper.person;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.credit.model.person.PerUploadFile;

public interface PerUploadFileMapper {
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
   	List<PerUploadFile> selectByPerIDAndFileID(@Param("personalID")String personalID,@Param("fileID")String fileID);
   	/**
   	 * @title 判断所有必须上传的文件是否已经上传
   	 * @author  孙尚飞  2017-9-15
   	 * @desc
   	 */
   	List<String> judgeFileAllUpload(@Param("personalID")String personalID,@Param("isMust")String isMust);
}