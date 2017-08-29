package io.jky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import io.jky.dao.ClassRegistrationDao;
import io.jky.dao.DateClassDao;
import io.jky.dao.DateclassRegistDao;
import io.jky.entity.ClassRegistrationEntity;
import io.jky.entity.DateClassEntity;
import io.jky.entity.DateclassRegistEntity;
import io.jky.service.ClassRegistrationService;
import io.jky.service.DateclassRegistService;
import io.renren.utils.FileUtil;
import io.renren.utils.GeneratorDateUtils;
import io.renren.utils.ShiroUtils;



@Service("classRegistrationService")
public class ClassRegistrationServiceImpl implements ClassRegistrationService {
	@Autowired
	private ClassRegistrationDao classRegistrationDao;
	@Autowired
	private DateClassDao dateClassDao;
	@Autowired
	private DateclassRegistDao dateclassRegistDao;
	
	@Autowired
	private DateclassRegistService dateclassRegistService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
	@Override
	public ClassRegistrationEntity queryObject(Long id){
		return classRegistrationDao.queryObject(id);
	}
	
	@Override
	public List<ClassRegistrationEntity> queryList(Map<String, Object> map){
		return classRegistrationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classRegistrationDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassRegistrationEntity classRegistration){
		//classRegistrationDao.save(classRegistration);
		DateClassEntity dce =  dateClassDao.getObjectByName(classRegistration.getYearMoth());
		classRegistration.setTimeClassId(dce.getId());
		//classRegistrationDao.save(classRegistration);
		Long userId = ShiroUtils.getUserId();
		DateclassRegistEntity dre = new DateclassRegistEntity();
		dre.setUserId(userId);
		//dre.setDrId(classRegistration.getId());
		dre.setDcId(dce.getId());
		try {
			new GeneratorDateUtils().generatorData(classRegistrationDao,classRegistration,dateclassRegistDao,dre);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(ClassRegistrationEntity classRegistration){
		classRegistrationDao.update(classRegistration);
	}
	
	@Override
	public void delete(Long id){
		classRegistrationDao.delete(id);
		dateclassRegistDao.deleteBatch(new Long[]{id});
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		classRegistrationDao.deleteBatch(ids);
		dateclassRegistDao.deleteBatch(ids);
	}

	@Override
	public void generatorWord(String n, List<Map<String,Object>> lists, HttpServletResponse response) {
		String url = "f:\\课时登记表.docx";
		//String url = "d:\\hx\\课时登记表.docx";
		try {
			FileUtil.readwriteWord(n,url, lists,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
