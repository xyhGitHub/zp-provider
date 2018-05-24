package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.four.model.Company;
import com.four.model.Post;
import com.four.model.User;
@Mapper
public interface PostMapper {
    int deleteByPrimaryKey(Integer Postid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer Postid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

	
	long querycount(Post Post);

	
	List<Post> queryPost(@Param("Post") Post Post, @Param("start") int start, @Param("end") Integer rows);

	//新增
	int insertPost(Post Post);
	
	//删除
	Integer deletePostByIDs(@Param(value = "ar") String[] split);

	
	//
	String querycompanyname(Integer postcompany);

	List<Company> querycompanys();
	
	
	//
	String queryusername(Integer postusername);
	
	List<User> queryusers(int postid);
	
	
	
	

}
