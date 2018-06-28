package com.credit.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.credit.bean.pagelist.QueryResult;
import com.credit.dao.DAO;
import com.credit.util.GenericsUtils;


@SuppressWarnings("unchecked")
@Transactional
public abstract class DaoSupport<T> implements DAO<T>{
	protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	
	
	@PersistenceContext protected EntityManager entityManager;
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public ArrayList<T> getQueryData(String whereSql, Object[] params,LinkedHashMap<String, String> orderby){
		String entityname = getEntityName(entityClass);
		Query query=entityManager.createQuery("select o from "+ entityname +" o "+ (whereSql==null || "".equals(whereSql.trim())? "": "where " + whereSql) + buildOrderby(orderby));
		//query.setHint("org.hibernate.cacheable", true);		
		setQueryParams(query, params);
		ArrayList<T> list = (ArrayList<T>) query.getResultList();
		return list;
	}

	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public long getCount() {
		return (Long)entityManager.createQuery("select count("+ getCountField(this.entityClass) +") from "+ getEntityName(this.entityClass)+ " o").getSingleResult();
	}

	public void clear(){
		entityManager.clear();
	}
	
	public void close(){
		entityManager.close();
	}
	
	public void flush(){
		entityManager.flush();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(Object entity) {
		entityManager.merge(entity);		
	}	

	public void delete(Serializable ... entityids) {
		for(Serializable id : entityids){
			entityManager.remove(entityManager.getReference(this.entityClass, id));
		}
	}

	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public T find(Serializable entityId) {
		if(entityId==null) throw new RuntimeException(this.entityClass.getName()+ ":传入的实体id不能为空");
		return entityManager.find(this.entityClass, entityId);
	}	
	
	
	

	
	

	protected static void setQueryParams(Query query,Object[] queryParams){
		if(queryParams!=null&&queryParams.length>0){
			for(int i=0;i<queryParams.length;i++){
				query.setParameter(i+1, queryParams[i]);
			}
		}
	}
	
	protected static void setQueryParams(Query query,HashMap<String, Object> queryParams){
		for(Entry<String, Object> entry : queryParams.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 组装order by 语句
	 * @param orderby
	 * @return
	 */
	//order by o.key desc,o.key2 asc
	protected static String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			orderbyql.append(" order by ");
			for(String key : orderby.keySet()){
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");				
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}
	/**
	 * 获取实体的名称
	 * @param <T>
	 * @param clazz 实体类
	 * @return
	 */
	protected static <T> String getEntityName(Class<T> entityClass){
		String entityname = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if(entity.name()!=null && !"".equals(entity.name())){
			entityname = entity.name();
		}
		return entityname;
	}
	
	protected static <T> String getCountField(Class<T> entityClass){
		String out = "o";
		try {
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(entityClass).getPropertyDescriptors();
			for(PropertyDescriptor propertydesc : propertyDescriptors){
				Method method = propertydesc.getReadMethod();
				if(method!=null && method.isAnnotationPresent(EmbeddedId.class)){	
					PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
					out = "o."+ propertydesc.getName()+ "." + (!ps[1].getName().equals("class")? ps[1].getName(): ps[0].getName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return out;
	}
	
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(firstindex,maxresult,null,null,orderby);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
		return getScrollData(firstindex,maxresult,wherejpql,queryParams,null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, int maxresult) {
		return getScrollData(firstindex,maxresult,null,null,null);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData() {
		return getScrollData(-1, -1);
	}

	
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public QueryResult<T> getScrollData(int firstindex, 
			int maxresult, String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		
		@SuppressWarnings("rawtypes")
		QueryResult qr = new QueryResult<T>();		
		String entityname = getEntityName(entityClass);
		
		Query query=entityManager.createQuery("select o from "+ entityname +" o "+ (wherejpql==null || "".equals(wherejpql.trim())? "": "where " + wherejpql) + buildOrderby(orderby));
		setQueryParams(query, queryParams);
		if (firstindex != -1 && maxresult != -1)
			query.setFirstResult(firstindex);
		if (firstindex != -1 && maxresult != -1)
			query.setMaxResults(maxresult);
		qr.setResultlist(query.getResultList());
		query = entityManager.createQuery("select count("	+ getCountField(this.entityClass) + ") from "+ entityname + " o " + (wherejpql == null || "".equals(wherejpql.trim()) ? "": "where " + wherejpql));
		setQueryParams(query, queryParams);
		qr.setTotalrecord((Long) query.getSingleResult());
		
			
		return qr;
	}
	
}
