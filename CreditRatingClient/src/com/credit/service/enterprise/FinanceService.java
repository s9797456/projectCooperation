/**
 * 
 */
package com.credit.service.enterprise;

import com.credit.model.enterprise.Finance;


public interface FinanceService {
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
    int insert(Finance record);
	/**
	 * @title 根据主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
    Finance selectByPrimaryKey(String uuid);
	/**
	 * @title 更新数据
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
    int updateByPrimaryKey(Finance record);
	/**
	 * @title 根据企业主键查询
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	Finance selectByEntID(String entBaseInfoUuid);

}
