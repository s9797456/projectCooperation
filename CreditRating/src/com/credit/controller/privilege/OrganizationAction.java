package com.credit.controller.privilege;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.member.Customer;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Organization;
import com.credit.bean.vo.privilege.OrganizationVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.member.CustomerService;
import com.credit.service.privilege.OrganizationService;


@Controller
@RequestMapping("/control/organization")
public class OrganizationAction {

	private static final Logger logger = Logger.getLogger(OrganizationAction.class);

	@Resource(name = "organizationServiceBean")
	private OrganizationService organizationService;
	
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;


	/**
	 * @Title 机构列表
	 * @author  Administrator  @date 2017-10-24 
	 * @Description 
	 */
	@Permission(model = "organization", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(Organization organization,int page,int limit) throws Exception {
		logger.info( "机构列表listUI;Organization:"+organization);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		PageView<Organization> pageView = new PageView<Organization>(limit, page);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();

		if (organization.getName() != null && !"".equals(organization.getName().trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.name like ?");
			params.add("%" + organization.getName().trim() + "%");
		}

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("name", "asc");
		QueryResult<Organization> qr = organizationService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),jpql.toString(), params.toArray(), orderby);

		pageView.setQueryResult(qr);

		List<OrganizationVO> list = new ArrayList<OrganizationVO>();
		for (Organization org : qr.getResultlist()) {
			OrganizationVO orgvo = new OrganizationVO();
			// ov.setParentName(or.getParent().getName());
			if (org != null) {
				orgvo.setUuid(org.getUuid());
				orgvo.setDescription(org.getDescription());
				orgvo.setTwoDomainNames(org.getTwoDomainNames());
				orgvo.setOrgUrl(org.getOrgUrl());
				orgvo.setImgUrl(org.getImgUrl());
				orgvo.setName(org.getName());
				if (org.getVisible().intValue() == 1) {
					orgvo.setVisible("1");
					orgvo.setStatus("正常" + " | " + "锁定");
				} else {
					orgvo.setVisible("0");
					orgvo.setStatus("关闭" + " | " + "激活");
				}
				if(org.getCustomers() != null && org.getCustomers().size()>0){
					for (Customer customer : org.getCustomers()) {
						if(customer.getType() != 0){
							// 帐号
							orgvo.setUserName(customer.getUserName());
							// 登录次数
							orgvo.setLoginTimes(customer.getLoginTimes());
							// 注册日期
							orgvo.setRegTime(customer.getRegTime());
							// 更新日期
							orgvo.setUpdateTime(customer.getUpdateTime());
							// 成员状态 true为激活,false为关闭
							orgvo.setCusVisible(customer.getVisible()) ;
							orgvo.setPhone(customer.getCellphone());
							orgvo.setEmail(customer.getEmail());
							orgvo.setType(customer.getType());
							break;
						}else{
							orgvo.setUserName(" - ");
							orgvo.setPhone(org.getPhone());
							orgvo.setEmail(org.getEmail());
							orgvo.setType(1);
						}
					}
				}else{
					orgvo.setUserName(" - ");
					orgvo.setPhone(org.getPhone());
					orgvo.setEmail(org.getEmail());
					orgvo.setType(1);
				}
				
				list.add(orgvo);
			}
		}
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("list",list );
		return msgMap;

	}

}
