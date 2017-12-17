package com.lkp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.lkp.dao.BaseDao;
/**
 * 
 * @date 2017-10-28下午9:01:51
 * @author lkp
 * @mail 1253364701@qq.com
 * @param <T>
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Autowired 
	private SessionFactory sessionFactory;
	private Class<T> entityClass;
	
	protected Session openSession() {
		return sessionFactory.openSession();
	}
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		try {
			entityClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch(Exception e) {}
	}
	/**
	 * 获取hibernate的session对象
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void add(T po) {
		getSession().save(po);
//		getSession().flush();
	}
	
	public void update(T po) {
		getSession().update(po);
	}
	
	@SuppressWarnings("unchecked")
	public T get(String id) {
		return (T) getSession().get(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public T get(Long id) {
		return (T) getSession().get(entityClass, id);
	}
	
	public void delete(String id) {
		executeUpdate("delete "+entityClass.getName()+" where id=?",id);
	}
	
	public void delete(T po) {
		getSession().delete(po);
	}
	
	public void delete(Long id) {
		executeUpdate("delete "+entityClass.getName()+" where id=?",id);
	}
	
	public List<T> find(DetachedCriteria detachedCriteria) {
		Criteria criteria=detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}
	
//	@Override
//	public void find(Pagination<T> pagin) {
//		
//		Criteria criteria=pagin.getCriteria().getExecutableCriteria(getSession());
//		long count=countRow(criteria);
//		pagin.setTotalCount(count);
//		
//		//如果只有0条记录,那么无需再查数据库,直接返回一个空list
//		if(count==0) {
//			pagin.setDataList(Collections.EMPTY_LIST);
//		} else {
//			criteria.setProjection(null);
//			criteria.setFirstResult(pagin.getStartResult());
//			criteria.setMaxResults(pagin.getPageSize());
//			pagin.setDataList(criteria.list());
//		}
//	}
	
//	@Override
//	public void find(T obj,Pagination<T> pagin) {
		
//		Criteria criteria=pagin.getCriteria().getExecutableCriteria(getSession());
//		long count=countRow(criteria);
//		pagin.setTotalCount(count);
//		
//		//如果只有0条记录,那么无需再查数据库,直接返回一个空list
//		if(count==0) {
//			pagin.setDataList(Collections.EMPTY_LIST);
//		} else {
//			criteria.setProjection(null);
//			criteria.setFirstResult(pagin.getStartResult());
//			criteria.setMaxResults(pagin.getPageSize());
//			pagin.setDataList(criteria.list());
//		}
//	}
	
	/**
	 * 快速统计Criteria的总行数
	 * @param criteria
	 * @return
	 */
	protected long countRow(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		try {
			List result=criteria.list();
			return (Long)result.get(0);
		} catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * 当field字段的值等于value时返回该对象
	 */
	protected <T> T get(Class<T> clazz,String field,Object value) {
		Criteria criteria=getSession().createCriteria(clazz);
		criteria.add(Restrictions.eq(field, value));
		List<T> result= criteria.list();
		
		if(result==null || result.isEmpty()) return null;
		return result.get(0);
	}
	
	/**
	 * 获取下一个排序号
	 * @param field		排序号实体属性名
	 * @return
	 */
	public Integer getNextSeqNum(String property) {
		Criteria criteria=getCriteria().setProjection(Projections.max(property));
		try {
			List result=criteria.list();
			return Integer.parseInt(result.get(0)+"")+1;	
		} catch(Exception e) {
			return 1;
		}
	}
	
	/**
	 * 获取下一排序号,默认排序属性为:seqNum
	 * @return
	 */
	public Integer getNextSeqNum() {
		return getNextSeqNum("seqNum");
	}
	
	protected Criteria getCriteria() {
		return getSession().createCriteria(entityClass);
	}
	
	protected Criteria getCriteria(Class<?> clazz) {
		return getSession().createCriteria(clazz);
	}
	
	/**
	 * 从配制文件获取命名sql
	 * @param name
	 * @return
	 */
	protected Query getNamedQuery(String name) {
		return getSession().getNamedQuery(name);
	}
	
	/**
	 * 快速执行命名查询
	 * @param hql
	 * @return
	 */
	protected List executeNamedQuery(String name,Object... params) {
		Query query=getSession().getNamedQuery(name);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 快速创建Query对象
	 * @param hql
	 * @return
	 */
	protected Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}
	
	/**
	 * 快速执行hql查询
	 * @param hql
	 * @return
	 */
	protected List executeQuery(String hql,Object... params) {
		Query query=getSession().createQuery(hql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 快速执行hql更新
	 * @param hql
	 * @return
	 */
	protected int executeUpdate(String hql,Object... params) {
		Query query=getSession().createQuery(hql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}
}
