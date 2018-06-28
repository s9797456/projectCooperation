package com.credit.controller.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.member.Customer;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.enterprise.EntResultVO;
import com.credit.bean.vo.member.CustomerVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.member.CustomerService;

@Controller
@RequestMapping("/control/customer")
public class CustomerAction {

	private static final Logger logger = Logger.getLogger(CustomerAction.class);
	private String moduleName = "管理员操作情况";
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;
	@Resource(name = "entResultServiceBean")
	private EntResultService entResultService;
	/**
	 * @title 客户列表
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(String userName,int page,int start,int limit) throws Exception {
		logger.info(moduleName + "[查看客户列表]");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<Object> responseJson = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("updateTime", "desc");
		//orderby.put("visible", "asc");
		PageView<Customer> pageView = null;
		pageView = new PageView<Customer>(limit, page);
		// 根据用户名进行模糊查询
		if (userName != null && !"".equals(userName.trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.userName like ?" + (params.size() + 1));
			params.add("%" + userName.trim() + "%");
		}
		if(params.size()>0){
			jpql.append(" and ");
		} 
		jpql.append(" o.type = ?"+ (params.size()+1));
		params.add(0);
		QueryResult<Customer> qr = customerService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(qr);
		long total = qr.getTotalrecord();
		List<Customer> users = qr.getResultlist();
		for (Customer u : users) {
			CustomerVO uv = new CustomerVO();
			EntBaseInfo ent=u.getEntBaseInfo();
			if (ent != null) {
				uv.setEntID(ent.getUuid());
				uv.setEntName(ent.getName());
				if(ent.getIndustry()!=null){
					uv.setEntIndustry(ent.getIndustry());
				}
			}
			if(u.getUpdateTime()!= null){
				uv.setUpdateTime(sdf.format(u.getUpdateTime()));
			}
			EntResult er = entResultService.selectModelNameByUserName(u.getUserName());
			if(er!=null && er.getModel()!=null){
				uv.setModelName(er.getModel().getName()+er.getModel().getVersion());
			}
			uv.setUserName(u.getUserName());
			uv.setRealName(u.getRealName());
			Date date = u.getLastLoginTime();
			if (date != null) {
				String lastLoginTime = sdf.format(date);
				uv.setLastLoginTime(lastLoginTime);
			} else {
				uv.setLastLoginTime("");
			}
			if (u.getVisible() == 1) {
				uv.setStatus("正常" + " | " + "锁定");
			} else {
				uv.setStatus("关闭" + " | " + "激活");
			}
			uv.setVisible(u.getVisible());
			uv.setCellphone(u.getCellphone());
			uv.setEmail(u.getEmail());
			responseJson.add(uv);
		}
		msgMap.put("customers", responseJson);
		msgMap.put("total", total);
		return msgMap;
	}

	
}
