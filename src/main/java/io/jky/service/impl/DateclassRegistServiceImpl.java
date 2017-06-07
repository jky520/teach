package io.jky.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jky.dao.DateclassRegistDao;
import io.jky.entity.DateclassRegistEntity;
import io.jky.service.DateclassRegistService;

@Service("dateclassRegistService")
public class DateclassRegistServiceImpl implements DateclassRegistService {
	
	@Resource
	private DateclassRegistDao dateclassRegistDao;
	
	@Override
	public void save(DateclassRegistEntity dateclassRegist) {
		dateclassRegistDao.save(dateclassRegist);
	}

	@Override
	public List<Long> queryDcIdByUserId(Long userId, Long dcId) {
		DateclassRegistEntity dr = new DateclassRegistEntity();
		dr.setUserId(userId);
		dr.setDcId(dcId);
		return dateclassRegistDao.queryDcIds(dr);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] crId) {
		dateclassRegistDao.deleteBatch(crId);
	}
}
