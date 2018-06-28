/**
 * 
 */
package com.credit.service.person.impl;

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

import com.credit.mapper.person.PerResultMapper;
import com.credit.model.person.PerResult;
import com.credit.service.person.PerResultService;
import com.credit.util.model.Index;
import com.credit.util.model.IndexRateVo;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PerResultServiceBean implements PerResultService {

	private static final Logger logger = Logger.getLogger(PerResultServiceBean.class);
	@Resource
	private PerResultMapper mapper;
	
	/* (non-Javadoc)
	 * @see com.credit.service.person.PerResultService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerResultService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerResultService#insert(com.credit.model.person.PerResult)
	 */
	@Override
	public int insert(PerResult record) {
		logger.info("PerResultService _ insert;record:" + record);
		return mapper.insert(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerResultService#insertSelective(com.credit.model.person.PerResult)
	 */
	@Override
	public int insertSelective(PerResult record) {
		logger.info("PerResultService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerResultService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public PerResult selectByPrimaryKey(String uuid) {
		logger.info("PerResultService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerResultService#updateByPrimaryKeySelective(com.credit.model.person.PerResult)
	 */
	@Override
	public int updateByPrimaryKeySelective(PerResult record) {
		logger.info("PerResultService _ updateByPrimaryKeySelective;uuid:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerResultService#updateByPrimaryKey(com.credit.model.person.PerResult)
	 */
	@Override
	public int updateByPrimaryKey(PerResult record) {
		logger.info("PerResultService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PerResult selectByPerID(String perid) {
		logger.info("PerResultService _ selectByPerID;perid:" + perid);
		return mapper.selectByPerID(perid);
	}

	@Override
	public List<IndexRateVo> getIndexRate(String scoreXMLUrl) {
		logger.info("PerResultService _ getIndexRate;scoreXMLUrl:" + scoreXMLUrl);
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

}
