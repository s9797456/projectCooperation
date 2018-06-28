package com.credit.mapper.enterprise;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.credit.model.enterprise.UploadFile;

public interface UploadFileMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
    
	List<UploadFile> selectByEntID(String entid);

	List<UploadFile> selectByEntIDAndFileID(@Param("entid")String entid,@Param("fileID")String fileID);

	List<String> judgeFileAllUpload(@Param("entid")String entID,@Param("ismust")String ismust);

	List<UploadFile> findAllPathByCross();
}