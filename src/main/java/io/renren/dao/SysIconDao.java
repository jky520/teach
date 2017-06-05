package io.renren.dao;

import java.util.List;

import io.renren.entity.SysIconEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-22 17:19:10
 */
public interface SysIconDao extends BaseDao<SysIconEntity> {
	List<String> list();
}
