package com.credit.service.addition;

import java.util.Set;

import com.credit.bean.addition.Template;
import com.credit.dao.DAO;
import com.credit.util.tree.SimpleTreeNode;

public interface TemplateService extends DAO<Template> {
	/**
	 * @Title 根据属性名称 和属性值 判断对象是否存在
	 * @author  Administrator  @date 2017-7-27 
	 * @Description 
	 *
	 */
	public boolean exist(String field, String value);
	
	public Set<SimpleTreeNode> allChildrenTreeNode(Template t);
	
	public Template SelectByName(String name);
}
