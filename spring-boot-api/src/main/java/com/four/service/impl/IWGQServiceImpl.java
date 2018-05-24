/** 
 * <pre>项目名称:four_group 
 * 文件名称:IWGQServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月14日下午4:01:53 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.CompanyMapper;
import com.four.dao.IWGQDao;
import com.four.model.CompanyAndPost;
import com.four.model.Post;
import com.four.model.User;
import com.four.model.WGQPostQiuzhi;
import com.four.service.WGQService;

/** 
 * <pre>项目名称：four_group    
 * 类名称：IWGQServiceImpl    
 * 类描述：    
 * 创建人：JavaScript 
 * 创建时间：2018年3月14日 下午4:01:53    
 * 修改人：JavaScript  
 * 修改时间：2018年3月14日 下午4:01:53    
 * 修改备注：       
 * @version </pre>    
 */
@Service("wgqService")
public class IWGQServiceImpl implements WGQService {

	@Autowired
	private IWGQDao WGQDao;
	
	@Override
	public Object queryCompanyAndPost(CompanyAndPost com,HttpServletRequest request) {
		User user =  (User) request.getSession().getAttribute("user");
		
		//用户ID
		User userisEmpty = UserisEmpty(user);	
		
		//总条数
		Long total = WGQDao.queryCompanyAndPostCount(com);
		
		//公司列表
		List<CompanyAndPost> list =  WGQDao.queryCompanyAndPost(com);	
		
		//通过用户ID查询用户审核表//用户投过的简历
		List<WGQPostQiuzhi> listP =  WGQDao.queryPostQiuZhiByUserId(userisEmpty.getUserid());
		
		
		
		for (CompanyAndPost companyAndPost : list) {
			for (WGQPostQiuzhi wgqPostQiuzhi : listP) {
				if(companyAndPost.getPostid() == wgqPostQiuzhi.getPostid()){
					companyAndPost.setB(true);
				}
			}
		}
		
		JSONObject json =  new JSONObject();
		json.put("total", total);
		json.put("rows",list);
		return json;
	}

	@Override
	public Object getCompanyAll() {	
		return null;
	}
	
	@Override
	//下拉框获取全部职位
	public Object getPostAll() {
		List<Post> postAll = WGQDao.getPostAll();
		for (Post post : postAll) {
			post.setPostcompany(null);
		}
		
		List arrayList = new ArrayList(new HashSet(postAll));
		return arrayList;
	}


	@Override
	//用户申请职位存入 post_qiuzhi表
	public Object UserPutResume(String postid, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		User userisEmpty = UserisEmpty(user);

		//通过postid查询出来 职位表
		Post post = WGQDao.selectPost(postid);
		
		WGQDao.updateUserTypeIdByUserId(userisEmpty.getUserid());
		
		user.setTypeid(5);
		
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("user", user);
		
		//把用户和 职位表数据存入 "用户和职位关联表"
		WGQDao.savePostQiuzhi(userisEmpty.getUserid(),post);
		return "success";
	}
	
	public User UserisEmpty(User user){
		if(user == null ){
			User u = new User();
			u.setUserid(1);
			return u;
		}else{
			if(user.getUserid() == null | "".equals(user.getUserid())){
				User u = new User();
				user.setUserid(1);
				return u;
			}else{
				return user;
			}
		}		
	}

	@Override
	//通过用户ID查询用户已投简历
	public Object queryCompanyAndPostByuserid(Integer offset, Integer limit, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		User userisEmpty = UserisEmpty(user);
		Long total = WGQDao.queryCompanyAndPostCountByUserid(userisEmpty.getUserid());
		List<CompanyAndPost> list = WGQDao.queryCompanyAndPostByUserid(userisEmpty.getUserid(),offset,limit);
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", list);
		return json;
	}

	@Override
	public Object UpdateComPostResume(String postid, HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		User userisEmpty = UserisEmpty(user);
		WGQDao.delectComPostResume(userisEmpty.getUserid(),postid);
		WGQDao.updateUserTypeIdByUserId2(userisEmpty.getUserid());
		
		user.setTypeid(4);
		
		request.getSession().removeAttribute("user");
		request.getSession().setAttribute("user", user);
		return "success";
	}
}
