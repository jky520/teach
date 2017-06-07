package io.jky.service;

import io.jky.entity.ClassRegistrationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jky
 * @email jky1988@qq.com
 * @date 2017-06-05 16:30:12
 */
public interface ClassRegistrationService {
	
	ClassRegistrationEntity queryObject(Long id);
	
	List<ClassRegistrationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassRegistrationEntity classRegistration);
	
	void update(ClassRegistrationEntity classRegistration);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
