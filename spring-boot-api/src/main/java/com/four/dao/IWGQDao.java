/** 
 * <pre>项目名称:four_group 
 * 文件名称:IWGQDao.java 
 * 包名:com.four.dao 
 * 创建日期:2018年3月14日下午4:05:48 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.four.model.CompanyAndPost;
import com.four.model.Post;
import com.four.model.WGQPostQiuzhi;

/** 
 * <pre>项目名称：four_group    
 * 类名称：IWGQDao    
 * 类描述：    
 * 创建人：JavaScript 
 * 创建时间：2018年3月14日 下午4:05:48    
 * 修改人：JavaScript  
 * 修改时间：2018年3月14日 下午4:05:48    
 * 修改备注：       
 * @version </pre>    
 */
@Mapper
public interface IWGQDao {

	/** <pre>queryCompanyAndPost(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月14日 下午4:07:30    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月14日 下午4:07:30    
	 * 修改备注： 
	 * @param com 
	 * @return</pre>    
	 */
	List<CompanyAndPost> queryCompanyAndPost(CompanyAndPost com);

	/** <pre>queryCompanyAndPostCount(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月14日 下午4:49:19    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月14日 下午4:49:19    
	 * 修改备注： 
	 * @param com
	 * @return</pre>    
	 */
	Long queryCompanyAndPostCount(CompanyAndPost com);

	/** <pre>getPostAll(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月14日 下午7:23:30    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月14日 下午7:23:30    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Post> getPostAll();

	/** <pre>selectPost(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月16日 上午11:09:13    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月16日 上午11:09:13    
	 * 修改备注： 
	 * @param postid
	 * @return</pre>    
	 */
	Post selectPost(String postid);

	/** <pre>savePostQiuzhi(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月16日 上午11:13:20    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月16日 上午11:13:20    
	 * 修改备注： 
	 * @param userid
	 * @param post</pre>    
	 */
	void savePostQiuzhi(@Param("userid") Integer userid, @Param("post") Post post);

	/** <pre>queryPostQiuZhiByUserId(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月16日 下午2:00:56    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月16日 下午2:00:56    
	 * 修改备注： 
	 * @param userid
	 * @return</pre>    
	 */
	List<WGQPostQiuzhi> queryPostQiuZhiByUserId(Integer userid);

	/** <pre>queryCompanyAndPostCountByUserid(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月16日 下午2:56:42    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月16日 下午2:56:42    
	 * 修改备注： 
	 * @param userid
	 * @return</pre>    
	 */
	Long queryCompanyAndPostCountByUserid(Integer userid);

	/** <pre>queryCompanyAndPostByUserid(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月16日 下午3:06:04    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月16日 下午3:06:04    
	 * 修改备注： 
	 * @param userid
	 * @param limit 
	 * @param offset 
	 * @return</pre>    
	 */
	List<CompanyAndPost> queryCompanyAndPostByUserid(@Param("userid") Integer userid, @Param("offset") Integer offset, @Param("limit") Integer limit);

	/** <pre>delectComPostResume(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月16日 下午4:30:50    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月16日 下午4:30:50    
	 * 修改备注： 
	 * @param userid
	 * @param postid</pre>    
	 */
	void delectComPostResume(@Param("userid") Integer userid, @Param("postid") String postid);

	/** <pre>updateUserTypeIdByUserId(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月20日 下午7:21:33    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月20日 下午7:21:33    
	 * 修改备注： 
	 * @param userid</pre>    
	 */
	void updateUserTypeIdByUserId(Integer userid);

	/** <pre>updateUserTypeIdByUserId2(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月20日 下午7:30:10    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月20日 下午7:30:10    
	 * 修改备注： 
	 * @param userid</pre>    
	 */
	void updateUserTypeIdByUserId2(Integer userid);


}
