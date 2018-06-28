/**
 * 
 */
package com.credit.service.enterprise;

import java.util.List;

import com.credit.model.enterprise.UploadFile;


public interface UploadFileService {
	/**
	 * @title 根据主键删除
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	int deleteByPrimaryKey(String uuid);
	/**
	 * @title 插入新数据
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
    int insert(UploadFile record);
	/**
	 * @title 根据主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
    UploadFile selectByPrimaryKey(String uuid);
	/**
	 * @title 更新新数据
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
    int updateByPrimaryKey(UploadFile record);
	/**
	 * @title 根据企业主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	List<UploadFile> selectByEntID(String entBaseInfoUuid);
	/**
	 * @title 根据企业主键和附件类型主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	List<UploadFile> selectByEntIDAndFileID(String entBaseInfoUuid,
			String fileID);
	/**
	 * @title 判断所有必须上传的文件是否已经上传
	 * @author  孙尚飞  2017-9-15
	 * @desc
	 */
	List<String> judgeFileAllUpload(String entBaseInfoUuid,String isMust);
	/**
	 * @title 批量文件跨域上传
	 * @author  孙尚飞  2017-9-15
	 * @desc
	 */
	public void batchUploadFile();
}
