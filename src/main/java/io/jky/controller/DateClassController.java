package io.jky.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.jky.entity.DateClassEntity;
import io.jky.service.DateClassService;
import io.renren.utils.DateUtils;
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
@RequestMapping("jky/dateclass")
public class DateClassController {
	@Autowired
	private DateClassService dateClassService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("dateclass:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<DateClassEntity> dateClassList = dateClassService.queryList(map);
		int total = dateClassService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(dateClassList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("dateclass:info")
	public R info(@PathVariable("id") Long id){
		DateClassEntity dateClass = dateClassService.queryObject(id);
		
		return R.ok().put("dateClass", dateClass);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("dateclass:save")
	public R save(@RequestBody DateClassEntity dateClass){
		dateClass.setCreateAt(new Date());
		/*Long userId = ShiroUtils.getUserId();
		dateClass.setUserId(userId);*/
		dateClassService.save(dateClass);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("dateclass:update")
	public R update(@RequestBody DateClassEntity dateClass){
		dateClassService.update(dateClass);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("dateclass:delete")
	public R delete(@RequestBody Long[] ids){
		dateClassService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
