/** 
 * <pre>项目名称:four_group 
 * 文件名称:ILoginService.java 
 * 包名:com.four.service 
 * 创建日期:2018年3月13日下午1:12:30 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.service;

import com.four.model.LoginGs;
import com.four.model.LoginUser;
import com.four.model.TongJi;
import com.four.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/** 
 * <pre>项目名称：four_group    
 * 类名称：ILoginService    
 * 类描述：    
 * 创建人：孙梦娜  
 * 创建时间：2018年3月13日 下午1:12:30    
 * 修改人：孙梦娜    
 * 修改时间：2018年3月13日 下午1:12:30    
 * 修改备注：       
 * @version </pre>    
 */
public interface ILoginService {

	//前台注册
	Integer laGouReg(LoginUser laGouUser);
	//前台注册检验
	Integer laGouRegCheck(LoginUser laGouUser);
	//前台登录
	Map<String,Object> laGouLogin(LoginUser laGouUser);

	User login(User user)throws Exception;

	User getResourcesRoleList(User login)throws Exception;


	/** <pre>checkLoginName(这里用一句话描述这个方法的作用)   
	 * 创建人：张国彬 
	 * 创建时间：2018年3月13日 下午8:29:26    
	 * 修改人:张国彬       
	 * 修改时间：2018年3月13日 下午8:29:26    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User checkLoginName(User user);


	/** <pre>addUserLogin(这里用一句话描述这个方法的作用)   
	 * 创建人：张国彬 
	 * 创建时间：2018年3月14日 上午10:34:34    
	 * 修改人:张国彬       
	 * 修改时间：2018年3月14日 上午10:34:34    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void addUserLogin(User user);

	//前台注册统计
	List<TongJi> tongJiRegNum();

	//后台公司登录
	Integer loginGS(LoginGs loginGs);

	//后台公司注册
	Integer addRegGS(LoginGs loginGs);

	//前台注册修改userid
	void queryIdByName(String loginName);

	//前台查询登录id并修改comid
	void queryComIdByName(String loginName);

	//前台注册检测手机号
	Integer laGouRegGSCheck(LoginUser laGouUser);

	//前台登录公司
	Map<String,Object> laGouLoginGS(LoginUser laGouUser);

}
