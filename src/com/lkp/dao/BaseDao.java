package com.lkp.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * @date 2017-10-23
 * @author lkp
 * @mail 1253364701@qq.com
 */
public interface BaseDao<T> {
	
	public T get(String id);
	
	public T get(Long id);
	
	public void add(T po);
	
	public void update(T po);
	
	public void delete(String id);
	
	public void delete(T po);
	
	public void delete(Long id);
	
	public List<T> find(DetachedCriteria criteria);
	
//	public void find(Pagination<T> pagin);
//	
//	public void find(T obj,Pagination<T> pagin);
}
