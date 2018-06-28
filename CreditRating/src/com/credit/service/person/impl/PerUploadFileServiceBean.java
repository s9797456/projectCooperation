package com.credit.service.person.impl;



import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.person.PerUploadFile;
import com.credit.bean.vo.enterprise.UploadFileVO;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.person.PerUploadFileService;
import com.credit.util.properties.BusinessUtil;


@Service
@Transactional
public class PerUploadFileServiceBean extends DaoSupport<PerUploadFile> implements PerUploadFileService {

	public QueryResult<UploadFileVO> findPerUploadFiles(
			Map<String, String> searchpParams, String perID, int page, int limit) {
		QueryResult<UploadFileVO> p = new QueryResult<UploadFileVO>();
		StringBuffer sb = new StringBuffer(
				"SELECT s.name,o.fileName,o.fileSize,o.fileUrl FROM TB_UploadFileCategory s  ");
		sb.append("INNER JOIN TI_PerUploadFile o ON s.uuid = o.uploadFileCategoryID and o.perID=?1 ");
		for (Map.Entry<String, String> entry : searchpParams.entrySet()) {
			sb.append("and s." + entry.getKey() + " like '%" + entry.getValue()
					+ "%'");
		}
		p.setTotalrecord(this.countReocrd(sb.toString(), perID));
		if (p.getTotalrecord() > 0) {
			p.setResultlist(this.findLimitRecond(sb.toString(), page, limit,perID));
		}
		return p;
	}
	private Long countReocrd(String sql, String perID) {
		sql = sql.replace("SELECT s.name,o.fileName,o.fileSize,o.fileUrl",
				"select count(*)");
		Object obj = this.entityManager.createNativeQuery(sql)
				.setParameter(1, perID).getSingleResult();
		return (new BigInteger(obj.toString())).longValue();
	}

	@SuppressWarnings("unchecked")
	private List<UploadFileVO> findLimitRecond(String sql, int page,int limit, String perID) {
		String root=BusinessUtil.getMsg("root");
		List<UploadFileVO> operaScanFileVos = new ArrayList<UploadFileVO>();
		List<Object[]> objs = this.entityManager.createNativeQuery(sql)
				.setParameter(1, perID)
				.setFirstResult((page - 1) * limit).setMaxResults(limit)
				.getResultList();
		for (Object[] obj : objs) {
			UploadFileVO v = new UploadFileVO();
			v.setType((String) obj[0]);
			v.setFileName((String) obj[1]);
			v.setFileSize(new BigInteger(obj[2].toString()).longValue());
			String path=(String) obj[3];
			v.setFileUrl(root+path);
			operaScanFileVos.add(v);
		}
		return operaScanFileVos;
	}
	public List<PerUploadFile> findAllByPerID(String uuid) {
		Query query = entityManager.createQuery("select o from PerUploadFile o where o.PerID = ?1 ").setParameter(1, uuid);
		return query.getResultList();
	}


}
