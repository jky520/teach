package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.SysIconDao;
import io.renren.entity.SysIconEntity;
import io.renren.service.SysIconService;



@Service("sysIconService")
public class SysIconServiceImpl implements SysIconService {
	@Autowired
	private SysIconDao sysIconDao;
	
	@Override
	public SysIconEntity queryObject(Long id){
		return sysIconDao.queryObject(id);
	}
	
	@Override
	public List<SysIconEntity> queryList(Map<String, Object> map){
		return sysIconDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysIconDao.queryTotal(map);
	}
	
	@Override
	public void save(SysIconEntity sysIcon){
		sysIconDao.save(sysIcon);
	}
	
	@Override
	public void update(SysIconEntity sysIcon){
		sysIconDao.update(sysIcon);
	}
	
	@Override
	public void delete(Long id){
		sysIconDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysIconDao.deleteBatch(ids);
	}
	
}
