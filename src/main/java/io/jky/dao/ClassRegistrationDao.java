package io.jky.dao;

import java.util.List;
import java.util.Map;

import io.jky.entity.ClassRegistrationEntity;
import io.renren.dao.BaseDao;

/**
 * 
 * 
 * @author jky
 * @email jky1988@qq.com
 * @date 2017-06-05 16:30:12
 */
public interface ClassRegistrationDao extends BaseDao<ClassRegistrationEntity> {
	List<ClassRegistrationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
}
