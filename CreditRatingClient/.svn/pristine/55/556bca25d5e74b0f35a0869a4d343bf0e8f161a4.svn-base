package com.credit.service.enterprise.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.EntResultMapper;
import com.credit.model.enterprise.EntResult;
import com.credit.service.enterprise.EntResultService;
import com.credit.util.model.Index;
import com.credit.util.model.IndexRateVo;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
@Service
@Transactional
public class EntResultServiceBean implements EntResultService {

	private static final Logger logger = Logger.getLogger(EntResultServiceBean.class);
	
	@Resource
	private EntResultMapper mapper;
	
	@Override
	public EntResult selectByCustomerID(String customerid) {
		logger.info("EntResultService_selectByCustomerID;customerid:" + customerid);
		return mapper.selectByCustomerID(customerid);
	}
	@Override
	public int updateByPrimaryKey(EntResult record) {
		logger.info("EntResultService_updateByPrimaryKey;EntResult");
		return mapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public EntResult selectByEntId(String enterpriseId) {
		logger.info("EntResultService_selectByEntId;enterpriseId:" + enterpriseId);
		return mapper.selectByEntId(enterpriseId);
	}
	@Override
	public List<IndexRateVo> getIndexRate(String scoreXMLUrl) {
			List<IndexRateVo> list=null;
			List<Index> indexs=new ArrayList<Index>();
			File file=new File(scoreXMLUrl);
			System.out.println(scoreXMLUrl);
			if(file.exists()){
				list = new ArrayList<IndexRateVo>();
				try {
					Document document = new SAXReader().read(file);
					indexs=ModelUtil.getIndex(document);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Index index1 : indexs){
					IndexRateVo irv=new IndexRateVo();
					irv.setIndexName(index1.getName());
					double weight=0;
					double score=0;
					for(Index index2 : index1.getChirds()){
						for(Index index3 : index2.getChirds()){
							double weight3=Double.parseDouble(index3.getWeight());
							weight+=weight3;
							for(Option option : index3.getOptions()){
								if(option.getSelected()!=null&&option.getSelected().equals("true")){
									double value=Double.parseDouble(option.getValue());
									score+=value*weight3;
								}
							}
						}
					}
					System.out.println("总权重："+weight+"   总分："+score);
					double rate=(score/weight)*100;
					String indexScore=String.format("%.2f", rate);
					irv.setRateValue(indexScore+"%");
					if(rate>60){
						irv.setDescription("--");
					}else{
						irv.setDescription(index1.getDesc());
					}
					
					list.add(irv);
				}
				Collections.sort(list, new Comparator<IndexRateVo>() {   
		            public int compare(IndexRateVo ds1,IndexRateVo ds2) {  
		            return ds2.getRateValue().compareTo(ds1.getRateValue());   
		            }   
		       }); 
			}else{
				System.out.println("评分XML文件不存在");			
			}
			return list;
		}
	@Override
	public List<EntResult> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}
	@Override
	public EntResult selectByPrimaryKey(String uuid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(uuid);
	}
	@Override
	public EntResult selectByEntAndModel(String entID, String modelID) {
		// TODO Auto-generated method stub
		return mapper.selectByEntAndModel(entID,modelID);
	}

}
