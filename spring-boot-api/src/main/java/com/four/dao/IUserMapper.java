/** 
 * <pre>项目名称:single_code 
 * 文件名称:IUserMapper.java 
 * 包名:com.lws.dao 
 * 创建日期:2018年3月7日下午11:01:03 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.four.model.Role;
import com.four.model.User;

/** 
 * <pre>项目名称：single_code    
 * 类名称：IUserMapper    
 * 类描述：    
 * 创建人：刘文圣  
 * 创建时间：2018年3月7日 下午11:01:03    
 * 修改人：刘文圣
 * 修改时间：2018年3月7日 下午11:01:03    
 * 修改备注：       
 * @version </pre>    
 */
@Mapper
public interface IUserMapper {
	
	/** <pre>queryUserAllByuserId(通过用户ID查询该用户全部信息)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月13日 下午12:54:44    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月13日 下午12:54:44    
	 * 修改备注： 
	 * @param id 用户ID
	 * @return
	 * 	用户全部信息
	 * </pre>    
	 */

	@Select("select *from t_user u left join t_user_role ur on u.userid = ur.userid" +
			"    left join t_role r on r.roleId = ur.roleid left join t_user_tree ut on u.userid = ut.userid" +
			"     left join t_tree t on t.id = ut.treeid " +
			"    where u.userid = #{id} ")
	List<User> queryUserAllByuserId(Integer id) throws Exception;
	
	/** <pre>getTotalCount(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月9日 下午10:40:37    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月9日 下午10:40:37    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	@Select("select count(1) from t_user " )
	int getTotalCount(@Param("user") User user)throws Exception;

	/** <pre>userlist(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月9日 下午10:48:41    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月9日 下午10:48:41    
	 * 修改备注： 
	 * @param start
	 * @param pageSize
	 * @param user
	 * @return</pre>    
	 */
	@Select("SELECT *,tt.typename, td.deptname FROM " +
			" t_user tu, t_type tt, t_department td WHERE " +
			" tu.deptid = td.deptid and " +
			" tu.typeid=tt.typeid  limit #{start},#{pageSize}")
	List<User> queryUserList(@Param("start") int start, @Param("pageSize") int pageSize, @Param("user") User user)throws Exception;

	/** <pre>deleteUserByIds(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月10日 上午10:51:34    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月10日 上午10:51:34    
	 * 修改备注： 
	 * @param getidList</pre>    
	 */
	@Delete("delete from t_user where userid in\n" +
			"    <foreach collection=\"list\" separator=\",\" open=\"(\"  " +
			"item=\"item\" close=\")\"> #{item}" +
			"    </foreach>")
	void deleteUserByIds(List getidList)throws Exception;

	/** <pre>queryUserById(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月10日 下午11:23:56    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月10日 下午11:23:56    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	@Select("select * from t_user tu where userid = #{userid}")
	User queryUserById(String id)throws Exception;

	/** <pre>addUser(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月11日 上午10:44:59    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月11日 上午10:44:59    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	@Insert("insert into t_user \n" +
			"    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n" +
			"        userid,username,usersex, loginname,pwd, deptid,typeid,companyid,userphone,email,\n" +
			"    </trim>\n" +
			"    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >\n" +
			"        #{userid,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, #{usersex,jdbcType=VARCHAR}, #{loginname,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR}, #{deptid,jdbcType=INTEGER}, #{typeid,jdbcType=INTEGER}, #{companyid,jdbcType=INTEGER},#{userphone,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR},\n" +
			"    </trim>")
	void addUserData(User user)throws Exception;

	/** <pre>updateUserById(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月11日 上午11:13:01    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月11日 上午11:13:01    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	@Update("update t_user tu " +
			"    <set >\n" +
			"        username = #{username,jdbcType=VARCHAR},\n" +
			"        usersex = #{usersex,jdbcType=INTEGER},\n" +
			"        loginname = #{loginname,jdbcType=INTEGER},\n" +
			"        pwd = #{pwd,jdbcType=INTEGER},\n" +
			"        deptid = #{deptid,jdbcType=INTEGER},\n" +
			"        typeid = #{typeid,jdbcType=INTEGER},\n" +
			"        companyid = #{companyid,jdbcType=INTEGER},\n" +
			"        userphone = #{userphone,jdbcType=VARCHAR},\n" +
			"        email = #{email,jdbcType=VARCHAR},\n" +
			"    </set>\n" +
			"    where userid = #{userid,jdbcType=INTEGER}")
	void updateUserById(User user)throws Exception;

	/** <pre>getRoleAll(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月13日 下午7:59:21    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月13日 下午7:59:21    
	 * 修改备注： 
	 * @return</pre>    
	 */
	@Select("select * from t_role")
	List<Role> getRoleAll()throws Exception;

