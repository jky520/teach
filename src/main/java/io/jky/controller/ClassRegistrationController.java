package io.jky.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.jky.entity.ClassRegistrationEntity;
import io.jky.service.ClassRegistrationService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


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
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("classregistration:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
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
	public R save(ClassRegistrationEntity classRegistration,HttpServletRequest req){
		String params = req.getParameter("endDate");
		String isweek = req.getParameter("isWeek");
		System.out.println(params+"----------"+isweek);
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
	
}
