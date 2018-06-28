/**
 * 
 */
package com.credit.controller.organization;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.model.enterprise.EntBaseInfo;
import com.credit.model.enterprise.EntResult;
import com.credit.model.enterprise.Executives;
import com.credit.model.enterprise.Shareholder;
import com.credit.model.member.Customer;
import com.credit.modelvo.Permission;
import com.credit.modelvo.SessionName;
import com.credit.modelvo.organization.ScoringEnterpriseList;
import com.credit.modelvo.organization.StatisticalListVO;
import com.credit.modelvo.pagelist.PageView;
import com.credit.modelvo.pagelist.QueryResult;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.member.OrganizationService;
import com.credit.util.model.Index;
import com.credit.util.model.IndexVO;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;


/**
 * 进入 机构界面
 * @Title: OrganizationController.java
 * @author Administrator @date 2017-9-21 下午6:49:09
 * @Description: TODO
 */
@Controller
@RequestMapping("/initOrganization")
public class OrganizationController {
	
	private static final Logger logger = Logger.getLogger(OrganizationController.class);

	private String moduleName = "用户操作情况";
	
	@Resource
	OrganizationService organizationService;
	/*企业基础信息*/
	@Resource
	private EntBaseInfoService entBaseInfoService;
	/*股东信息*/
	@Resource
	private ShareholderService shareholderService;
	/*高管信息*/
	@Resource
	private ExecutivesService executivesService ;
	/*评分结果*/
	@Resource
	private EntResultService entResultService ;
	/**
	 * @Title  机构下已评价公司分数的数量
	 * @author  Administrator  @date 2017-9-22 
	 * @Description 
	 *
	 */
	@Permission(model = "organization", privilegeValue = "main")
	@SuppressWarnings("unused")
	@RequestMapping("/getCountScore")
	@ResponseBody
	public Map<String, Object> getCountScore(Model model,
			HttpServletRequest request) {
		logger.info(moduleName + "[获取 机构下已评价公司分数的折线图]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		System.out.println("huoqu cus ： "+customer.getOrgid());
		if (customer != null) {
			msgMap.put("ChartVos", organizationService.selectlineChartNum(customer.getOrgid()));
			msgMap.put("status", true);
		}else{
			msgMap.put("status", false);
		}
		return msgMap;
	}
	/**
	 * @Title 获取 统计该机构下公司评分状态个数
	 * @author  Administrator  @date 2017-9-22 
	 * @Description 
	 *
	 */
	@Permission(model = "organization", privilegeValue = "main")
	@SuppressWarnings("unused")
	@RequestMapping("/summaryscoring")
	@ResponseBody
	public Map<String, Object> summaryscoring(Model model,HttpServletRequest request) {
		logger.info(moduleName + "[统计该机构下公司评分状态数量]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		System.out.println("获取 cus ： "+customer.getOrgid());
		if (customer != null) {
			List<StatisticalListVO> statisticalListVOs= organizationService.searchByStatisticalList(customer.getOrgid());
			for (StatisticalListVO statisticalListVO : statisticalListVOs) {
				if("0".equals(statisticalListVO.getApplyreportstate())){
					msgMap.put("notApplied",statisticalListVO.getC_scorestate());
				}else{
					if("0".equals(statisticalListVO.getScorestate())){
						msgMap.put("confirmation",statisticalListVO.getC_scorestate());
					}else if("1".equals(statisticalListVO.getScorestate())){
						msgMap.put("editorialSurvey",statisticalListVO.getC_scorestate());
					}else if("2".equals(statisticalListVO.getScorestate())){
						msgMap.put("endInvestigation",statisticalListVO.getC_scorestate());
					}else if("3".equals(statisticalListVO.getScorestate())){
						msgMap.put("preliminaryConfirmation",statisticalListVO.getC_scorestate());
					}else if("4".equals(statisticalListVO.getScorestate())){
						msgMap.put("score",statisticalListVO.getC_scorestate());
					}else if("5".equals(statisticalListVO.getScorestate())){
						msgMap.put("endScore",statisticalListVO.getC_scorestate());
					}
				}
			}
			msgMap.put("status", true);
		}else{
			msgMap.put("status", false);
		}
		return msgMap;
	}
	
	/**
	 * @Title 评分企业名单
	 * @author  Administrator  @date 2017-9-22 
	 * @Description 
	 *
	 */
	@Permission(model = "organization", privilegeValue = "scoreList")
	@RequestMapping("/scoringEnterpriseList")
	public String lineChart(HttpServletRequest request,Integer pageNum, Integer pageSize,String searchTitle) {
				Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
				if (customer != null) {
					logger.info(moduleName + "[统计该机构下评分企业名单]");
					if (pageNum==null) pageNum = 1;//页面第几页的参数，默认为第一页
					if (pageSize==null) pageSize = 15;//每个页面显示几条记录仅仅这儿定义即可
					Map<String, Object> params = new HashMap<String, Object>();	
					params.put("orgid", customer.getOrgid());
					if(searchTitle!=null && ! "".equals(searchTitle.trim())){
						params.put("name",searchTitle.trim());
					}
					PageView<ScoringEnterpriseList> pageView =new PageView<ScoringEnterpriseList>(pageSize, pageNum);
					QueryResult<ScoringEnterpriseList> qr = organizationService.searchByAll(pageNum, pageSize,params);
					System.out.println(qr.getTotalrecord());
					System.out.println(qr.getResultlist());
					pageView.setQueryResult(qr);
					System.out.println(pageView);
					request.setAttribute("pageView", pageView);//查询出的数据放入pageView中，供页面展示
					request.setAttribute("status", 0);
					/*相关代码此处补充*/
					return "menu/org/scoringEnterpriseList";
				}else{
					return "redirect:/customer/logonUI.do";
				}
	}
	@Permission(model = "organization", privilegeValue = "scoreList")
	@RequestMapping("/scoringEnterpriseListView")
	public String scoringEnterpriseListView(HttpServletRequest request,String entid) {
				Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
				if (customer != null) {
					logger.info(moduleName + "[查看公司详细信息]");
					if(entid !=null && !"".equals(entid.trim())){
						EntBaseInfo entBaseInfo= entBaseInfoService.selectByPrimaryKey(entid);
						request.setAttribute("entBaseInfo",entBaseInfo);
						List<Shareholder> shareholders = shareholderService.selectByEntBaseInfo(entid);
						request.setAttribute("shareholders", shareholders);
						List<Executives> executives = executivesService.selectByEntBaseInfo(entid);
						request.setAttribute("executives", executives);
						EntResult entResult= entResultService.selectByEntId(entid);
						request.setAttribute("entResult", entResult);
						
						//模型
						boolean flag=true;
						String adr=BusinessUtil.getMsg("adr");
						String root=BusinessUtil.getMsg("root");
						List<Index> indexs = new ArrayList<Index>();
						adr=adr+root;
						String path=new String();
						if(entResult==null){
							flag=false;
							request.setAttribute("msg","该企业未进入评分流程");
						}else{
							if(entResult.getScorexmlurl()!=null){
								path=adr+entResult.getScorexmlurl();
								File file=new File(path);
								if(!file.exists()){
									flag=false;
									request.setAttribute("msg","XML文件不存在");
								}
							}else{flag=false;}
						}
						if(flag){
							try {
								File file=new File(path);
								Document document = new SAXReader().read(file);
								indexs=ModelUtil.getIndex(document);
							} catch (DocumentException e) {
								e.printStackTrace();
							}
							List<IndexVO> indexvos=new ArrayList<IndexVO>();
							if(indexs != null){
								for(Index index1 : indexs){
									for(Index index2 : index1.getChirds()){
										for(Index index3 : index2.getChirds()){
											IndexVO indexvo=new IndexVO();
											indexvo.setUuid(index3.getUuid());
											indexvo.setIndexName(index3.getName());
											if(index3.getWeight()!=null){
												indexvo.setWeight(index3.getWeight());
												for(Option option : index3.getOptions()){
													if(option.getSelected()!=null&&option.getSelected().equals("true")){
														indexvo.setOptionName(option.getName());
														indexvo.setOptionScore(Double.parseDouble(option.getValue()));
														double indexscore=Double.parseDouble(option.getValue())*Double.parseDouble(index3.getWeight());
														indexvo.setIndexScore(indexscore);
													}
												}
												indexvos.add(indexvo);
											}
										}
									}

								}	
							}
							request.setAttribute("indexs", indexvos);
						}
						return "menu/org/scoringEnterpriseListView";
					}else{
						request.setAttribute("status", 1);
						return "menu/org/scoringEnterpriseList";
					}
					
				}else{
					return "redirect:/customer/logonUI.do";
				}
	}
	
}
