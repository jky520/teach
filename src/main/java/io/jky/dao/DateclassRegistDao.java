package io.jky.dao;

import java.util.List;

import io.jky.entity.DateclassRegistEntity;
import io.renren.dao.BaseDao;

public interface DateclassRegistDao extends BaseDao<DateclassRegistEntity> {
	/**
	 * 根据用户ID和时间分类ID，获取课时Id列表
	 */
	List<Long> queryDcIds(DateclassRegistEntity dr);
}
