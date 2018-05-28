/** 
 * <pre>项目名称:four_group 
 * 文件名称:ILoginDao.java 
 * 包名:com.four.daol 
 * 创建日期:2018年3月13日下午1:14:09 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.dao;

import java.util.List;
import java.util.Map;

import com.four.model.LoginGs;
import com.four.model.LoginUser;
import com.four.model.TongJi;
import org.apache.ibatis.annotations.*;

import com.four.model.User;

@Mapper
public interface ILoginDao {

	@Insert("insert into t_login_user (loginName, loginPwd,userType,createtime)" +
			"        values(" +
			"        #{laGouUser.loginName}," +
			"        #{laGouUser.loginPwd}," +
			"        #{laGouUser.userType}," +
			"		  #{laGouUser.createtime})")
	void addLoginUserInfo(@Param("laGouUser")LoginUser laGouUser);

	@Select("select * from t_login_user where loginName = #{laGouUser.loginName}")
	LoginUser laGouRegCheck(@Param("loginName") String loginName);

	//前台登录
	@Select("select * from t_login_user where loginName=#{laGouUser.loginName} and loginPwd=#{laGouUser.loginPwd}")
	LoginUser laGouLogin(@Param("laGouUser") LoginUser laGouUser);

	@Select(" select * from t_user where loginname=#{user.loginname} and pwd=#{user.pwd}")
	User login(@Param("user") User user)throws Exception;

	@Select("select * from t_user_role ur,t_role tr,t_user_role rt,t_tree tt where" +
			"       ur.roleid=tr.roleId and tr.roleId=rt.roleId and rt.userid=tt.id" +
			"       and ur.userid = #{login.userid}")
	List<Map<String, Object>> getResourcesRoleListLogin(@Param("login") User login)throws Exception;

	/** <pre>checkUserName(这里用一句话描述这个方法的作用)   
	 * 创建人：张国彬 
	 * 创建时间：2018年3月13日 下午8:31:21    
	 * 修改人:张国彬       
	 * 修改时间：2018年3月13日 下午8:31:21    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	@Select("select * from t_user where loginname = #{user.loginname}")
	User checkUserNameLogin(@Param("user") User user);

	/** <pre>addUserLogin(这里用一句话描述这个方法的作用)   
	 * 创建人：张国彬 
	 * 创建时间：2018年3月14日 上午10:35:24    
	 * 修改人:张国彬       
	 * 修改时间：2018年3月14日 上午10:35:24    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	@Insert("insert into t_user (userid,username, loginname, pwd,typeid,userphone)" +
			"    values " +
			"    (#{user.userid,jdbcType=INTEGER}," +
			"    #{user.username,jdbcType=VARCHAR} ," +
			"    #{user.loginname,jdbcType=VARCHAR}," +
			"    #{user.pwd,jdbcType=VARCHAR}," +
			"    #{user.typeid,jdbcType=INTEGER}," +
			"    #{user.userphone,jdbcType=VARCHAR})")
	void addUserLogin(@Param("user")User user);

	//统计注册人数
	@Select("SELECT count(1) nums,createtime names FROM t_login_user GROUP BY createtime")
    List<TongJi> tongJiRegNum();

	//后台登陆
	@Select("select * from t_login_user_137 where loginTel=#{loginGs.loginTel} and loginPwd=#{loginGs.loginPwd}")
	LoginGs loginGS137(@Param("loginGs") LoginGs loginGs);
	@Select("select * from t_login_user_138 where loginTel=#{loginGs.loginTel} and loginPwd=#{loginGs.loginPwd}")
	LoginGs loginGS138(@Param("loginGs") LoginGs loginGs);
	@Select("select * from t_login_user_187 where loginTel=#{loginGs.loginTel} and loginPwd=#{loginGs.loginPwd}")
	LoginGs loginGS187(@Param("loginGs") LoginGs loginGs);
	@Select("select * from t_login_user_185 where loginTel=#{loginGs.loginTel} and loginPwd=#{loginGs.loginPwd}")
	LoginGs loginGS185(@Param("loginGs") LoginGs loginGs);

	//后台公司注册
	@Insert("insert into t_login_user_137 (loginTel,loginPwd,createtime) " +
			" values (#{loginGs.loginTel},#{loginGs.loginPwd},#{loginGs.createtime})")
	void addRegGS137(@Param("loginGs") LoginGs loginGs);
	@Insert("insert into t_login_user_138 (loginTel,loginPwd,createtime) " +
			" values (#{loginGs.loginTel},#{loginGs.loginPwd},#{loginGs.createtime})")
	void addRegGS138(@Param("loginGs") LoginGs loginGs);
	@Insert("insert into t_login_user_187 (loginTel,loginPwd,createtime) " +
			" values (#{loginGs.loginTel},#{loginGs.loginPwd},#{loginGs.createtime})")
	void addRegGS187(@Param("loginGs") LoginGs loginGs);
	@Insert("insert into t_login_user_185 (loginTel,loginPwd,createtime) " +
			" values (#{loginGs.loginTel},#{loginGs.loginPwd},#{loginGs.createtime})")
	void addRegGS185(@Param("loginGs") LoginGs loginGs);

	@Select("select loginId from t_login_user where loginName = #{loginName}")
	Integer queryIdByName(@Param("loginName") String loginName);

	@Update("update t_login_user set userid = #{loginId} where loginId = #{loginId}")
	void updateLoginById(@Param("loginId") Integer loginId);

	@Update("update t_login_user set comid = #{loginId} where loginId = #{loginId}")
	void updateLoginGsById(@Param("loginId")Integer loginId);
}
