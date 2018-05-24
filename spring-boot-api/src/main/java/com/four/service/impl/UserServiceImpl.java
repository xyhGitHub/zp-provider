/** 
 * <pre>项目名称:single_code 
 * 文件名称:UserServiceImpl.java 
 * 包名:com.lws.service.impl 
 * 创建日期:2018年3月7日下午10:59:43 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.IUserMapper;
import com.four.model.Role;
import com.four.model.User;
import com.four.service.IUserService;
import com.four.util.CommonUtil;
import com.four.util.MD5;

/** 
 * <pre>项目名称：single_code    
 * 类名称：UserServiceImpl    
 * 类描述：    
 * 创建人：刘文圣  
 * 创建时间：2018年3月7日 下午10:59:43    
 * 修改人：刘文圣
 * 修改时间：2018年3月7日 下午10:59:43    
 * 修改备注：       
 * @version </pre>    
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserMapper userDao;

	
	public Map<String,Object> queryUserList(int startIndex, int pageSize, User user) throws Exception {
		//查询总条数
		int totalCount = userDao.getTotalCount(user);
		//开始条数
		int start = (startIndex - 1)*pageSize;
		List<User> rows = userDao.queryUserList(start,pageSize,user);
		Map<String,Object> jobj = new HashMap<String,Object>();
		jobj.put("total", totalCount);
		jobj.put("rows", rows);
		return jobj;
	}

	
	public void deleteUserByIds(String str) throws Exception {
		List getidList = CommonUtil.getidList(str);
		userDao.deleteUserByIds(getidList);
		
		
	}

	
	public User queryUserById(String id)throws Exception {
		
		return userDao.queryUserById(id);
	}

	
	public void addUserData(User user)throws Exception {
		String md5 = MD5.md5(user.getPwd());
		user.setPwd(md5);
		userDao.addUserData(user);
		
	}

	public void updateUserById(User user)throws Exception {
		String md5 = MD5.md5(user.getPwd());
		user.setPwd(md5);
		userDao.updateUserById(user);
		
	}

	/**
	 * 
	 * <pre>queryRoleByUserid(通过用户ID查询该用户的所有)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月13日 下午7:20:03    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月13日 下午7:20:03    
	 * 修改备注： 
	 * @param id
	 * @return</pre>
	 */
	@Override
	public List<User> queryRoleByUserid(Integer id) throws Exception {
		List<User> queryUserAllByuserId = userDao.queryUserAllByuserId(id);
		return queryUserAllByuserId;
	}

	/* 获取全部role  
	 */
	@Override
	public List<Role> getRoleAll()throws Exception {	
		return userDao.getRoleAll();
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IUserService#saveRolesByUserId(java.lang.Integer, java.lang.String)    
	 */
	@Override
	public void saveRolesByUserId(Integer userId, String roleId)throws Exception {
		// roleId = 1,2
		// roleId = 1
		// roleId  = null
		
		
		if(!"".equals(roleId) & roleId != null){
			List list = CommonUtil.getidList(roleId);
			userDao.deleteByUserId(userId);
			userDao.addRolesByUserId(userId,list);
		}else{
			userDao.deleteByUserId(userId);
		}
		
	}


	/* (non-Javadoc)    
	 * @see com.four.service.IUserService#queryUserResumeList(java.lang.Integer)    
	 */
	@Override
	public User queryUserResumeList(Integer typeidcriteria)throws Exception {
		
		return userDao.queryUserResumeList(typeidcriteria);
	}


	/* (non-Javadoc)    
	 * @see com.four.service.IUserService#lookCompany(java.lang.Integer)    
	 */
	@Override
	public User lookCompany(User u) throws Exception {
		User us = userDao.lookCompany(u);
		return us;
	}


	/* (non-Javadoc)    
	 * @see com.four.service.IUserService#querySeclect()    
	 */
	@Override
	public List<User> querySeclect()throws Exception {
		//
		return userDao.querySeclect();
	}


	/* (non-Javadoc)    
	 * @see com.four.service.IUserService#queryTypeListSelect()    
	 */
	@Override
	public List<User> queryTypeListSelect() throws Exception {
		//
		return userDao.queryTypeListSelect();
	}

}
