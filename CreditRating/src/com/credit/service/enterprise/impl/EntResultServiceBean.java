package com.credit.service.enterprise.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.enterprise.EntResultVO;
import com.credit.bean.vo.enterprise.EntScoreVO;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.EntResultService;
import com.credit.util.model.Index;
import com.credit.util.model.IndexRateVo;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;


@Service
@Transactional
public class EntResultServiceBean extends DaoSupport<EntResult> implements EntResultService {

	public EntResult selectByEntID(String entID) {
		Query querySecond = entityManager.createQuery("select o from EntResult o where o.entBaseInfo.uuid=?1");
		querySecond.setParameter(1, entID);
		@SuppressWarnings("unchecked")
		List<EntResult> results = querySecond.getResultList();
		EntResult result = null;
		if (results != null && results.size() > 0) {
			result = results.get(0);
		}
		return result;
	}
	
	public QueryResult<EntScoreVO> findScoreEnterperise(int page,int limit,Map<String, String> params) {
		QueryResult<EntScoreVO> p = new QueryResult<EntScoreVO>();
		StringBuffer sb = new StringBuffer(
				"select e,o,u from EntBaseInfo e,ProcessState o,EntResult u where ");
		sb.append("e.uuid = o.entBaseInfo.uuid and u.entBaseInfo.uuid = e.uuid ");//and u.model.uuid is not null 
		String userName=params.get("userName");
		if(userName!=null&&!userName.equals("")){
			sb.append("and u.customer.userName like "+userName);
		}
		String cellphone=params.get("cellphone");
		if(cellphone!=null&&!cellphone.equals("")){
			sb.append("and u.customer.cellphone like "+cellphone);
		}
		String uscc=params.get("uscc");
		if(uscc!=null&&!uscc.equals("")){
			sb.append("and e.USCC like "+uscc);
		}
		String entName=params.get("entName");
		if(entName!=null&&!entName.equals("")){
			sb.append("and e.name like "+entName);
		}
		String legalPerson=params.get("legalPerson");
		if(legalPerson!=null&&!legalPerson.equals("")){
			sb.append("and e.legalPerson like "+legalPerson);
		}
		sb.append(" order by e.updateTime desc");
		p.setTotalrecord(this.countReocrd(sb.toString()));
		if (p.getTotalrecord() > 0) {
			if(p.getTotalrecord()<=limit){
				page=1;
			}else if(page>p.getTotalrecord()/limit+1){
				page=(int) (p.getTotalrecord()/limit+1);
			}
			p.setResultlist(this.findLimitRecond(sb.toString(), page, limit));
		}
		return p;
	}
	private List<EntScoreVO> findLimitRecond(String sql, int page,int limit) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<EntScoreVO> entvos= new ArrayList<EntScoreVO>();
		@SuppressWarnings("unchecked")
		List<Object[]> objs = this.entityManager.createQuery(sql)
				.setFirstResult((page - 1) * limit).setMaxResults(limit)
				.getResultList();
		for (Object[] obj : objs) {
			EntScoreVO score = new EntScoreVO();
			EntBaseInfo 		entBaseInfo = (EntBaseInfo) obj[0];
			ProcessState 	process = (ProcessState) obj[1];
			EntResult 			result = (EntResult) obj[2];
			
			score.setEntID(entBaseInfo.getUuid());
			score.setEntName(entBaseInfo.getName());
			score.setUscc(entBaseInfo.getUSCC());
			score.setLegalPerson(entBaseInfo.getLegalPerson());
			score.setUpdateTime(sdf.format(entBaseInfo.getUpdateTime()));
			score.setProcessID(process.getUuid());
			score.setApplyReportState(process.getApplyReportState());
			score.setScoreState(process.getScoreState());
			score.setResultID(result.getUuid());
			if(result.getModel() != null){
				score.setModelID(result.getModel().getUuid());
				score.setModelName(result.getModel().getName()+result.getModel().getVersion());
			}
			score.setUserName(result.getCustomer().getUserName());
			score.setCellphone(result.getCustomer().getCellphone());
			
			entvos.add(score);
		}
		return entvos;
	}
	
	private Long countReocrd(String sql) {
		sql = sql.replace("select e,o,u", "select count(*)");
		Object obj = this.entityManager.createQuery(sql).getSingleResult();
		return (Long) obj;
	}
	@SuppressWarnings("unchecked")
	public long countByEncoding() {
		List<String> strs=new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMdd");  
		long count=0;
		strs=(List<String>) entityManager
				.createQuery(
						"SELECT SUBSTRING(o.encoding,6,9) as code FROM EntResult o where o.encoding is not null")
				.getResultList();
		for(String o:strs){
			if(o.equals(sdf.format(new Date()))){
				count++;
			}
		}
		return count;
	}
	
	public List<String> getAdjustOption(EntResult result) {
		List<String> list = new ArrayList<String>();
		String adjustscore=BusinessUtil.getMsg("adjustScores");
		String[] strs=adjustscore.split("#");
		String[] options = null;
		String advantageReason = "";
		String status = "0";
		
		if (result.getAdjustReason() != null&& !"".equals(result.getAdjustReason().trim())) {
			advantageReason = result.getAdjustReason();
			status = "1";
		} else if (result.getAdjustOption() != null&& !"".equals(result.getAdjustOption().trim())) {
			String option=result.getAdjustOption();
			if(option.indexOf(",")!=-1){  
				options = result.getAdjustOption().split(",");
				for (int i = 0; i < strs.length; i++) {
					String[] adjust=strs[i].split("=");
					if (options != null) {
						for (int j = 0; j < options.length; j++) {
							if (adjust[0].trim().equals(options[j].trim())) {
								advantageReason += adjust[0]+ "#,";
							}
						}
					}
				}
			}else{ 
				for (int i = 0; i < strs.length; i++) {
					String[] adjust=strs[i].split("=");
					if (result.getAdjustOption() != null) {
						if (adjust[0].trim().equals(result.getAdjustOption().trim())) {
							advantageReason += adjust[0]+ "#,";
						}
					}
				}

			} 
		} else {
			status = "1";
			advantageReason = "";
		}
		list.add(status);
		list.add(advantageReason);
		return list;
	}

	public List<IndexRateVo> getIndexRate(String scoreXMLUrl) {
		List<IndexRateVo> list=new ArrayList<IndexRateVo>();
		List<Index> indexs=new ArrayList<Index>();
		File file=new File(scoreXMLUrl);
		if(file.exists()){
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
		}else{
			System.out.println("评分XML文件不存在");
			IndexRateVo irv=new IndexRateVo();
			list.add(irv);
		}
		Collections.sort(list, new Comparator<IndexRateVo>() {   
            public int compare(IndexRateVo ds1,IndexRateVo ds2) {  
            return ds2.getRateValue().compareTo(ds1.getRateValue());   
            }   
       });  
		return list;
	}

	public void deleteCompletely(String resultID) {
		EntResult result=this.find(resultID);
		result.getEntBaseInfo().setProcess(null);
		result.setModel(null);
		result.setEntBaseInfo(null);
		result.setCustomer(null);
		this.update(result);
		this.delete(resultID);
	}

	public String selectByModelName(String name) {
		Query query = entityManager.createQuery("select o.uuid from EntResult o where o.model.name=?1").setParameter(1, name);
		return (String) query.getResultList().get(0);
	}

	public String getEncodingByUuid(String uuid) {
		Query query = entityManager.createQuery("select o.encoding from EntResult o where o.uuid=?1").setParameter(1, uuid);
		return (String) query.getResultList().get(0);
	}

	public EntResult getEntResultByEntId(String entId) {
		Query query = entityManager.createQuery("select o from EntResult o where o.entBaseInfo.uuid=?1").setParameter(1, entId);
		return query.getResultList().size()==1?(EntResult) query.getResultList().get(0):null;
	}

	public EntResult selectByEntAndModel(String uuid, String model) {
		Query query = entityManager.createQuery("select o from EntResult o where o.entBaseInfo.uuid=?1 and o.model.uuid=?2 ")
				.setParameter(1, uuid).setParameter(2, model);
		return query.getResultList().size()==1?(EntResult) query.getResultList().get(0):null;
	}

	public EntResult selectModelNameByUserName(String username) {
		Query query = entityManager.createQuery("select o from EntResult o where o.customer.userName=?1").setParameter(1, username);
		return query.getResultList().size()>0?(EntResult) query.getResultList().get(0):null;
	}

}