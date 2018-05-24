/** 
 * <pre>项目名称:four_group 
 * 文件名称:RoleServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月12日下午8:27:19 
 * Copyright (c) 2018, 1103304339@qq.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.RoleMapper;
import com.four.model.Role;
import com.four.service.RoleService;

/** 
 * <pre>项目名称：four_group    
 * 类名称：RoleServiceImpl    
 * 类描述：    
 * 创建人：苑鹏飞  1103304339@qq.com   
 * 创建时间：2018年3月12日 下午8:27:19    
 * 修改人：苑鹏飞  1103304339@qq.com      
 * 修改时间：2018年3月12日 下午8:27:19    
 * 修改备注：       实现角色接口
 * @version </pre>    
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleDao;
	@Override
	public Map<String,Object> queryRole(Role role, Integer rows, Integer page) {
//		查询角色信息的总条数
		long total=roleDao.querycount(role);
//		计算分页
		int start = page*rows-rows;
//		查询分页
		List<Role> list= roleDao.queryRole(role,start,rows);
		Map<String,Object> jss= new HashMap<String,Object>();
		jss.put("total", total);
		jss.put("rows", list);
		return jss;
	}

//	角色新增
	@Override
	public int insertRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.insertRole(role);
	}

//	角色删除批量删
	@Override
	public Integer deleteRoleByIDs(String ids) {
		String[] split = ids.split(",");
		return roleDao.deleteRoleByIDs(split);
	}
//  角色修改的回显
	@Override
	public Role toRoleById(Role role) {
		
		return roleDao.toRoleById(role);
	}

//	根据ID修改选择的角色信息
	@Override
	public void updateRoleById(Role role) {
		roleDao.updateRoleById(role);
	}


}
