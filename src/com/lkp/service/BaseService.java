package com.lkp.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 * @date 2017-10-28下午9:03:34
 * @author lkp
 * @mail 1253364701@qq.com
 */
public interface BaseService<T> {
	
	
	public void queryLog();	//默认方法添加日志用

	public T get(String id);
	
	public T get(Long id);
	
	public void add(T obj);
	
	public void update(T obj);
	
	public void delete(String id);
	
	public void delete(Long id);
	
	public void delete(T po);
	
	public List<?> find(DetachedCriteria criteria);
	
//	public void find(Pagination<T> pagin);
//	
//	public void find(T obj,Pagination<T> pagin);
	
}
