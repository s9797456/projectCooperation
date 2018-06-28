package com.credit.service.enterprise;

import java.util.List;

import com.credit.model.enterprise.EntResult;
import com.credit.util.model.IndexRateVo;

public interface EntResultService {
	/**
	 * @title 根据客户主键查询
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
    EntResult selectByCustomerID(String customerid);
	/**
	 * @title 更新
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
    int updateByPrimaryKey(EntResult record);
    /**
	 * @title 根据企业主键查询
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
	EntResult selectByEntId(String enterpriseId);
	/**
	 * @Title  根据xml 获取 一级指标得分
	 * @author  Administrator  @date 2017-9-17 
	 * @Description 
	 *
	 */
	List<IndexRateVo> getIndexRate(String string);
	 /**
		 * @title 查询所有数据
		 * @author  孙尚飞  2017-8-30
		 * @desc
		 */
	List<EntResult> selectAll();
	/**
	 * @title 根据主键查询
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
    EntResult selectByPrimaryKey(String uuid);
    /**
	 * @title 根据企业主键和模型主键查询
	 * @author  孙尚飞  2017-8-30
     * @param modelID 
     * @param string 
	 * @desc
	 */
	EntResult selectByEntAndModel(String string, String modelID);
}
