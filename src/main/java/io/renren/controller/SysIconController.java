package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.SysIconEntity;
import io.renren.service.SysIconService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-22 17:19:10
 */
@Controller
@RequestMapping("sys/icon")
public class SysIconController {
	@Autowired
	private SysIconService sysIconService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sysicon:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<SysIconEntity> sysIconList = sysIconService.queryList(map);
		int total = sysIconService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysIconList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获得所有的icon图标
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllIcon")
	@RequiresPermissions("sysicon:list")
	public R getAllIcon() {
		List<String> list = sysIconService.list();
		return R.ok().put("icons", list);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sysicon:info")
	public R info(@PathVariable("id") Long id){
		SysIconEntity sysIcon = sysIconService.queryObject(id);
		
		return R.ok().put("sysIcon", sysIcon);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sysicon:save")
	public R save(@RequestBody SysIconEntity sysIcon){
		sysIconService.save(sysIcon);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sysicon:update")
	public R update(@RequestBody SysIconEntity sysIcon){
		sysIconService.update(sysIcon);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sysicon:delete")
	public R delete(@RequestBody Long[] ids){
		sysIconService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
