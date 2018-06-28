package junit.test;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.credit.bean.member.User;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.vo.enterprise.FinanceVo;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.member.UserService;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.OrganizationService;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.service.privilege.SystemPrivilegeService;
import com.credit.util.HistoricalDataUtil;
import com.credit.util.financeExecl.ResolveFinance;
import com.credit.util.model.Index;
import com.credit.util.model.IndexRateVo;
import com.credit.util.model.Option;



public class JPASpringTestOfPrivilege {
	private static ApplicationContext cxt;
	
	private static EntityManagerFactory entityManagerFactory;
	
	@Resource(name="systemPrivilegeServiceBean")
	private static SystemPrivilegeService systemPrivilegeService;
	
	@Resource(name="privilegeGroupServiceBean")
	private static PrivilegeGroupService privilegeGroupService;
	
	@Resource(name="organizationServiceBean")
	private static OrganizationService organizationService;
	
	@Resource(name="userServiceBean")
	private static UserService userService;
	
	@Resource(name="menuServiceBean")
	private static MenuService menuService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			systemPrivilegeService = (SystemPrivilegeService) cxt.getBean("systemPrivilegeServiceBean");
			userService = (UserService) cxt.getBean("userServiceBean");
			
			entityManagerFactory = (EntityManagerFactory) getBean("entityManagerFactory");
			EntityManager em = entityManagerFactory.createEntityManager();
			TransactionSynchronizationManager.bindResource(entityManagerFactory,new EntityManagerHolder(em));
			
		} catch (Exception e) {
			System.out.println("-------------err-----------------");
			System.out.println(e);		
			System.out.println("--------------err----------------");
		}
	}
	
	protected static Object getBean(String beanName) {
		return cxt.getBean(beanName);
	}

	public void tearDown() throws Exception {//解决延迟加载
		EntityManagerHolder holder = (EntityManagerHolder)TransactionSynchronizationManager.getResource(entityManagerFactory);
		EntityManager em = holder.getEntityManager();
		em.flush();
		TransactionSynchronizationManager.unbindResource(entityManagerFactory);
		EntityManagerFactoryUtils.closeEntityManager(holder.getEntityManager());
	}

	
	@Test
	public void TestOfFinance()  throws Exception{
		User user = userService.find("admin");
		Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();
		List<SystemPrivilege> spList = systemPrivilegeService.getScrollData().getResultlist();
		for(SystemPrivilege sp : spList){
			systemPrivileges.add(sp);
		}
		user.setSystemPrivileges(systemPrivileges);
		userService.update(user);
	}

}