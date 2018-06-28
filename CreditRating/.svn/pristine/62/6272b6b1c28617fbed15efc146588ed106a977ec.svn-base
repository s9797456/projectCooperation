package com.credit.controller.dataIO;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.member.Customer;
import com.credit.bean.transferBean.MainPerson;
import com.credit.bean.transferBean.MainPersonList;
import com.credit.bean.transferBean.ShareHolder;
import com.credit.bean.transferBean.ShareHolderList;
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
import com.credit.util.MD5Code;
import com.credit.util.Excel.ReadOfData;

@Controller
@RequestMapping("/initial/import/excel")
public class ImportFromExcel {

	@Resource(name="uploadFileServiceBean")
	private UploadFileService uploadFileService;
	
	@Resource(name="entBaseInfoServiceBean")
	private EntBaseInfoService entBaseInfoService;
	
	@Resource(name="customerServiceBean")
	private CustomerService customerService;
	
	@Resource(name="executivesServiceBean")
	private ExecutivesService executivesService;
	
	@Resource(name="shareholderServiceBean")
	private ShareholderService shareholderService;
	
	@Resource(name="modelServiceBean")
	private ModelService modelService;
	
	@Resource(name="entResultServiceBean")
	private EntResultService entResultService;
	
	@Resource(name="financeServiceBean")
	private FinanceService financeService;
	
	@Resource(name="processStateServiceBean")
	private ProcessStateService processStateService;
	
	/**
	 * Description:导入企业数据
	 * @author 严树炜
	 * @date 2017-10-10
	 */
	@RequestMapping("/entBaseInfo")
	public void TransferEntBaseInfo(String path) throws JAXBException, IOException, ParseException{
		List<EntBaseInfo> ents = new ReadOfData().readEntBaseInfoExcel(path);
		for (EntBaseInfo e : ents) {
			List<EntBaseInfo> list= entBaseInfoService.selectByName(e.getName());
			int  count=0;
			if(!list.isEmpty()){//如果企业名重复 去除关联关系并删除
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
			System.out.println("删除企业总数："+count);
			String sid=entBaseInfoService.selectByEntName(e.getName());
			if(sid==null){
				e.setUuid(UUID.randomUUID().toString().replace("-", ""));
				e.setCreateTime(new Date());
				e.setUpdateTime(new Date());
				System.out.println(e.getSetupDate());
				entBaseInfoService.save(e);
				
				Customer customer = new Customer();
				customer.setUserName("user"+(int)((Math.random()*9+1)*100000));
				customer.setEntBaseInfo(e);
				MD5Code md5 = new MD5Code();
				customer.setPassword(md5.getMD5ofStr("123456"));
				customer.setLastLoginIP("127.0.0.1");
				customer.setLastLoginTime(new Date());
				customer.setLoginTimes(1);
				customer.setRegIP("127.0.0.1");
				customer.setRegTime(new Date());
				customer.setType(0);
				customer.setUpdateTime(new Date());
				customer.setVisible(1);
				customerService.save(customer);
				
				EntResult entResult = new EntResult();
				entResult.setUuid(UUID.randomUUID().toString().replace("-", ""));
				entResult.setEntBaseInfo(e);
				entResult.setCustomer(customer);
				entResultService.save(entResult);
			}
		}
	}
	
	/**
	 * Description:导入高管数据
	 * @author 严树炜
	 * @date 2017-10-10
	 */
	@RequestMapping("/executives")
	public void TransferExecutives(String path) throws JAXBException, IOException, ParseException{
		List<Executives> executives = new ReadOfData().readExecutivesExcel(path);
		List<Executives> list = new ArrayList<Executives>();
		int count = 0;
		for (Executives m : executives) {
			String entId = entBaseInfoService.selectByEntName(m.getEntID());
			List<Executives> exs=executivesService.findAllByEntID(entId);
			if(!exs.isEmpty()&&count!=exs.size()){//如果查询到的该企业的高管数量跟插入的高管数量不一致，全部清空
				for(Executives ex:exs){
					executivesService.delete(ex.getUuid());
				}
			}
			if(entId!=null && entBaseInfoService.selectById(entId)!=null){
				m.setUuid(UUID.randomUUID().toString().replace("-", ""));
				m.setEntID(entId);
				executivesService.save(m);
				count++;
			}else{
				list.add(m);
			}
		}
	}
	
	/**
	 * Description:导入股东数据
	 * @author 严树炜
	 * @throws ParseException 
	 * @date 2017-10-10
	 */
	@RequestMapping("/shareHolder")
	public void TransferShareHolder(String path) throws JAXBException, IOException, ParseException{
		List<Shareholder> shs = new ReadOfData().readShareholderExcel(path);
		List<Shareholder> list = new ArrayList<Shareholder>();
		int count = 0;
		for (Shareholder sh : shs) {
			String entId = entBaseInfoService.selectByEntName(sh.getEntID());
			List<Shareholder> exs=shareholderService.findAllByEntID(entId);
			if(!exs.isEmpty()&&count!=exs.size()){//如果查询到的该企业的高管数量跟插入的高管数量不一致，全部清空？存在问题，如果该企业已部分股东数据
				for(Shareholder ex:exs){
					shareholderService.delete(ex.getUuid());
				}
			}
			if(entId!=null && entBaseInfoService.find(entId)!=null){
				sh.setEntID(entId);
				sh.setUuid(UUID.randomUUID().toString().replace("-", ""));
				shareholderService.save(sh);
				count++;
			}else{
				list.add(sh);
			}
		}
	}
}
