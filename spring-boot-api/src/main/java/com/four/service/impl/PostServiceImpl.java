package com.four.service.impl;

import java.util.List;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.PostMapper;
import com.four.model.Company;
import com.four.model.Post;
import com.four.model.User;
import com.four.service.PostService;


@Service("postService")
public class PostServiceImpl implements PostService{
	@Autowired
	private PostMapper PostDao;

	@Override
	public JSONObject queryPost(Post Post, Integer rows, Integer page) {
		long total=PostDao.querycount(Post);
		int start = page*rows-rows;
		List<Post> list= PostDao.queryPost(Post,start,rows);
		for (int i = 0; i < list.size(); i++) {
			String name=PostDao.querycompanyname(list.get(i).getPostcompany());
			list.get(i).setPostcompanyname(name);
		}
		JSONObject jss= new JSONObject();
		jss.put("total", total);
		jss.put("rows", list);
		return jss;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.CompanyService#insertCompany(com.four.model.Company)    
	 */
	
	//新增
	@Override
	public int insertPost(Post Post) {
		// TODO Auto-generated method stub
		return PostDao.insertPost(Post);
	}
//	角色删除批量删
	@Override
	public Integer deletePostByIDs(String ids) {
		String[] split = ids.split(",");
		return PostDao.deletePostByIDs(split);
	}

	@Override
	public Integer updatePostById(Integer postid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> querycompanys() {
		// TODO Auto-generated method stub
		return PostDao.querycompanys();
	}

	

	@Override
	public List<User> queryusers(int postid) {
		// TODO Auto-generated method stub
		return PostDao.queryusers(postid);
	}



}
