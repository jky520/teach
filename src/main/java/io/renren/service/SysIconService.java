package io.renren.service;

import io.renren.entity.SysIconEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-22 17:19:10
 */
public interface SysIconService {
	
	SysIconEntity queryObject(Long id);
	
	List<SysIconEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysIconEntity sysIcon);
	
	void update(SysIconEntity sysIcon);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
