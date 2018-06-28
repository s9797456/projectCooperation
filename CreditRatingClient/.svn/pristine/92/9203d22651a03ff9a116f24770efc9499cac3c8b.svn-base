/**
 * 
 */
package com.credit.service.member;

import java.util.List;
import java.util.Map;

import com.credit.model.member.Organization;
import com.credit.modelvo.organization.LineChartVO;
import com.credit.modelvo.organization.ScoringEnterpriseList;
import com.credit.modelvo.organization.StatisticalListVO;
import com.credit.modelvo.pagelist.QueryResult;

/**
 * @Title: OrganizationService.java
 * @author Administrator @date 2017-9-21 下午7:59:24
 * @Description: TODO
 */
public interface OrganizationService {

	int deleteByPrimaryKey(String uuid);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    /**
     * @Title 获取折线图 的客户得分 、相同分数的数量
     * @author  Administrator  @date 2017-9-21 
     * @Description 
     *
     */
    List<LineChartVO> selectlineChartNum(String uuid);
    
  /**
   * @Title 获取该机构下评分的企业列表
   * @author  Administrator  @date 2017-9-21 
   * @Description 
   *
   */
    public QueryResult<ScoringEnterpriseList> searchByAll(int pageNum, int pageSize,Map<String, Object> params);
    
    /**
     * @Title 统计该机构下各项评分状态 数量（统计 scorestate applyreportstate 的数量）
     * @author  Administrator  @date 2017-9-22 
     * @Description 
     *
     */
	public List<StatisticalListVO> searchByStatisticalList(String orgid);

	/**
	 * @Title 用于机构使用 二级域名登录
	 * @author  Administrator  @date 2017-9-22 
	 * @Description 
	 * @return Map<String, Organization>
	 */
	Map<String, Organization> selectByAll();
	
	/**
	 * @Title 选择所有企业的分数统计
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	List<LineChartVO> selectAllScores();
	
	/**
	 * @Title 选择所有企业的评分状态统计
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	List<StatisticalListVO> searchByAllStatisticalList();
	
	/**
	 * @Title 根据企业状态获取 各行业 企业的数量
	 * @author  Administrator  @date 2017-9-29 
	 * @Description 
	 *
	 */
	public List<LineChartVO> selectIndustryNum(Integer applyreportstate,Integer scorestate);

}
