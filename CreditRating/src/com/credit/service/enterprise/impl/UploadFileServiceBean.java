package com.credit.service.enterprise.impl;


import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.enterprise.UploadFile;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.enterprise.UploadFileVO;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.UploadFileService;
import com.credit.util.FileUtil;
import com.credit.util.properties.BusinessUtil;


@Service
@Transactional
public class UploadFileServiceBean extends DaoSupport<UploadFile> implements UploadFileService {
	
	public QueryResult<UploadFileVO> findEntUploadFiles(Map<String, String> searchpParams, String entID, int page,int limit) {
		QueryResult<UploadFileVO> p = new QueryResult<UploadFileVO>();
		StringBuffer sb = new StringBuffer(
				"SELECT s.name,o.fileName,o.fileSize,o.fileUrl FROM TB_UploadFileCategory s  ");
		sb.append("INNER JOIN TE_UploadFile o ON s.uuid = o.uploadFileCategoryID and o.entID=?1 ");
		for (Map.Entry<String, String> entry : searchpParams.entrySet()) {
			sb.append("and s." + entry.getKey() + " like '%" + entry.getValue()
					+ "%'");
		}
		p.setTotalrecord(this.countReocrd(sb.toString(), entID));
		if (p.getTotalrecord() > 0) {
			p.setResultlist(this.findLimitRecond(sb.toString(), page, limit,entID));
		}
		return p;
	}
	private Long countReocrd(String sql, String entID) {
		sql = sql.replace("SELECT s.name,o.fileName,o.fileSize,o.fileUrl",
				"select count(*)");
		Object obj = this.entityManager.createNativeQuery(sql)
				.setParameter(1, entID).getSingleResult();
		return (new BigInteger(obj.toString())).longValue();
	}

	@SuppressWarnings("unchecked")
	private List<UploadFileVO> findLimitRecond(String sql, int page,int limit, String entID) {
		String root=BusinessUtil.getMsg("root");
		List<UploadFileVO> operaScanFileVos = new ArrayList<UploadFileVO>();
		List<Object[]> objs = this.entityManager.createNativeQuery(sql)
				.setParameter(1, entID)
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
	public List<UploadFile> findAllByEntID(String entID) {
		Query query = entityManager
				.createQuery(
						"select o from UploadFile o where o.EntID = ?1 ")
				.setParameter(1, entID);
		return query.getResultList();
	}
	
	public int exsit(String entId) {
		Query query = entityManager.createQuery("select o from UploadFile o where o.EntID = ?1").setParameter(1, entId);
		return query.getResultList().size()>0?1:0;
	}

}
