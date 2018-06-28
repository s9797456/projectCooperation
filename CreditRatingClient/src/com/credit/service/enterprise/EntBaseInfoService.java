/**
 * 
 */
package com.credit.service.enterprise;

import com.credit.model.enterprise.EntBaseInfo;

public interface EntBaseInfoService {
	/**
	 * @title 根据主键查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	EntBaseInfo selectByPrimaryKey(String uuid);
	/**
	 * @title 更新
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	int updateByPrimaryKeySelective(EntBaseInfo record);
	/**
	 * @title 插入数据
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	int insertSelective(EntBaseInfo record);
	/**
	 * @title 根据企业名查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	EntBaseInfo selectByName(String name);
	/**
	 * @title 根据企业统一信用代码查询
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	EntBaseInfo selectByUscc(String uscc);
}
