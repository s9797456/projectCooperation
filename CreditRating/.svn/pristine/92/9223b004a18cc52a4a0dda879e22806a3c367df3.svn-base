package com.credit.service.privilege.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.member.User;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.PrivilegeGroupService;

@Service
@Transactional
public class PrivilegeGroupServiceBean extends DaoSupport<PrivilegeGroup>
		implements PrivilegeGroupService {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(PrivilegeGroupServiceBean.class);

	@Override
	public void delete(Serializable... entityids) {
		logger.info("PrivilegeGroupService_delete;entityids:" + entityids);
		for (Serializable id : entityids) {
			PrivilegeGroup group = this.find(id);
			if (group != null) {
				for (User e : group.getUsers()) {
					e.getGroups().remove(group);
				}

				for (Department o : group.getDepartments()) {
					o.getPrivilegeGroups().remove(group);
				}
				entityManager.remove(group);
			}
		}
	}

	public void batchDelete(List<PrivilegeGroup> privilegeGroups) {
		logger.info("PrivilegeGroupService_batchDelete;privilegeGroups:"
				+ privilegeGroups);
		for (PrivilegeGroup pg : privilegeGroups) {
			this.delete(pg.getUuid());
		}

	}

	@Override
	public void save(PrivilegeGroup entity) {
		logger.info("PrivilegeGroupService_save;entity:" + entity);
		((PrivilegeGroup) entity).setUuid(UUID.randomUUID().toString());
		super.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value) {
		logger.info("PrivilegeGroupService_exist;field:" + field + ";value:"
				+ value);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from PrivilegeGroup o where o."
								+ field + "=?1").setParameter(1, value)
				.getSingleResult();
		return count > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value, String value2) {
		logger.info("PrivilegeGroupService_exist;field:" + field + ";value:"
				+ value + ";value2:" + value2);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from PrivilegeGroup o where o."
								+ field + "=?1 and o.uuid!=?2")
				.setParameter(1, value).setParameter(2, value2)
				.getSingleResult();
		return count > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public PrivilegeGroup searchByName(String name) {
		logger.info("PrivilegeGroupService_searchByName;name:" + name);
		PrivilegeGroup pg = (PrivilegeGroup) entityManager
				.createQuery("select o from PrivilegeGroup o where o.name=?1")
				.setParameter(1, name).getSingleResult();
		return pg;
	}

	public Set<SystemPrivilege> allSystemPrivilegeInSomePrivilegeGroup(
			PrivilegeGroup pg) {
		logger.info("PrivilegeGroupService_allSystemPrivilegeInSomePrivilegeGroup;pg:"
				+ pg);
		PrivilegeGroup role = find(pg.getUuid());
		Set<SystemPrivilege> spSet = role.getSystemPrivileges();
		for (SystemPrivilege sp : spSet) {
			// 取一下属性，否则在懒加载的情况下默认不会将数据加载进来
			sp.getId().getModel();
			sp.getId().getPrivilegeValue();
			sp.getName();
		}
		return spSet;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public PrivilegeGroup getPrivilegeGroupOfUuid(String uuid) {
		logger.info("PrivilegeGroupService_getPrivilegeGroupOfUuid;uuid:"
				+ uuid);
		PrivilegeGroup entry = new PrivilegeGroup();
		Query query = entityManager
				.createQuery(
						"select o from com.credit.bean.privilege.PrivilegeGroup o where o.uuid=?1")
				.setParameter(1, uuid);
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			entry = (PrivilegeGroup) lists.get(0);
		}
		return entry;
	}
}
