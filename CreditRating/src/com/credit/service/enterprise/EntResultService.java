package com.credit.service.enterprise;

import java.util.List;
import java.util.Map;

import com.credit.bean.enterprise.EntResult;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.enterprise.EntResultVO;
import com.credit.bean.vo.enterprise.EntScoreVO;
import com.credit.dao.DAO;
import com.credit.util.model.IndexRateVo;

public interface EntResultService extends DAO<EntResult> {

	public EntResult selectByEntID(String entID);
	
	public QueryResult<EntScoreVO> findScoreEnterperise(int page,int limit, Map<String, String> params);
	/**
	 * @title 查询当天第几个生成报告
	 * @author  孙尚飞  2017-8-16
	 * @desc
	 */
	public long countByEncoding();
	/**
	 * @title 获取调整项
	 * @author  孙尚飞  2017-8-21
	 * @desc
	 */
	public List<String> getAdjustOption(EntResult result);
	/**
	 * @title 获取指标比重
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	public List<IndexRateVo> getIndexRate(String scoreXMLUrl);
	/**
	 * @title 级联删除
	 * @author  孙尚飞  2017-8-24
	 * @desc
	 */
	public void deleteCompletely(String resultID);
	/**
	 * Description:获取企业评级对象
	 * @author 严树炜
	 * @date 2017-10-19
	 */
	public EntResult getEntResultByEntId(String entId);
	/**
	 * @title 根据企业和模型查询
	 * @author  孙尚飞  2017-12-22
	 * @desc
	 */
	public EntResult selectByEntAndModel(String uuid, String model);
	
	/**
	 * @Description 根据用户主键查找模型名
	 * @author 严树炜
	 * @date 2018-1-16
	 */
	public EntResult selectModelNameByUserName(String username);
}