	/** <pre>saveRolesByUserId(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月13日 下午8:57:38    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月13日 下午8:57:38    
	 * 修改备注： 
	 * @param userId
	 * @param list</pre>    
	 */
	@Insert("insert into  t_user_role  (userid,roleid )values\n" +
			"\t\t<foreach collection=\"list\" separator=\",\" item=\"l\">\n" +
			"\t\t\t( #{userId},${l} )\n" +
			"\t\t</foreach>")
	void addRolesByUserId(@Param("userId") Integer userId, @Param("list") List list)throws Exception;

	/** <pre>deleteByUserId(这里用一句话描述这个方法的作用)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月13日 下午9:01:24    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月13日 下午9:01:24    
	 * 修改备注： 
	 * @param userId</pre>    
	 */
	@Delete("delete from t_user_role where userid = #{userId}")
	void deleteByUserId(Integer userId)throws Exception;

	/** <pre>queryUserResumeList(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月14日 下午8:52:44    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月14日 下午8:52:44    
	 * 修改备注： 
	 * @param typeidcriteria
	 * @return</pre>    
	 */
	@Select("SELECT tu.userid, tu.username,tu.usersex,tu.loginname,tu.pwd,tu.deptid," +
			"tu.typeid,tt.typename, td.deptname FROM" +
			"t_user tu, t_type tt, t_department td WHERE" +
			"tu.deptid = td.deptid and" +
			"tu.typeid=tt.typeid" +
			"and tu.userid = #{typeidcriteria}")
	User queryUserResumeList(Integer typeidcriteria)throws Exception;

	/** <pre>lookCompany(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月16日 上午10:34:24    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月16日 上午10:34:24    
	 * 修改备注： 
	 * @param u
	 * @return</pre>    
	 */
	@Select("select *,tc.companyname  from t_company tc,t_user tu where tu.companyid = tc.companyid and tu.userid=#{u.userid}")
	User lookCompany(@Param("u") User u)throws Exception;

	/** <pre>querySeclect(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月16日 下午2:15:42    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月16日 下午2:15:42    
	 * 修改备注： 
	 * @return</pre>    
	 */
	@Select("select * from  t_department")
	List<User> querySeclect()throws Exception;

	/** <pre>queryTypeListSelect(这里用一句话描述这个方法的作用)   
	 * 创建人：刘文圣
	 * 创建时间：2018年3月16日 下午3:30:29    
	 * 修改人：刘文圣
	 * 修改时间：2018年3月16日 下午3:30:29    
	 * 修改备注： 
	 * @return</pre>    
	 */
	@Select("select * from  t_type")
	List<User> queryTypeListSelect()throws Exception;

	/** <pre>selectuser(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月16日 上午10:56:46    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月16日 上午10:56:46    
	 * 修改备注： 
	 * @param salarysum
	 * @param salarysum2 
	 * @return</pre>    
	 */
	@Select("SELECT * FROM t_user WHERE userid in" +
			" (SELECT salaryid FROM t_salary WHERE 1=1 )")
	List<User> selectuser(@Param("salarysum") Integer salarysum, @Param("salarysum2") Integer salarysum2);

}
