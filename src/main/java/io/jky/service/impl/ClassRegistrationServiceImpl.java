package io.jky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.jky.dao.ClassRegistrationDao;
import io.jky.entity.ClassRegistrationEntity;
import io.jky.service.ClassRegistrationService;



@Service("classRegistrationService")
public class ClassRegistrationServiceImpl implements ClassRegistrationService {
	@Autowired
	private ClassRegistrationDao classRegistrationDao;
	
	@Override
	public ClassRegistrationEntity queryObject(Long id){
		return classRegistrationDao.queryObject(id);
	}
	
	@Override
	public List<ClassRegistrationEntity> queryList(Map<String, Object> map){
		return classRegistrationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classRegistrationDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassRegistrationEntity classRegistration){
		classRegistrationDao.save(classRegistration);
	}
	
	@Override
	public void update(ClassRegistrationEntity classRegistration){
		classRegistrationDao.update(classRegistration);
	}
	
	@Override
	public void delete(Long id){
		classRegistrationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		classRegistrationDao.deleteBatch(ids);
	}
	
}
