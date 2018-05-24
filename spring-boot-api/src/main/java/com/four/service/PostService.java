package com.four.service;

import java.util.List;

import com.alibaba.dubbo.common.json.JSONObject;
import com.four.model.Company;
import com.four.model.Post;
import com.four.model.User;


public interface PostService {

	
	JSONObject queryPost(Post Post, Integer rows, Integer page);

	
	int insertPost(Post Post);


	Integer deletePostByIDs(String ids);


	Integer updatePostById(Integer postid);


	List<Company> querycompanys();

	List<User> queryusers(int postid);


}
