package com.credit.service.member.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;

import com.credit.bean.member.User;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.member.UserService;
import com.credit.util.MD5Code;

@Service
@Transactional
public class UserServiceBean extends DaoSupport<User> implements UserService {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(UserServiceBean.class);
	@Resource
	PrivilegeGroupService privilegeGroupService;
	@Resource
	ProcessStateService processService;

	@Override
	public void delete(Serializable... entityids) {
		logger.info("UserService_delete;entityids:" + entityids);
		for (Serializable id : entityids) {
			User user = entityManager.find(User.class, id);
			user.getGroups().clear();
			user.getDepartments().clear();

			entityManager.remove(user);
		}
	}

	public void batchDelete(List<User> users) {
		logger.info("UserService_batchDelete;");
		for (User u : users) {
			this.delete(u.getUserName());
		}

	}

	public void updatePassword(String userName, String newPassword) {
		logger.info("UserService_updatePassword;userName:" + userName
				+ ";newPassword:" + newPassword);
		MD5Code md5 = new MD5Code();
		entityManager
				.createQuery(
						"update com.credit.bean.member.User o set o.password=?1 where o.userName=?2")
				.setParameter(1, md5.getMD5ofStr(newPassword))
				.setParameter(2, userName).executeUpdate();
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value) {
		logger.info("UserService_exist;field:" + field + ";value:" + value);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.member.User o where o."
								+ field + "=?1").setParameter(1, value)
				.getSingleResult();
		return count > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value, String value2) {
		logger.info("UserService_exist;field:" + field + ";value:" + value
				+ ";value2:" + value2);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.member.User o where o."
								+ field + "=?1 and o.userName!=?2")
				.setParameter(1, value).setParameter(2, value2)
				.getSingleResult();

		return count > 0;
	}
	

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean validate(String userName, String password) {
		logger.info("UserService_validate;userName:" + userName + ";password:"
				+ password);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.member.User o where o.userName=?1 and o.password=?2 and visible=1")
				.setParameter(1, userName).setParameter(2, password)
				.getSingleResult();


		return count > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean validateFindpwd(String userName, String email) {
		logger.info("UserService_validateFindpwd;userName:" + userName
				+ ";email:" + email);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.member.User o where o.userName=?1 and o.email=?2")
				.setParameter(1, userName).setParameter(2, email)
				.getSingleResult();
		return count > 0;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<Department> getUserDeps(User user) {
		logger.info("UserService_getUserDeps;user:" + user);
		User u = find(user.getUserName());
		Set<Department> depSet = null;
		if(u != null){
			depSet = u.getDepartments();
			// 取一下部门的数据，否则在懒加载模式下不会加载部门的数据
			for (Department dep : depSet) {
				dep.getUuid();
				dep.getSn();
				dep.getName();
			}
		}
		return depSet;
	}

	public Set<PrivilegeGroup> getUserPrivilegeGroups(User user) {
		logger.info("UserService_getUserPrivilegeGroups;user:" + user);
		User entity = find(user.getUserName());
		Set<PrivilegeGroup> pgSet = entity.getGroups();
		for (PrivilegeGroup pg : pgSet) {
			// 取一下属性，否则在懒加载的情况下默认不会将数据加载进来
			pg.getName();
		}
		return pgSet;
	}

	@SuppressWarnings("unused")
	private Long countReocrd(String sql) {
		// sql = sql.replace("select u,e,m", "select count(*)");
		sql = "select count(*) from User u";
		Object obj = this.entityManager.createQuery(sql).getSingleResult();
		return (Long) obj;
	}

	public Set<SystemPrivilege> getSystemPrivilegesOnlyUnderUser(User user) {
		logger.info("UserService_getSystemPrivilegesOnlyUnderUser;user:" + user);
		User entity = find(user.getUserName());
		Set<SystemPrivilege> spSet = entity.getSystemPrivileges();
		for (SystemPrivilege sp : spSet) {
			// 取一下属性，否则在懒加载的情况下默认不会将数据加载进来
			sp.getId().getModel();
			sp.getId().getPrivilegeValue();
			sp.getName();
		}
		return spSet;
	}

	public Set<SystemPrivilege> getUserSystemPrivileges(User user) {
		logger.info("UserService_getUserSystemPrivileges;user:" + user);
		User entity = find(user.getUserName());
		Set<PrivilegeGroup> roleSet = entity.getGroups();
		Set<SystemPrivilege> rolePgSet = getRoleSPs(roleSet);
		Set<SystemPrivilege> userPgSet = entity.getSystemPrivileges();
		return getAllSPs(rolePgSet, userPgSet);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<SystemPrivilege> getRoleSPs(Set<PrivilegeGroup> pgs) {
		logger.info("UserService_getRoleSPs;pgs:" + pgs);
		Set<SystemPrivilege> roleSPs = new HashSet<SystemPrivilege>();
		for (Iterator<PrivilegeGroup> it = pgs.iterator(); it.hasNext();) {
			PrivilegeGroup pg = it.next();
			for (SystemPrivilege sp : pg.getSystemPrivileges()) {
				roleSPs.add(sp);
			}
		}

		return roleSPs;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<SystemPrivilege> getAllSPs(Set<SystemPrivilege> sps1,
			Set<SystemPrivilege> sps2) {
		logger.info("UserService_getAllSPs;sps1:" + sps1 + ";sps2:" + sps2);
		Set<SystemPrivilege> allSPs = new HashSet<SystemPrivilege>();
		for (SystemPrivilege sp : sps1) {
			allSPs.add(sp);
		}

		for (SystemPrivilege sp : sps2) {
			allSPs.add(sp);
		}

		return allSPs;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<PrivilegeGroup> getUserRolesOfUserName(String userName) {
		logger.info("UserService_getUserRolesOfUserName;userName:" + userName);
		Set<PrivilegeGroup> roles = new HashSet<PrivilegeGroup>();
		Query query = entityManager.createNativeQuery(
				"select groupID from  TP_USER_ROLE o where o.userName=?1")
				.setParameter(1, userName);
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				String s = (String) lists.get(i);
				PrivilegeGroup pg = privilegeGroupService
						.getPrivilegeGroupOfUuid(s);
				roles.add(pg);
			}
		}
		return roles;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<SystemPrivilege> getUserSPsOfUserName(String userName) {
		logger.info("UserService_getUserSPsOfUserName;userName:" + userName);
		Set<SystemPrivilege> sps = new HashSet<SystemPrivilege>();
		Query query = entityManager
				.createNativeQuery(
						"select userID,model,privilegeValue from  TP_USER_PRIVILEGE o where o.userID=?1")
				.setParameter(1, userName);
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				Object[] cells = (Object[]) lists.get(i);
				SystemPrivilege sp = new SystemPrivilege();
				sp.setName(cells[0].toString());
				sp.setId(new SystemPrivilegePK(cells[1].toString(), cells[2]
						.toString()));
				sps.add(sp);
			}
		}
		return sps;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public User getUserOfUserName(String userName) {
		logger.info("UserService_getUserOfUserName;userName:" + userName);
		User u = new User();
		Query query = entityManager
				.createQuery(
						"select o from com.credit.bean.member.User o where o.userName=?1")
				.setParameter(1, userName);
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			u = (User) lists.get(0);
		}
		return u;
	}

	public User findByEnterpriseID(String enterpriseID) {
		logger.info("UserService_findByEnterpriseID;enterpriseID:"
				+ enterpriseID);
		User user = null;
		Query query = entityManager
				.createNativeQuery("select * from tp_user  where enterprise_infoid=?1",User.class);
		query.setParameter(1, enterpriseID);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public void  deleteByUserNameGetRole(String userName) {
		logger.info("UserService_findByUserNameGetRole;userName:"
				+ userName);
		entityManager.createNativeQuery("delete from tp_user_role  where username=?1").setParameter(1, userName).executeUpdate();
	}

}
