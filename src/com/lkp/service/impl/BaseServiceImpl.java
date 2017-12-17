package com.lkp.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.lkp.dao.BaseDao;
import com.lkp.service.BaseService;
/**
 * @date 2017-10-28下午9:04:01
 * @author lkp
 * @mail 1253364701@qq.com
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired protected BaseDao<T> baseDao;
	
	public void add(T obj) {
		baseDao.add(obj);
	}
	
	public void delete(String id) {
		baseDao.delete(id);
	}
	
	public void delete(Long id) {
		baseDao.delete(id);
	}
	
	public void delete(T obj) {
		baseDao.delete(obj);
	}
	
	@Transactional(readOnly = true)
	public T get(String id) {
		T obj=baseDao.get(id);
		return obj;
	}
	
	@Transactional(readOnly = true)
	public T get(Long id) {
		T obj=baseDao.get(id);
		return obj;
	}

	public void update(T obj) {
		baseDao.update(obj);
	}
	
	@Transactional(readOnly = true)
	public List<T> find(DetachedCriteria criteria) {
		return baseDao.find(criteria);
	}
	
//	@Transactional(readOnly = true)
//	public void find(Pagination<T> pagin) {
//		baseDao.find(pagin);
//	}
//	
//	@Transactional(readOnly = true)
//	public void find(T obj,Pagination<T> pagin) {
//		baseDao.find(obj,pagin);
//	}
	
	@Transactional(readOnly = true)
	public void queryLog(){
		
	}
}
