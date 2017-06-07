package io.jky.service;

import java.util.List;

import io.jky.entity.DateclassRegistEntity;

public interface DateclassRegistService {
	void save(DateclassRegistEntity dateclassRegist);
	
	List<Long> queryDcIdByUserId(Long userId,Long dcId);
	
	void deleteBatch(Long[] crId);
}
