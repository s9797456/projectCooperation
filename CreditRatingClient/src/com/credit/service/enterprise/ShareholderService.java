/**
 * 
 */
package com.credit.service.enterprise;

import java.util.List;

import com.credit.model.enterprise.Shareholder;

public interface ShareholderService {
		/**
		 * @Title 删除
		 * @author  Administrator  @date 2017-8-21 
		 * @Description 
		 *
		 */
	 	int deleteByPrimaryKey(String uuid);
		/**
		 * @Title 插入
		 * @author  Administrator  @date 2017-8-21 
		 * @Description 
		 *
		 */
	    int insert(Shareholder record);

	    /**
	     * @Title 插入
	     * @author  Administrator  @date 2017-8-21 
	     * @Description 
	     *
	     */
	    int insertSelective(Shareholder record);

	    /**
	     * @Title 获取 Shareholder 对象  根据uuid
	     * @author  Administrator  @date 2017-8-21 
	     * @Description 
	     *
	     */
	    Shareholder selectByPrimaryKey(String uuid);

	    /**
	     * @Title 获取 Shareholder 对象  根据uuid
	     * @author  Administrator  @date 2017-8-21 
	     * @Description 
	     *
	     */
	     List<Shareholder> selectByEntBaseInfo(String entBaseInfouuid);
	    /**
	     * @Title 更新
	     * @author  Administrator  @date 2017-8-21 
	     * @Description 
	     *
	     */
	    int updateByPrimaryKeySelective(Shareholder record);

	    /**
	     * @Title 更新
	     * @author  Administrator  @date 2017-8-21 
	     * @Description 
	     *
	     */
	    int updateByPrimaryKey(Shareholder record);
	    
	    /***
	     * @title 计数
	     * @author Administrator @date 2017-8-23
	     * @Description
	     */
	    public int countShareholder(String entBaseInfouuid);

}
