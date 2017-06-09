package io.jky.service;

import io.jky.entity.DateClassEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jky
 * @email jky1988@qq.com
 * @date 2017-06-05 16:30:12
 */
public interface DateClassService {
	
	DateClassEntity queryObject(Long id);
	
	DateClassEntity getObjectByName(String name);
	
	List<DateClassEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DateClassEntity dateClass);
	
	void update(DateClassEntity dateClass);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
