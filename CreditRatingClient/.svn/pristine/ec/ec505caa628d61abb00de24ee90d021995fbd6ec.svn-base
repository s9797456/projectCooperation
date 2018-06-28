package com.credit.mapper.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.credit.model.member.Organization;
import com.credit.modelvo.organization.LineChartVO;
import com.credit.modelvo.organization.ScoringEnterpriseList;
import com.credit.modelvo.organization.StatisticalListVO;

public interface OrganizationMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    
    /**
     * @Title 获取该机构下 已评分企业按 分值统计 的数量
     * @author  Administrator  @date 2017-9-28 
     * @Description 
     *
     */
	public List<LineChartVO> selectlineChartNum(String uuid);
	
	/**
	 * 
	 * @Title 获取该机构下 企业列表
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	public List<ScoringEnterpriseList> searchByAll(RowBounds rowBounds,Map<String, Object> params);
	/**
	 * @Title 统计该机构下企业评分状态 数量
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	public List<StatisticalListVO> searchByStatisticalList(@Param("orgid")String orgid);
	/**
	 * @Title 获取所有org 对象  
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	public List<Organization> selectByAll();

	/**
	 * 获取数据库下所有 已评分企业按 分值统计 的数量
	 * @Title 
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	public List<LineChartVO> selectAllScores();

	/**
	 * @Title 统计所有企业评分状态 数量
	 * @author  Administrator  @date 2017-9-28 
	 * @Description 
	 *
	 */
	public List<StatisticalListVO> searchByAllStatisticalList();
	
	/**
	 * @Title 获取 所有数据   已   评分企业 （状态：applyreportstate scorestate） 按 分值统计 的数量
	 * @author  Administrator  @date 2017-9-29 
	 * @Description 
	 *
	 */
	public List<LineChartVO> selectIndustryNum(@Param("applyreportstate")Integer applyreportstate,@Param("scorestate")Integer scorestate);
}