package com.credit.service.addition;

import com.credit.bean.addition.UploadFileCategory;
import com.credit.dao.DAO;

public interface UploadFileCategoryService extends DAO<UploadFileCategory> {
	/**
	 * @Title 根据属性名称 和属性值 判断对象是否存在
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	public boolean exist(String field, String value);
}
