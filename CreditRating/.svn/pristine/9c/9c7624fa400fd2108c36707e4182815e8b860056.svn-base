package junit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.credit.bean.addition.Model;
import com.credit.bean.addition.UploadFileCategory;
import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Finance;
import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.enterprise.UploadFile;
import com.credit.bean.member.Customer;
import com.credit.bean.member.User;
import com.credit.bean.privilege.Organization;
import com.credit.bean.transferBean.EnterpriseInfo;
import com.credit.bean.transferBean.EnterpriseInfoList;
import com.credit.bean.transferBean.Finance2;
import com.credit.bean.transferBean.FinanceList;
import com.credit.bean.transferBean.MainPerson;
import com.credit.bean.transferBean.MainPersonList;
import com.credit.bean.transferBean.Member2;
import com.credit.bean.transferBean.MemberList;
import com.credit.bean.transferBean.Model2;
import com.credit.bean.transferBean.ModelList;
import com.credit.bean.transferBean.OperScanFile;
import com.credit.bean.transferBean.OperScanFileList;
import com.credit.bean.transferBean.ScanFile;
import com.credit.bean.transferBean.ScanFileList;
import com.credit.bean.transferBean.ScoreResult;
import com.credit.bean.transferBean.ScoreResultList;
import com.credit.bean.transferBean.ShareHolder;
import com.credit.bean.transferBean.ShareHolderList;
import com.credit.bean.transferBean.User2;
import com.credit.bean.transferBean.UserList;
import com.credit.bean.transferBean.XmlSite;
import com.credit.service.addition.ModelService;
import com.credit.service.addition.UploadFileCategoryService;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.FinanceService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.enterprise.UploadFileService;
import com.credit.service.member.CustomerService;
//import com.credit.service.member.OrganizationService;
import com.credit.service.member.UserService;
import com.credit.util.FileUtil;
import com.credit.util.model.ModelUtil;
import com.credit.util.properties.BusinessUtil;
import com.credit.util.xml.XmlToBean;
/**
 * Description:本类用来导入HYTScore、P1、P2项目数据
 * Company: 汇誉通
 * @author 严树炜
 * @date 2017-9-25上午11:17:36
 */
public class TransXmlToBean {
	
	private static ApplicationContext cxt;
	private static UserService userService;
	private static ShareholderService shareholderService;
	private static UploadFileCategoryService uploadFileCategoryService;
	private static UploadFileService uploadFileService;
	private static ModelService modelService;
	private static EntResultService entResultService;
//	private static OrganizationService organizationService;
	
	
	private static EntBaseInfoService entBaseInfoService;
	private static CustomerService customerService;
	private static ExecutivesService executivesService;
	private static FinanceService financeService;
	private static ProcessStateService processStateService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			
			userService = (UserService) cxt.getBean("userServiceBean");
			
