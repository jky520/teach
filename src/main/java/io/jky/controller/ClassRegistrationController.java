package io.jky.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Controller;

import io.jky.entity.ClassRegistrationEntity;
import io.jky.entity.DateClassEntity;
import io.jky.service.ClassRegistrationService;
import io.jky.service.DateClassService;
import io.jky.service.DateclassRegistService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author jky
 * @email jky1988@qq.com
 * @date 2017-06-05 16:30:12
 */
@Controller
@RequestMapping("jky/classregistration")
public class ClassRegistrationController {
	@Autowired
	private ClassRegistrationService classRegistrationService;
	@Autowired
	private DateClassService dateClassService;
	@Autowired
	private DateclassRegistService dateclassRegistService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("classregistration:list")
	public R list(Integer page, Integer limit,String dt){
		dt = dt != null ? dt :  sdf.format(new Date());
		DateClassEntity dec = dateClassService.getObjectByName(dt);
		Long userId = ShiroUtils.getUserId();
		Long[] crIds = dateclassRegistService.queryDcIdByUserId(userId, dec.getId()).toArray(new Long[]{});
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("ids", crIds);
		
		//查询列表数据
		List<ClassRegistrationEntity> classRegistrationList = classRegistrationService.queryList(map);
		int total = classRegistrationService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(classRegistrationList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("classregistration:info")
	public R info(@PathVariable("id") Long id){
		ClassRegistrationEntity classRegistration = classRegistrationService.queryObject(id);
		
		return R.ok().put("classRegistration", classRegistration);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("classregistration:save")
	public R save(@RequestBody ClassRegistrationEntity classRegistration){
		classRegistrationService.save(classRegistration);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("classregistration:update")
	public R update(@RequestBody ClassRegistrationEntity classRegistration){
		classRegistrationService.update(classRegistration);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("classregistration:delete")
	public R delete(@RequestBody Long[] ids){
		classRegistrationService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
	 * 生成word文档并进行下载
	 */
	@RequestMapping("/down1")
	@RequiresPermissions("classregistration:down")
	public void down1(List<Map<String,Object>> lists, HttpServletResponse response) throws IOException{
		classRegistrationService.generatorWord(lists,response);
	}
	
	/**
	 * 生成word文档并进行下载
	 */
	@ResponseBody
	@RequestMapping("/down")
	@RequiresPermissions("classregistration:down")
	public R down(String dt, HttpServletResponse response) throws IOException{
		String myDate = sdf.format(new Date());
		DateClassEntity dec = dateClassService.getObjectByName(myDate);
		//Long userId = ShiroUtils.getUserId();
		Long userId = 1l;
		Long[] crIds = dateclassRegistService.queryDcIdByUserId(userId, dec.getId()).toArray(new Long[]{});
		Map<String, Object> map = new HashMap<>();
		map.put("ids", crIds);
		List<ClassRegistrationEntity> classRegistrationList = classRegistrationService.queryList(map);
		String teacher = ShiroUtils.getUserEntity().getUsername();
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> lists1 = new ArrayList<Map<String,Object>>();
		
		for(ClassRegistrationEntity cre : classRegistrationList) {
			if(lists.size()<=30) {
				lists.add(getEntityMap(cre,teacher));
			} else {
				lists1.add(getEntityMap(cre,teacher));
			}
		}
		//this.down1(lists1, response);
		classRegistrationService.generatorWord(lists,response);
		
		return R.ok().put("v", lists1);
	}
		
	private Map<String,Object> getEntityMap(ClassRegistrationEntity cre,String t) {
		Map<String,Object> params = new LinkedHashMap<String,Object>();
		params.put("date", cre.getDay());  
	    params.put("week", cre.getWeek());  
	    params.put("sedate", cre.getStartFinishDate());  
	    params.put("class", cre.getClassRoom());  
	    params.put("adress", cre.getAdress());  
	    params.put("content", cre.getContent());  
	    params.put("teacher", t);  
	    params.put("ks", cre.getClassCount());  
		return params;
	}
}
