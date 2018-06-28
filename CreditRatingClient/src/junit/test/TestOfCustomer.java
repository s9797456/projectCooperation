package junit.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.credit.dataIO.ImportFromXml;
import com.credit.model.enterprise.EntBaseInfo;
import com.credit.model.member.Customer;
import com.credit.model.member.Organization;
import com.credit.model.privilege.C_Customer_RoleKey;
import com.credit.model.privilege.C_PrivilegeGroup;
import com.credit.model.privilege.C_SystemPrivilege;
import com.credit.model.privilege.C_SystemPrivilegeKey;
import com.credit.modelvo.RedBlackList;
import com.credit.modelvo.organization.LineChartVO;
import com.credit.modelvo.organization.ScoringEnterpriseList;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.member.CustomerService;
import com.credit.service.member.OrganizationService;
import com.credit.service.privilege.C_PrivilegeGroupService;
import com.credit.service.privilege.C_SystemPrivilegeService;
import com.credit.util.financialexecl.ExcelShower;
import com.nbchina.ws.api.entinfo.riskinfo.Penalty;
import com.nbchina.ws.api.entinfo.riskinfo.RiskInterface;

public class TestOfCustomer {
	private static ApplicationContext cxt;

	private static CustomerService<?> customerService;

	private static EntBaseInfoService baseInfoService;

	private static OrganizationService organizationService;
	
	private static C_SystemPrivilegeService<?> c_SystemPrivilegeService;
	
	private static C_PrivilegeGroupService<?> c_PrivilegeGroupService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			customerService=(CustomerService<?>) cxt.getBean("customerServiceBean");
			baseInfoService=(EntBaseInfoService) cxt.getBean("entBaseInfoServiceBean");
			organizationService=(OrganizationService) cxt.getBean("organizationServiceBean");
			c_SystemPrivilegeService=(C_SystemPrivilegeService<?>) cxt.getBean("c_SystemPrivilegeServiceBean");
			c_PrivilegeGroupService=(C_PrivilegeGroupService<?>) cxt.getBean("c_PrivilegeGroupServiceBean");
		} catch (Exception e) {
		}
	}

	@Test
	public void Test01() throws Exception {
		
		List<Customer> customers =customerService.selectAll();
		for(Customer customer : customers){
			int type=customer.getType();
			C_Customer_RoleKey ccrk=new C_Customer_RoleKey();
			ccrk.setUsername(customer.getUsername());
			if(type==0){
				ccrk.setGroupid("7dbd20066778497bade71cbe46cc4d60");
			}else if(type==1){
				ccrk.setGroupid("fbcc02fb9c2c462ea6fe4c725c17b5b5");
			}else if(type==2){
				ccrk.setGroupid("2928612898d0408fa26f6ae303ee17ea");
			}else{
				continue;
			}
			c_PrivilegeGroupService.insertCustomerRole(ccrk);
		}
	}

	@Test
	public void Test02() throws Exception {
		List<C_SystemPrivilegeKey> cspk=c_PrivilegeGroupService.selectByCustomer("leader");
		for(C_SystemPrivilegeKey key : cspk){
			System.out.println(key.getModel()+"-"+key.getPrivilegevalue());
		}
	}

	@Test
	public void Test03() throws Exception {
		System.out.println(c_SystemPrivilegeService);
		C_SystemPrivilege cs=new C_SystemPrivilege();
		cs.setModel("qaaaa");
		cs.setPrivilegevalue("aaaaa");
		cs.setName("测试");
		c_SystemPrivilegeService.insertSelective(cs);
	}
	
	@Test
	public void Test04() throws Exception {
		System.out.println(c_PrivilegeGroupService);
		/*C_PrivilegeGroup cs=new C_PrivilegeGroup();
		cs.setName("asdasd");
		c_privilegeGroupService.insertSelective(cs);*/
	}
	
	@Test
	public void importData() throws Exception{
		
		try{
			Document document = new SAXReader().read(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("com/config/init_datas.xml")
			);
			importPermissions(document.selectNodes("//Permissions/Permission"));
			importRoles(document.selectNodes("//Roles/Role"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void importPermissions(List<C_SystemPrivilege> lists){
		if (c_SystemPrivilegeService.getCount() == 0) {
			List<C_SystemPrivilege> systemPrivileges = new ArrayList<C_SystemPrivilege>();
			for (Iterator<C_SystemPrivilege> iter = lists.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				C_SystemPrivilege systemPrivilege = new C_SystemPrivilege();
				systemPrivilege.setName(element.attributeValue("name"));
				systemPrivilege.setModel(element.attributeValue("model"));
				systemPrivilege.setPrivilegevalue(element.attributeValue("privilegeValue"));
				systemPrivileges.add(systemPrivilege);
				System.out.println("导入权限模块["+element.attributeValue("name")+"]");
			}
			boolean flag=c_SystemPrivilegeService.batchInsert(systemPrivileges);	
			if(!flag){System.err.println("---导入权限模块失败:插入失败");}
		}else{
			System.err.println("---导入权限模块失败:已存在数据---");
		}
		System.out.println("+++导入权限模块成功+++");
	}
	
	
	
	@SuppressWarnings("unchecked")
	protected void importRoles(List<C_PrivilegeGroup> lists){
		for (Iterator<C_PrivilegeGroup> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			C_PrivilegeGroup pg = new C_PrivilegeGroup();
			pg.setUuid(UUID.randomUUID().toString().replace("-", ""));
			pg.setName(element.attributeValue("name"));
			pg.setMenuurl(element.attributeValue("menuUrl"));
			c_PrivilegeGroupService.insertSelective(pg);
			
			List<C_SystemPrivilege> permissions = element.selectNodes("Permission");
			Set<C_SystemPrivilege> systemPrivileges = new HashSet<C_SystemPrivilege>();;
			for (Iterator<C_SystemPrivilege> iterator = permissions.iterator(); iterator.hasNext();) {
				Element spElem = (Element) iterator.next();
				C_SystemPrivilege sp = new C_SystemPrivilege();
				sp.setName(spElem.attributeValue("name"));
				sp.setModel(spElem.attributeValue("model"));
				sp.setPrivilegevalue(spElem.attributeValue("privilegeValue"));
				systemPrivileges.add(sp);
			}
			boolean flag=c_PrivilegeGroupService.insertGroupPrivilege(systemPrivileges,pg.getUuid());
			if(!flag){
				System.err.println("---导入角色权限失败:插入失败---");
				}
		}
	}
	
}