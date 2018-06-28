package com.credit.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.credit.bean.addition.Model;
import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Finance;
import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.member.Customer;
import com.credit.bean.privilege.Organization;
import com.credit.bean.transferBean.EnterpriseInfo;
import com.credit.bean.transferBean.EnterpriseInfoList;
import com.credit.bean.transferBean.Finance2;
import com.credit.bean.transferBean.FinanceList;
import com.credit.bean.transferBean.MainPerson;
import com.credit.bean.transferBean.MainPersonList;
import com.credit.bean.transferBean.Model2;
import com.credit.bean.transferBean.ModelList;
import com.credit.bean.transferBean.ScoreResult;
import com.credit.bean.transferBean.ScoreResultList;
import com.credit.bean.transferBean.ShareHolder;
import com.credit.bean.transferBean.ShareHolderList;
import com.credit.bean.transferBean.User2;
import com.credit.bean.transferBean.UserList;
import com.credit.bean.transferBean.XmlSite;
import com.credit.service.addition.ModelService;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.FinanceService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.enterprise.UploadFileService;
import com.credit.service.member.CustomerService;
//import com.credit.service.member.OrganizationService;
import com.credit.util.Excel.ReadOfData;
import com.credit.util.properties.BusinessUtil;

public class DataImport {
	private static ApplicationContext cxt;
	private static ShareholderService shareholderService;
	private static ModelService modelService;
	private static EntResultService entResultService;
	//private static OrganizationService organizationService;
	private static UploadFileService uploadFileService;
	
	
	private static EntBaseInfoService entBaseInfoService;
	private static CustomerService customerService;
	private static ExecutivesService executivesService;
	private static FinanceService financeService;
	private static ProcessStateService processStateService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			uploadFileService= (UploadFileService) cxt.getBean("uploadFileServiceBean");
			entBaseInfoService= (EntBaseInfoService) cxt.getBean("entBaseInfoServiceBean");
			customerService= (CustomerService) cxt.getBean("customerServiceBean");
			executivesService = (ExecutivesService) cxt.getBean("executivesServiceBean");
			shareholderService = (ShareholderService) cxt.getBean("shareholderServiceBean");
			modelService = (ModelService) cxt.getBean("modelServiceBean");
			entResultService = (EntResultService) cxt.getBean("entResultServiceBean");
			financeService = (FinanceService) cxt.getBean("financeServiceBean");
			//organizationService = (OrganizationService) cxt.getBean("organizationServiceBean");
			processStateService = (ProcessStateService) cxt.getBean("processStateServiceBean");
		} catch (Exception e) {
			System.out.println("-------------err-----------------");
			System.out.println(e);		
			System.out.println("--------------err----------------");
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
	 * Discribution:导入企业数据
	 * @author 严树炜
	 * @throws ParseException 
	 * @date 2017-9-18
	 */
	@Test
	public void TransferEntBaseInfo() throws JAXBException, IOException, ParseException{
		List<String> strs = new ArrayList<String>();
		EnterpriseInfoList object=(EnterpriseInfoList)transToBean(XmlSite.ENTERPRISEINFO.getSite(), EnterpriseInfoList.class);
		List<EnterpriseInfo> ents2 =object.getList();
		for (EnterpriseInfo e : ents2) {//遍历存入XML中的企业数据集合
			List<EntBaseInfo> list= entBaseInfoService.selectByName(e.getName());
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
/*		UserList object = (UserList) transToBean(XmlSite.USER.getSite(),UserList.class);
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
		}*/
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
	 * Description:更新评分结果表推送、评分XML存储路径
	 * @author 严树炜
	 * @date 2017-9-25
	 */
	@Test
	public void UpdateEntResultXMLUrl(){
		String input = BusinessUtil.getMsg("inputXMLUrl");
		String score = BusinessUtil.getMsg("scoreXMLUrl");
		String adr = BusinessUtil.getMsg("adr");
		List<EntResult> lists=entResultService.getScrollData().getResultlist();
		for(EntResult result:lists){
			EntBaseInfo ent=result.getEntBaseInfo();
			String entID=ent.getUuid();
			Customer customer=ent.getCustomer();
			String path1="D://xml//InputXml//";
			String path2="D://xml//ScoreXml//";
			File file1=new File(path1+entID+".xml");
			File file2=new File(path2+entID+".xml");
			if(file1.exists()){
				this.copyFile(path1+entID+".xml", adr+input+result.getUuid()+".xml");
				result.setInputXMLTime(new Date());
				result.setInputXMLUrl(input+result.getUuid()+".xml");
			}
			if(file2.exists()){
				this.copyFile(path2+entID+".xml", adr+score+result.getUuid()+".xml");
				result.setScoreXMLTime(new Date());
				result.setScoreXMLUrl(score+result.getUuid()+".xml");
			}
			result.setCustomer(customer);
			entResultService.update(result);
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
	
	public Object transToBean(String xmlPath,Class<?> load)throws JAXBException, IOException{
        Object object=XmlToBean.xmlToBean(xmlPath,load);
        return object;
	}
}
