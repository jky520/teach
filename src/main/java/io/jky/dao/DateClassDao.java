package io.jky.dao;

import io.jky.entity.DateClassEntity;
import io.renren.dao.BaseDao;

/**
 * 
 * 
 * @author jky
 * @email jky1988@qq.com
 * @date 2017-06-05 16:30:12
 */
public interface DateClassDao extends BaseDao<DateClassEntity> {
	DateClassEntity getObjectByName(String name);
}
