/**
 * 
 */
package com.credit.service.member.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.member.OrganizationMapper;
import com.credit.model.member.Organization;
import com.credit.modelvo.organization.LineChartVO;
import com.credit.modelvo.organization.ScoringEnterpriseList;
import com.credit.modelvo.organization.StatisticalListVO;
import com.credit.modelvo.pagelist.QueryResult;
import com.credit.service.member.OrganizationService;
import com.github.pagehelper.PageInfo;

/**
 * @Title: OrganizationServiceBean.java
 * @author Administrator @date 2017-9-21 下午8:01:06
 * @Description: TODO
 */

@Service
@Transactional
public class OrganizationServiceBean implements OrganizationService {

	@Resource
	private OrganizationMapper mapper;
	
	private static final Logger logger = Logger.getLogger(OrganizationServiceBean.class);
	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("OrganizationService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#insert(com.credit.model.member.Organization)
	 */
	@Override
	public int insert(Organization record) {
		logger.info("OrganizationService_insert;record:" + record);
		return mapper.insert(record);
	}

	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#insertSelective(com.credit.model.member.Organization)
	 */
	@Override
	public int insertSelective(Organization record) {
		logger.info("OrganizationService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Organization selectByPrimaryKey(String uuid) {
		logger.info("OrganizationService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#updateByPrimaryKeySelective(com.credit.model.member.Organization)
	 */
	@Override
	public int updateByPrimaryKeySelective(Organization record) {
		logger.info("OrganizationService_updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#updateByPrimaryKey(com.credit.model.member.Organization)
	 */
	@Override
	public int updateByPrimaryKey(Organization record) {
		logger.info("OrganizationService_updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	/* 
	 * @author Administrator @date 2017-9-21 下午8:01:06
	 * (non-Javadoc)
	 * @see com.credit.service.member.OrganizationService#selectlineChartNum(java.lang.String)
	 */
	@Override
	public List<LineChartVO> selectlineChartNum(String uuid) {
		logger.info("OrganizationService_selectlineChartNum;uuid:" + uuid);
		return mapper.selectlineChartNum(uuid);
		
	}

	@Override
	public QueryResult<ScoringEnterpriseList> searchByAll(int pageNum, int pageSize,Map<String, Object> params){
		logger.info("OrganizationService_searchByAll;pageNum:" + pageNum+";pageSize:"+pageSize+";params:"+params);
		QueryResult<ScoringEnterpriseList> q = new QueryResult<ScoringEnterpriseList>();
		List<ScoringEnterpriseList> list = mapper.searchByAll(new RowBounds(pageNum,pageSize),params);
		PageInfo<ScoringEnterpriseList> page = new PageInfo<ScoringEnterpriseList>(list);
		q.setResultlist(page.getList());
		q.setTotalrecord(page.getTotal());
		return q;
	}

	@Override
	public List<StatisticalListVO> searchByStatisticalList(String orgid) {
		logger.info("OrganizationService_searchByStatisticalList;orgid："+orgid);
		return mapper.searchByStatisticalList(orgid);
	}
	
	@Override
	public Map<String, Organization> selectByAll() {
		logger.info("OrganizationService_selectByAll;");
		Map<String, Organization> tpOrganazationMap = new HashMap<String, Organization>();
		List<Organization> organizations =  mapper.selectByAll();
		for (int i = 0; i < organizations.size(); i++) {
			Organization t = organizations.get(i);
			if(t.getTwodomainnames() != null && !"".equals(t.getTwodomainnames().trim())){
				tpOrganazationMap.put(t.getTwodomainnames().trim(), t);
			}
		}
		return tpOrganazationMap;
	}

	@Override
	public List<LineChartVO> selectAllScores() {
		logger.info("OrganizationService_selectAllScores;");
		return mapper.selectAllScores();
	}

	@Override
	public List<StatisticalListVO> searchByAllStatisticalList() {
		logger.info("OrganizationService_searchByStatisticalList;");
		return mapper.searchByAllStatisticalList();
	}

	@Override
	public List<LineChartVO> selectIndustryNum(Integer applyreportstate,
			Integer scorestate) {
		logger.info("OrganizationService_selectIndustryNum;applyreportstate ："+applyreportstate+" ;scorestate: "+scorestate);
		return mapper.selectIndustryNum(applyreportstate,scorestate);
	}
}
