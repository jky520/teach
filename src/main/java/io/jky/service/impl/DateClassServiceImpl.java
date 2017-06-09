package io.jky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.jky.dao.DateClassDao;
import io.jky.entity.DateClassEntity;
import io.jky.service.DateClassService;



@Service("dateClassService")
public class DateClassServiceImpl implements DateClassService {
	@Autowired
	private DateClassDao dateClassDao;
	
	@Override
	public DateClassEntity queryObject(Long id){
		return dateClassDao.queryObject(id);
	}
	
	@Override
	public List<DateClassEntity> queryList(Map<String, Object> map){
		return dateClassDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dateClassDao.queryTotal(map);
	}
	
	@Override
	public void save(DateClassEntity dateClass){
		dateClassDao.save(dateClass);
	}
	
	@Override
	public void update(DateClassEntity dateClass){
		dateClassDao.update(dateClass);
	}
	
	@Override
	public void delete(Long id){
		dateClassDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		dateClassDao.deleteBatch(ids);
	}

	@Override
	public DateClassEntity getObjectByName(String name) {
		return dateClassDao.getObjectByName(name);
	}
	
}
