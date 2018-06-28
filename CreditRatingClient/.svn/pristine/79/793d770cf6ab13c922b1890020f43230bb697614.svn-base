/**
 * 
 */
package com.credit.service.enterprise;

import com.credit.model.enterprise.Opinion;

/**
 * @Title: OpinionService.java
 * @author Administrator @date 2017-8-1 
 * @Description: TODO
 */
public interface OpinionService {

	/**
	 * @Title 根据UUID删除 对象
	 * @author  Administrator  @date 2017-8-2 
	 * @Description 
	 *
	 */
    int deleteByPrimaryKey(String uuid);

    /**
     * @Title 保存一个对象
     * @author  Administrator  @date 2017-8-2 
     * @Description 
     *
     */
    int insert(Opinion record);

    /**
     * @Title 插入数据
     * @author  Administrator  @date 2017-8-2 
     * @Description 
     *
     */
    int insertSelective(Opinion record);

    /**
     * @Title 根据uuid 选择 返回对象
     * @author  Administrator  @date 2017-8-2 
     * @Description 
     *
     */
    Opinion selectByPrimaryKey(String uuid);

    /**
     * @Title 更新对象
     * @author  Administrator  @date 2017-8-2 
     * @Description 
     *
     */
    int updateByPrimaryKeySelective(Opinion record);

    /**
     * @Title 更新对象
     * @author  Administrator  @date 2017-8-2 
     * @Description 
     *
     */
    int updateByPrimaryKey(Opinion record);
    
    /**
     * @Title 根据EntBaseInfo查询对象
     * @author  Administrator  @date 2017-8-2 
     * @Description 
     * @return Opinion
     *
     */
	Opinion selectByEntBaseInfoKey(String entBaseInfoUuid, Integer isAdmin);
}
