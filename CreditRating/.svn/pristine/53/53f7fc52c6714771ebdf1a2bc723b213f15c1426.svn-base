package com.credit.service.enterprise.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.Finance;
import com.credit.bean.vo.enterprise.FinanceVo;
import com.credit.bean.vo.html2pdf.FinancialData;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.FinanceService;
import com.credit.util.FileUtil;
import com.credit.util.financeExecl.ResolveFinance;
import com.credit.util.properties.BusinessUtil;


@Service
@Transactional
public class FinanceServiceBean extends DaoSupport<Finance> implements FinanceService {
	
	public List<Map<String, String>> reportFinalData(String entID) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		Map<String, String> map3 = new HashMap<String, String>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		StringBuffer sb = new StringBuffer(
				"select o from Finance o where ");
		sb.append("o.EntID = ?1");
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter(1, entID);
		if(query.getResultList().size()>0){
			Finance finance= (Finance) query.getSingleResult();
			if(finance!=null&&finance.getFileurl()!=null){
				String path=adr+finance.getFileurl();
				if(FileUtil.isExist(path)){
					try {
						List<FinanceVo> lists = ResolveFinance.ReadExecl2(path);
						String year1=new String();
		            	String year2=new String();
		            	String year3=new String();
						for(FinanceVo fv : lists){
							String name=fv.getName();
							String value=null;
							if(!"".equals(fv.getValue())){
								value=fv.getValue();
							}
							if(fv.getType()==1){
								map1.put(name, value);
								year1=(int)Double.parseDouble(fv.getYear())+"年";
							}
							if(fv.getType()==2){
								map2.put(name, value);
								year2=(int)Double.parseDouble(fv.getYear())+"年";
							}
							if(fv.getType()==3){
								map3.put(name, value);
								year3=(int)Double.parseDouble(fv.getYear())+"年";
							}
						}
					    map1.put("年份", year1);
					    map2.put("年份", year2);
					    map3.put("年份", year3);
					} catch (IOException e) {
						e.printStackTrace();
					}
					}
			}
			list.add(map1);
			list.add(map2);
			list.add(map3);
		}
		return list;
	}
	
	//资产负债表、利润表
	public List<FinancialData> balanceSheet(String entID,int num) {
		List<FinancialData> list = new ArrayList<FinancialData>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		StringBuffer sb = new StringBuffer(
				"select o from Finance o where ");
		sb.append("o.EntID = ?1");
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter(1, entID);
		if(query.getResultList().size()>0){
			Finance finance= (Finance) query.getSingleResult();
			if(finance!=null&&finance.getFileurl()!=null){
				String path=adr+finance.getFileurl();
				if(FileUtil.isExist(path)){
					try {
						List<FinanceVo> lists = new ArrayList<FinanceVo>();
						if(num == 0){
							lists = ResolveFinance.ReadExecl(path);
						}else if(num == 1){
							lists = ResolveFinance.ReadExecl1(path);
						}
						List<String> names = new ArrayList<String>();
						//资产负债表所有name
						for(FinanceVo fv : lists){
							if(fv.getName()!=null&&!"".equals(fv.getName())) names.add(fv.getName());
						}
						//去重
						Set<String> names2 = new HashSet<String>();
						names2.addAll(names);
						List<String> names3 = new ArrayList<String>();
						names3.addAll(names2);
						
						for (String s : names3) {
							FinancialData fd = new FinancialData();
							for(FinanceVo fv : lists){
								String name = fv.getName();
								String value = fv.getValue();
								String year = fv.getYear().substring(0,4);
								int type = fv.getType();
								if(s.equals(name)){
									fd.setName(name);
									switch(type){
										case 1 : 
											if(value!=null&&!"".equals(value)){
												fd.setValue1(value);
											}else{
												fd.setValue1("-");
											}
											fd.setYear1(year);
											break;
										case 2 : 
											if(value!=null&&!"".equals(value)){
												fd.setValue2(value);
											}else{
												fd.setValue2("-");
											}
											fd.setYear2(year);
											break;
										case 3 : 
											if(value!=null&&!"".equals(value)){
												fd.setValue3(value);
											}else{
												fd.setValue3("-");
											}
											fd.setYear3(year);
											break;
									}
								}
							}
							list.add(fd);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					}
			}
		}
		return list;
	}
	public List<Finance> findAllByEntID(String entID) {
		Query query = entityManager
				.createQuery(
						"select o from Finance o where o.EntID = ?1 ")
				.setParameter(1, entID);
		return query.getResultList();
	}

	public int exsit(String entId) {
		Query query = entityManager.createQuery("select o from Finance o where o.EntID = ?1").setParameter(1, entId);
		return query.getResultList().size()>0?1:0;
	}

}
