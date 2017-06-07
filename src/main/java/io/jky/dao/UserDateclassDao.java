package io.jky.dao;

import io.jky.entity.UserDateclassEntity;
import io.renren.dao.BaseDao;

public interface UserDateclassDao extends BaseDao<UserDateclassEntity> {
	/**
	 * 根据用户ID，获取时间分类ID
	 */
	Long getdcIdByUserId(Long userId);
}
