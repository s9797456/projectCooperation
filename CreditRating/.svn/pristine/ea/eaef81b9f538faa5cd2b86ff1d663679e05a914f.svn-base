/**
 * 
 */
package com.credit.service.addition.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.addition.Template;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.addition.TemplateService;
import com.credit.util.tree.SimpleTreeNode;

/**
 * @Title: TemplateServiceBean.java
 * @author Administrator @date 2017-7-27 
 * @Description: TODO
 */
@Service
@Transactional
public class TemplateServiceBean extends DaoSupport<Template> implements TemplateService {

	private static final Logger logger = Logger.getLogger(TemplateServiceBean.class);
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value) {
		logger.info("TemplateService_exist;field:" + field + ";value:" + value);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.addition.Template o where o."
						+ field + "=?1").setParameter(1, value).getSingleResult();
		return count > 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<SimpleTreeNode> allChildrenTreeNode(Template t) {
		logger.info("ReportTemplateCategoryService_allChildrenTreeNode;t:" + t);
		Set<SimpleTreeNode> children = new HashSet<SimpleTreeNode>();
		for (Template childrenRtc : t.getChilds()) {
			SimpleTreeNode childrenTreeNode = new SimpleTreeNode();
			childrenTreeNode.setId(childrenRtc.getUuid());
			childrenTreeNode.setText(childrenRtc.getTemplateName());
			childrenTreeNode.setExpanded(true);
			// childrenTreeNode.setLeaf(false);
			if (childrenRtc.getChilds().size() == 0) {
				childrenTreeNode.setLeaf(true);
			} else {
				childrenTreeNode.setLeaf(false);
				childrenTreeNode.setChildren(this
						.allChildrenTreeNode(childrenRtc));
			}
			children.add(childrenTreeNode);
		}
		return children;
	}

	public Template SelectByName(String name) {
		logger.info("ReportTemplateCategoryService_SelectByName;name:" + name);
		String uuid = (String) entityManager.createQuery("select uuid from com.credit.bean.addition.Template o where o.templateName=?1").setParameter(1, name).getSingleResult();
		return this.find(uuid);
	}

}