			uploadFileCategoryService= (UploadFileCategoryService) cxt.getBean("uploadFileCategoryServiceBean");
			uploadFileService= (UploadFileService) cxt.getBean("uploadFileServiceBean");
			entBaseInfoService= (EntBaseInfoService) cxt.getBean("entBaseInfoServiceBean");
			customerService= (CustomerService) cxt.getBean("customerServiceBean");
			executivesService = (ExecutivesService) cxt.getBean("executivesServiceBean");
			shareholderService = (ShareholderService) cxt.getBean("shareholderServiceBean");
			modelService = (ModelService) cxt.getBean("modelServiceBean");
			entResultService = (EntResultService) cxt.getBean("entResultServiceBean");
			financeService = (FinanceService) cxt.getBean("financeServiceBean");
//			organizationService = (OrganizationService) cxt.getBean("organizationServiceBean");
			processStateService = (ProcessStateService) cxt.getBean("processStateServiceBean");
		} catch (Exception e) {
			System.out.println("-------------err-----------------");
			System.out.println(e);		
			System.out.println("--------------err----------------");
		}
	}
	/**
	 * Description:流程表数据导入
	 * @author 严树炜
	 * @date 2017-9-25
	 */
	@Test
	public void testprint111(){
		List<EntResult> lists=entResultService.getScrollData().getResultlist();
		for(EntResult result:lists){
			EntBaseInfo ent=result.getEntBaseInfo();
			String entID=ent.getUuid();
			if(ent.getProcess()==null){
				ProcessState process=new ProcessState();
				process.setUuid(UUID.randomUUID().toString().replace("-", ""));
				process.setEntBaseInfo(ent);
				process.setApplyReportState(1);
				process.setScoreState(5);
				process.setExecutivesState(executivesService.exsit(entID));
				process.setFinanceState(financeService.exsit(entID));
				process.setUploadFileState(uploadFileService.exsit(entID));
				process.setShareholderState(shareholderService.exsit(entID));
				processStateService.save(process);
			}else{
				ProcessState process1=ent.getProcess();
				process1.setApplyReportState(1);
				process1.setScoreState(5);
				process1.setExecutivesState(executivesService.exsit(entID));
				process1.setFinanceState(financeService.exsit(entID));
				process1.setUploadFileState(uploadFileService.exsit(entID));
				process1.setShareholderState(shareholderService.exsit(entID));
				processStateService.update(process1);
			}
			
		}
	}
	
	/**
	 * Description:企业评分表初评、终评数据导入
	 * @author 严树炜
	 * @date 2017-9-25
	 */
	@Test
	public void TestScoreResult() throws JAXBException, IOException{
		ScoreResultList object = (ScoreResultList) transToBean(XmlSite.SCORERESULT.getSite(),ScoreResultList.class);
		List<ScoreResult> srs = object.getList();
		List<String> list = new ArrayList<String>();
		for (ScoreResult sr : srs) {
			String entId = entBaseInfoService.selectByEntName(sr.getEnterpriseInfo_Id());
			if(entId!=null){
				EntResult er = entResultService.selectByEntID(entId);
				if(er!=null){
					if(er.getPreliminaryLevel()==null){//如果初评得分为null
						er.setEntBaseInfo(entBaseInfoService.find(entId));
						er.setPreliminaryLevel(sr.getPreliminaryLevel());
						er.setPreliminaryScore(sr.getPreliminarySocre());
						er.setFinalLevel(sr.getFinalLevel());
						er.setFinalScore(sr.getFinalSocre());
					}else{
						er.setPreliminaryLevel(null);
						er.setPreliminaryScore(null);
						er.setFinalLevel(null);
						er.setFinalScore(null);
					}
					entResultService.update(er);
				}else{
					list.add(sr.getEnterpriseInfo_Id());
				}
			}
		}
		for (String string : list) {
			System.out.println(string);
		}
	}
	/**
	 * Description:P1模型导入
	 * @author 严树炜
	 * @date 2017-9-25
	 */
	@Test
	public void testprint(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("648d689b-dd1d-49c5-bb33-6eb34235d187".replace("-", ""), "商贸(批发零售)业");
		map.put("9456ea27-7da7-4bd0-ae0b-5fb232a1f53f".replace("-", ""), "通用行业");
		map.put("aae53772-339f-4c1d-ac1c-7415054a8726".replace("-", ""), "软件和信息技术服务业");
		map.put("cbf965ba-7cf5-4c03-a00d-aed5a1c0be48".replace("-", ""), "建筑行业");
		map.put("e2b6387d-86ee-430f-832b-ad3e1e5fddb2".replace("-", ""), "制造业");
		map.put("935b0f9e-11f8-4c10-b52f-e53908c91b44".replace("-", ""), "软件行业");
		int count = 1;
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			count++;
			Map.Entry<String, String> entry = it.next();
			Model model = new Model();
			model.setUuid(entry.getKey());
			model.setName(entry.getValue());
			model.setParent(modelService.find("641adbcc66804a79bbf6747600e50c90"));
			String xmlPath = BusinessUtil.getMsg("XMLurl")+model.getUuid()+".xml";
			model.setXMLurl(xmlPath);
			model.setUpdateTime(new Date());
			model.setVersion("v1.0");
			model.setVisible(1);
			model.setOrderNO(count);
			modelService.save(model);
		}
	}
	
	@Test
	public void Test123(){
		String s1 = BusinessUtil.getMsg("inputXMLUrl");
		String s2 = BusinessUtil.getMsg("scoreXMLUrl");
		String d = BusinessUtil.getMsg("adr");
		String path = d+"\\"+s2+"35c92de74a794000aeaf81c828a41da7.xml";
		System.out.println(new File(path).exists());
	}
	
	@Test
	public void Test123456(){
		String id = ModelUtil.getXMLUuid("C:\\Users\\Administrator\\Desktop\\软件.xml");
		System.out.println(id);
	}
	@Test
	public void Test1234567(){
		System.out.println(entResultService.selectByEntID("1c82e63957ec412c99a84cf695b2a4da").getUuid());
	}
	
	/**
	 * Description:插入entResult中的评分、推送模型路径
	 * @author 严树炜
	 * @date 2017-9-21
	 */
	@Test
	public void TestSavePath(){
		String s1 = BusinessUtil.getMsg("inputXMLUrl");
		String s2 = BusinessUtil.getMsg("scoreXMLUrl");
		String d = BusinessUtil.getMsg("adr");
		List<String> list = entBaseInfoService.getAllEntId();
		for (String string : list) {
			String path = d+s2+string+".xml";//评分模型路径
			String path2 = d+s1+string+".xml";//推送模型路径
			if(new File(path).exists()){
				Customer c = customerService.selectByEntID(string);
				if(c!=null){
					EntResult er = entResultService.selectByEntID(string);
					if(er!=null){
						er.setScoreXMLTime(new Date());
						er.setScoreXMLUrl(s2+string+".xml");
						er.setCustomer(c);
						String modelId = ModelUtil.getXMLUuid(path);
						Model model = modelService.find(modelId);
						if(model!=null) er.setModel(model);
						EntBaseInfo ent = entBaseInfoService.find(string);
						if(ent!=null) er.setEntBaseInfo(ent);
						entResultService.update(er);
					} 
				}else{
					System.out.println("企业跟客户不匹配！");
					System.out.println("entId:"+string+"      customerName:"+c.getUserName());
				}
			}
			if(new File(path2).exists()){
				Customer c = customerService.selectByEntID(string);
				if(c!=null){
					EntResult er = entResultService.selectByEntID(string);
					if(er!=null){
						er.setInputXMLTime(new Date());
						er.setInputXMLUrl(s1+string+".xml");
						if(er.getCustomer()==null) er.setCustomer(c);
						if(er.getModel()==null){
							String modelId = ModelUtil.getXMLUuid(path);
							Model model = modelService.find(modelId);
							if(model!=null) er.setModel(model);
							EntBaseInfo ent = entBaseInfoService.find(string);
							if(ent!=null) er.setEntBaseInfo(ent);
						}
						entResultService.update(er);
					}
				}else{
					System.out.println("企业跟客户不匹配！");
					System.out.println("entId:"+string+"      customerName:"+c.getUserName());
				}
			}
		}
	}
	
	@Test
	public void Test() throws JAXBException, IOException{
		List<String> strs = entBaseInfoService.getRepetitiveName();
		for (String s : strs) {
			List<EntBaseInfo> list = entBaseInfoService.selectByName(s);
			int num = 0;
			for (EntBaseInfo e : list) {
				if(e.getUSCC()!=null || !"".equals(e.getUSCC())){
					num = 1;
				}
			}
			switch(num){
				case 0:
					for(int i = 0;i < list.size()-1;i++){
						entBaseInfoService.delete(list.get(i).getUuid());
					};
				case 1:
					for(EntBaseInfo e : list){
						String uuid = "";
						if(e.getUSCC()!=null || !"".equals(e.getUSCC())){
							uuid = e.getUuid();
						}
						if(!e.getUuid().equals(uuid)){
							entBaseInfoService.delete(e.getUuid());
						}
					}
			}
		}
	}
	
	@Test
	public void Testtt() throws JAXBException, IOException{
		EnterpriseInfoList object=(EnterpriseInfoList)transToBean(XmlSite.ENTERPRISEINFO.getSite(), EnterpriseInfoList.class);
		List<EnterpriseInfo> ents =object.getList();
		List<EntBaseInfo> list = new ArrayList<EntBaseInfo>();
		System.out.println(ents.size());
		for (EnterpriseInfo e : ents) {
			String sid = entBaseInfoService.selectByEntName(e.getName());//导入P2企业表时，有重复企业，故需排重
			if(sid==null){
				String uuid = UUID.randomUUID().toString().replace("-", "");
				EntBaseInfo entBaseInfo = new EntBaseInfo(e);
				entBaseInfo.setUuid(uuid);
				list.add(entBaseInfo);
			}
		}
		System.out.println("--------------------------------------");
		for (EntBaseInfo e : list) {
			System.out.println(e.getName());
		}
	}
	
	/**
	 * Discribution:导入模型数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferModel() throws JAXBException, IOException{
		ModelList object=(ModelList)transToBean(XmlSite.Model.getSite(), ModelList.class);
		List<Model2> models =object.getModelList();
		for (Model2 e : models) {
			Model model = new Model(e);
			model.setXMLurl(BusinessUtil.getMsg("XMLurl")+model.getUuid()+".xml");
			model.setParent(modelService.find("641adbcc66804a79bbf6747600e50c90"));
			modelService.save(model);			
			}
		}
	
	/**
	 * Description:导入评分数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferScoreResult() throws JAXBException, IOException{
		ScoreResultList object = (ScoreResultList) transToBean(XmlSite.SCORERESULT.getSite(),ScoreResultList.class);
		ScoreResultList object2 = (ScoreResultList) transToBean("D:\\xml\\P1\\scoreresult.xml",ScoreResultList.class);
		List<ScoreResult> srs = object.getList();
		List<ScoreResult> srs2 = object2.getList();
		List<String> strs = new ArrayList<String>();
		for (ScoreResult m : srs2) {
			strs.add(m.getEnterpriseInfo_Id());
		}
		for (ScoreResult m : srs) {//去重
			int count = 0;
			for (String s : strs) {
				if(s.equals(m.getEnterpriseInfo_Id())){
					count++;
				}
			}
			if(count == 0){
				EntResult er = new EntResult(m);
				EntBaseInfo ent = entBaseInfoService.find(m.getEnterpriseInfo_Id());
				if(ent!=null){
					er.setEntBaseInfo(ent);
					entResultService.save(er);
				}
			}
		}
	}
	
	/**
	 * Description:导入财务数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferFinance() throws JAXBException, IOException{
		FinanceList object = (FinanceList) transToBean(XmlSite.FINANCE.getSite(),FinanceList.class);
		List<Finance2> mps = object.getFinanceList();
		List<String> strs = new ArrayList<String>();
		String adr=BusinessUtil.getMsg("adr");
		String realUrl=BusinessUtil.getMsg("financeExeclUrl");
		for (Finance2 m : mps) {
				Finance f = new Finance(m);
				EntBaseInfo ent = entBaseInfoService.find(m.getEnterprise_id().replace("-", ""));
				
				if(ent!=null){
					f.setEntID(m.getEnterprise_id());
					String name=f.getFileurl().split("#")[1];
					System.out.println(name);
					String path1="D:\\FinancialStatement\\"+name;
					String path2=adr+realUrl;
					File file1=new File(path1);
					File file2=new File(path2);
					if(file1.exists()&&m.getUploadSequence()>10000){
						if(!file2.exists())file2.mkdirs();
						this.copyFile(path1, path2+name);
						f.setFileurl(path2+name);
						f.setEntID(m.getEnterprise_id().replace("-", ""));
						financeService.save(f);
					}else{
						System.out.println(name+":文件没找到");
						strs.add(name+":文件没找到");
					}
					
				}else{
					System.out.println(m.getEnterprise_id());
					strs.add(m.getEnterprise_id()+":企业没找到");
				}
			}
		for(String str:strs){
			System.out.println(str);
		}
		}
	//}
	@Test
	public void testprint111111(){
		String input = BusinessUtil.getMsg("inputXMLUrl");
		String score = BusinessUtil.getMsg("scoreXMLUrl");
		String adr = BusinessUtil.getMsg("adr");
		List<EntResult> lists=entResultService.getScrollData().getResultlist();
		for(EntResult result:lists){
			EntBaseInfo ent=result.getEntBaseInfo();
			if(ent!=null){
				String entID=ent.getUuid();
				String path1="C://Users//Administrator//Desktop//P1//input//";
				String path2="C://Users//Administrator//Desktop//P1//score//";
				File file1=new File(path1+entID+".xml");
				File file2=new File(path2+entID+".xml");
				if(file1.exists()){
					FileUtil.copyAndDelete(path1+entID+".xml", adr+input+result.getUuid()+".xml");
				}
				if(file2.exists()){
					FileUtil.copyAndDelete(path2+entID+".xml", adr+score+result.getUuid()+".xml");
				}
			}
			
		}
	}
	
	/**
	 * Description:复制文件
	 * @author 严树炜
	 * @date 2017-9-25
	 */
	 public void copyFile(String oldPath, String newPath) { 
		 try { 
			 int bytesum = 0; 
			 int byteread = 0; 
			 File oldfile = new File(oldPath); 
			 if (oldfile.exists()) { //文件存在时 
				 InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				 FileOutputStream fs = new FileOutputStream(newPath); 
				 byte[] buffer = new byte[1444]; 
				 while ( (byteread = inStream.read(buffer)) != -1) { 
					 bytesum += byteread; //字节数 文件大小 
					 System.out.println(bytesum); 
					 fs.write(buffer, 0, byteread); 
				 } 
				 inStream.close(); 
			 } 
		 } 
		 catch (Exception e) { 
			 System.out.println("复制单个文件操作出错"); 
			 e.printStackTrace(); 

		 } 
	 }
	/**
	 * Discribution:导入企业数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferEntBaseInfo() throws JAXBException, IOException{
		List<String> strs = new ArrayList<String>();
		EnterpriseInfoList object=(EnterpriseInfoList)transToBean(XmlSite.ENTERPRISEINFO.getSite(), EnterpriseInfoList.class);
		List<EnterpriseInfo> ents =object.getList();
		for (EnterpriseInfo e : ents) {//遍历存入XML中的企业数据集合
			List<EntBaseInfo> list= entBaseInfoService.selectByName(e.getName());//导入P2企业表时，有重复企业，故需排重
			int  count=0;
			if(!list.isEmpty()){//如果企业名重复 说明该数据已插入 更新之
				for(EntBaseInfo ent : list){
					EntResult result=new EntResult();
					result=ent.getEntResult();
					if(result!=null){
						result.setModel(null);
						result.setEntBaseInfo(null);
						entResultService.update(result);
						System.out.println("删除评分");
						entResultService.delete(result.getUuid());
					}
					Customer customer=new Customer();
					customer=ent.getCustomer();
					if(customer!=null){
						customer.setEntBaseInfo(null);
						customer.setEntResult(null);
						customer.setOrganization(null);
						customerService.update(customer);
						System.out.println("删除客户");
						customerService.delete(customer.getUserName());
					}
					ent.setCustomer(null);
					ent.setEntResult(null);
					entBaseInfoService.update(ent);
					System.out.println(ent.getUuid());
					entBaseInfoService.delete(ent.getUuid());
					count++;
				}
				
			}
			System.out.println("总数："+count);
			String sid=entBaseInfoService.selectByEntName(e.getName());
			if(sid==null){
				EntBaseInfo entBaseInfo = new EntBaseInfo(e);
				entBaseInfoService.save(entBaseInfo);
				
				EntResult entResult = new EntResult();
				entResult.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entResult.setEntBaseInfo(entBaseInfo);
				if(e.getModel()!=null) {
					String modelId = modelService.selectByModelName(e.getModel());
					if(modelId!=null){
						entResult.setModel(new Model(modelId));
					}else{
						strs.add(e.getModel());
					}
				}
				if(e.getBelong()!=null) entResult.setReportUrl(e.getBelong());
				entResultService.save(entResult);
				
			}else{
				EntBaseInfo entBaseInfo = new EntBaseInfo(e);
				entBaseInfoService.update(entBaseInfo);
				
				EntResult entResult = entResultService.selectByEntID(e.getUuid().replace("-", ""));
				if(entResult!=null){
					if(e.getBelong()!=null) entResult.setReportUrl(e.getBelong());
					if(e.getModel()!=null) {
						String modelId = modelService.selectByModelName(e.getModel());
						if(modelId!=null){
							entResult.setModel(new Model(modelId));
						}else{
							strs.add(e.getModel());
						}
					}
					entResultService.update(entResult);
				}else{
					EntResult entResult1 =new EntResult();
					entResult1.setUuid(UUID.randomUUID().toString().replace("-", ""));
					entResult1.setEntBaseInfo(entBaseInfo);
					if(e.getModel()!=null) {
						String modelId = modelService.selectByModelName(e.getModel());
						if(modelId!=null){
							entResult1.setModel(new Model(modelId));
						}else{
							strs.add(e.getModel());
						}
					}
					if(e.getBelong()!=null) entResult1.setReportUrl(e.getBelong());
					entResultService.save(entResult1);
				}
				
			}
		}
		for (String s : strs) {
			System.out.println("**"+s);
		}
	}
	/**
	 * Description:导入客户数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferCustomer() throws JAXBException, IOException{
		UserList object = (UserList) transToBean(XmlSite.USER.getSite(),UserList.class);
		List<User2> members = object.getUsers();
		List<Customer> list = new ArrayList<Customer>();

		for (User2 m : members) {//去重
				Customer c = new Customer(m);
				Customer cutomer = customerService.find(m.getUserName());
				if(cutomer!=null){
					cutomer.setOrganization(null);
					cutomer.setEntBaseInfo(null);
					cutomer.setEntResult(null);
					customerService.update(cutomer);
					System.out.println(cutomer.getUserName());
					customerService.delete(cutomer.getUserName());
				}
				String entId = entBaseInfoService.selectByEntName(m.getEnterpriseId());
				if(entId!=null){
					c.setEntBaseInfo(new EntBaseInfo(entId));
					if(m.getBelongorg()!=null){
						Organization org=organizationService.selectByName(m.getBelongorg());
						if(org==null){
							Organization o=new Organization();
							o.setUuid(UUID.randomUUID().toString().replace("-", ""));
							o.setName(m.getBelongorg());
							organizationService.save(o);
							c.setOrganization(o);
						}else{
							c.setOrganization(org);
						}
					}
					customerService.save(c);
				}else{
					list.add(c);
				}
		}
		for(Customer customer:list){
			System.out.println(customer.getUserName());
		}
	}
	
	/**
	 * Description:Member去重方法
	 * @author 严树炜
	 * @date 2017-9-19
	 */
	@Test
	public void testtttt() throws JAXBException, IOException{
		MemberList object = (MemberList) transToBean(XmlSite.MEMBER.getSite(),MemberList.class);//P2
		MemberList object2 = (MemberList) transToBean("D:\\xml\\P1\\member.xml",MemberList.class);//P1 Member
		List<Member2> members = object.getMemberList();
		List<Member2> members2 = object2.getMemberList();
		List<String> strs = new ArrayList<String>();
		List<String> strs2 = new ArrayList<String>();
		List<String> strs3 = new ArrayList<String>();
		
		for (Member2 m : members) {
			strs.add(m.getUserName());
		}
		for (Member2 m : members2) {
			strs2.add(m.getUserName());
		}
		for (String s1 : strs) {
			for (String s2 : strs2) {
				if(s1.equals(s2)){
					strs3.add(s1);
				}
			}
		}
		for (String s : strs3) {
			System.out.println(s);
		}
		
	}
	/**
	 * Description:导入高管数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferExecutives() throws JAXBException, IOException{
		MainPersonList object = (MainPersonList) transToBean(XmlSite.MAINPERSON.getSite(),MainPersonList.class);
		List<MainPerson> mps = object.getMainPersonList();
		List<String> strs = new ArrayList<String>();
		List<Executives> list = new ArrayList<Executives>();
		for (MainPerson m : mps) {
			String entId = entBaseInfoService.selectByEntName(m.getEnterprise_infoId());
			List<Executives> exs=executivesService.findAllByEntID(entId);
			if(!exs.isEmpty()){
				for(Executives ex:exs){
					System.out.println(entId);
					System.out.println(ex.getUuid());
					executivesService.delete(ex.getUuid());
				}
			}
				Executives e = new Executives(m);
				if(entBaseInfoService.selectByEntName(m.getEnterprise_infoId())!=null){
					if(entId!=null && entBaseInfoService.selectById(entId)!=null){
						e.setEntID(entId);
						executivesService.save(e);
					}else{
						list.add(e);
					}
				}
		}
	}
	
	/**
	 * Description:导入股东数据
	 * @author 严树炜
	 * @date 2017-9-18
	 */
	@Test
	public void TransferShareHolder1() throws JAXBException, IOException{
		ShareHolderList object = (ShareHolderList) transToBean(XmlSite.SHAREHOLDER.getSite(),ShareHolderList.class);
		List<ShareHolder> shs = object.getShareHolderList();
		List<Shareholder> list = new ArrayList<Shareholder>();
		for (ShareHolder sh : shs) {
			String entId = entBaseInfoService.selectByEntName(sh.getEnterprise_infoId());
			List<Shareholder> exs=shareholderService.findAllByEntID(entId);
			if(!exs.isEmpty()){
				for(Shareholder ex:exs){
					System.out.println(entId);
					System.out.println(ex.getUuid());
					shareholderService.delete(ex.getUuid());
				}
			}
				Shareholder s = new Shareholder(sh);
				if(entBaseInfoService.selectByEntName(sh.getEnterprise_infoId())!=null){
					
					if(entId!=null && entBaseInfoService.find(entId)!=null){
						s.setEntID(entId);
						shareholderService.save(s);
					}else{
						list.add(s);
					}
				}
			}
	}
	/**
	 * Description:导入P2股东数据
	 * @author 严树炜
	 * @date 2017-9-19
	 */
	@Test
	public void TransferShareHolder2() throws JAXBException, IOException{
		ShareHolderList object = (ShareHolderList) transToBean(XmlSite.SHAREHOLDER.getSite(),ShareHolderList.class);
		ShareHolderList object2 = (ShareHolderList) transToBean("D:\\xml\\P1\\shareholder.xml",ShareHolderList.class);
		List<ShareHolder> shs = object.getShareHolderList();
		List<ShareHolder> shs2 = object2.getShareHolderList();
		List<String> strs = new ArrayList<String>();
		List<Shareholder> list = new ArrayList<Shareholder>();
		for(ShareHolder sh :shs2){//P1
			strs.add(sh.getEnterprise_infoId());
		}
		for (ShareHolder sh : shs) {
			int count = 0;
			for(String s :strs){
				if(s.equals(sh.getEnterprise_infoId())){
					count++;
				}
			}
			if(count==0){
				Shareholder s = new Shareholder(sh);
				if(entBaseInfoService.selectByEntName(sh.getEnterprise_infoId())!=null){
					String entId = entBaseInfoService.selectByEntName(sh.getEnterprise_infoId());
					if(entId!=null && entBaseInfoService.find(entId)!=null){
						s.setEntID(entId);
						shareholderService.save(s);
					}else{
						list.add(s);
					}
				}
			}
		}
	}
	@Test
	public void test() throws Exception{
		entBaseInfoService.find("1c16c3774c7147c6aaa3259ec9d8f4ab");
	}
	//-------------------------------------------------------------------------
	@Test
	public void TransferUser() throws JAXBException, IOException{
		
		UserList object=(UserList)transToBean(XmlSite.USER.getSite(), UserList.class);
		List<User2> users=object.getUsers();
		for(User2 user:users){
			User u=new User(user);
			userService.save(u);
		}
	}
	
	/*股东信息表*/
	@Test
	public void TransferShareHolder() throws JAXBException, IOException{
		
		ShareHolderList object=(ShareHolderList)transToBean(XmlSite.SHAREHOLDER.getSite(), ShareHolderList.class);
		List<ShareHolder> holders=object.getShareHolderList();
		for(ShareHolder holder:holders){
			Shareholder h=new Shareholder(holder);
			shareholderService.save(h);
		}
	}
	
	/**/
	@Test
	public void TransferAccount(){
		System.out.println(userService);
	}
	
	/*高层信息*/
	@Test
	public void TransferMainPerson() throws JAXBException, IOException{
		
		MainPersonList object=(MainPersonList)transToBean(XmlSite.SHAREHOLDER.getSite(), MainPersonList.class);
		List<MainPerson> mps=object.getMainPersonList();
		for(MainPerson mp:mps){
			Executives h=new Executives(mp);
			executivesService.save(h);
		}
	}
	
	/*附件分类*/
	@Test
	public void TransferScanFile() throws JAXBException, IOException{
		
		ScanFileList object=(ScanFileList)transToBean(XmlSite.SHAREHOLDER.getSite(), ScanFileList.class);
		List<ScanFile> files=object.getList();
		for(ScanFile sf:files){
			UploadFileCategory h=new UploadFileCategory(sf);
			uploadFileCategoryService.save(h);
		}
	}
	
	/*附件信息*/
	@Test
	public void TransferOperScan() throws JAXBException, IOException{
		
		OperScanFileList object=(OperScanFileList)transToBean(XmlSite.SHAREHOLDER.getSite(), OperScanFileList.class);
		List<OperScanFile> files=object.getList();
		for(OperScanFile sf:files){
			UploadFile h=new UploadFile(sf);
			//h.setUploadFileCategoryID(uploadFileCategoryService)
			uploadFileService.save(h);
		}
	}

	public Object transToBean(String xmlPath,Class<?> load)throws JAXBException, IOException{
        Object object=XmlToBean.xmlToBean(xmlPath,load);
        return object;
	}

}
