package com.credit.service.addition;

import java.util.List;

import com.credit.bean.addition.Model;
import com.credit.dao.DAO;

public interface ModelService extends DAO<Model> {
	/**
	 * @title 获取所有父ID为空的模型
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	public List<Model> getAllRootModel();

	/**
	 * @Title 根据属性名称 和属性值 判断对象是否存在
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	public boolean exist(String field, String value);
	/**
	 * Description:通过模型名找到该模型ID
	 * @author 严树炜
	 * @date 2017-9-21
	 */
	public String selectByModelName(String name);
}
