/** 
 * <pre>项目名称:four_group 
 * 文件名称:LoginServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月13日下午1:13:08 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.four.dao.GongSiDao;
import com.four.dao.JianLiDao;
import com.four.model.*;
import com.four.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.ILoginDao;
import com.four.service.ILoginService;
import redis.clients.jedis.Jedis;


/**
 * <pre>项目名称：four_group    
 * 类名称：LoginServiceImpl    
 * 类描述：    
 * 创建人：孙梦娜  
 * 创建时间：2018年3月13日 下午1:13:08    
 * 修改人：孙梦娜    
 * 修改时间：2018年3月13日 下午1:13:08    
 * 修改备注：       
 * @version </pre>    
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginDao loginDao;

	@Autowired
	private JianLiDao jianLiDao;

	@Autowired
	private GongSiDao gongSiDao;


	//前台用户和公司注册
	@Override
	public Integer laGouReg(LoginUser laGouUser) {

		//默认0失败   1是成功
		Integer regFlag = 0;

		if(laGouUser != null){

			String loginPwd = laGouUser.getLoginPwd();
			//对密码进行加密
			laGouUser.setLoginPwd(MD5.md5(loginPwd));

			Date dateNow = new Date();
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			//为了方便后台统计,这里注册时间为年月日
			String formatTime = sim.format(dateNow);
			laGouUser.setCreatetime(formatTime);

			loginDao.addLoginUserInfo(laGouUser);

			regFlag = 1;
			return regFlag;

		}

		return regFlag;
	}


	//注册用户成功后的操作
	@Override
	public void queryIdByName(String loginName) {
		Integer loginId = loginDao.queryIdByName(loginName);

		//根据id修改userid
		loginDao.updateLoginById(loginId);
		//并在简历表t_userjianli新增userid
		jianLiDao.addJianLiInfo(loginId);
	}


	//查询id对公司表进行新增
	@Override
	public void queryComIdByName(String loginName) {

		Integer loginId = loginDao.queryIdByName(loginName);

		//根据id修改comid
		loginDao.updateLoginGsById(loginId);
		//并在公司表t_company新增comid
		gongSiDao.addGongSiId(loginId);

	}


	//检测前台公司注册的手机号
	@Override
	public Integer laGouRegGSCheck(LoginUser laGouUser) {
		//默认0 账号为null   1账号不为空但已存在   2是不为空且可用
		Integer regCheckFlag = 0;

		if(laGouUser.getLoginName() != null & "".equals(laGouUser.getLoginName())){

			LoginUser loginUser = loginDao.laGouRegCheck(laGouUser.getLoginName());

			if(loginUser == null){
				return regCheckFlag=1;
			}else if(loginUser != null){
				return regCheckFlag=2;
			}
		}

		return regCheckFlag;
	}

	//前台公司登录
	@Override
	public Map<String, Object> laGouLoginGS(LoginUser laGouUser) {
		// 0 登录失败，1登录失败用户为空 2 登陆成功  3验证码错误
		Integer loginFlag = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		if(laGouUser != null){
			String loginPwd = laGouUser.getLoginPwd();
			laGouUser.setLoginPwd(MD5.md5(loginPwd));

			LoginUser laGouUsers = loginDao.laGouLogin(laGouUser);
			if(laGouUsers == null){
				map.put("loginGsFlag",1);
				return map;
			}else if(laGouUsers != null){

				if(laGouUsers.getUserType() == 1){
					map.put("laGouCom",laGouUsers);
					Integer comid = laGouUsers.getComid();
					loginFlag = 2;
					map.put("loginComFlag",2);
					return map;
				}else if(laGouUsers.getUserType() == 0){
					map.put("laGouUsers",laGouUsers);
					Integer userid = laGouUsers.getUserid();
					loginFlag = 2;
					map.put("loginFlag",2);
					return map;
				}
			}
		}
		return map;
	}

	//前台用户注册的手机号检测
	@Override
	public Integer laGouRegCheck(LoginUser laGouUser) {

		//默认0 账号为null   1账号不为空但已存在   2是不为空且可用
		Integer regCheckFlag = 0;

		if(laGouUser.getLoginName() != null & "".equals(laGouUser.getLoginName())){

			LoginUser loginUser = loginDao.laGouRegCheck(laGouUser.getLoginName());

			if(loginUser == null){
				return regCheckFlag=1;
			}else if(loginUser != null){
				return regCheckFlag=2;
			}
		}

		return regCheckFlag;
	}

	//前台用户的登录
	@Override
	public Map<String,Object> laGouLogin(LoginUser laGouUser) {


		// 0 登录失败，1登录失败用户为空 2 登陆成功
		Integer loginFlag = 0;
//		Jedis jedis = new Jedis("192.168.116.129",6379);
		Map<String,Object> map = new HashMap<String,Object>();
			if(laGouUser != null){
				String loginPwd = laGouUser.getLoginPwd();
				laGouUser.setLoginPwd(MD5.md5(loginPwd));


				LoginUser laGouUsers = loginDao.laGouLogin(laGouUser);
				if(laGouUsers == null){
					map.put("loginFlag",1);
					return map;
				}else if(laGouUsers != null){
					map.put("laGouUsers",laGouUsers);

					if(laGouUsers.getUserType() == 1){
						Integer comid = laGouUsers.getComid();

//					为了session共享 存入redis
//					jedis.setex(comid.toString(),150000,comid.toString());
						loginFlag = 2;
						map.put("loginFlag",2);
					return map;
				}else if(laGouUsers.getUserType() == 0){
						Integer userid = laGouUsers.getUserid();
						//根据userid查询到用户信息
//					jedis.setex(userid.toString(),150000,userid.toString());
					//注销时需要移除
						loginFlag = 2;
						map.put("loginFlag",2);
					return map;
				}
				}
//				jedis.close();
			}
		return map;
	}

	/* (non-Javadoc)  后台用户登录
         * @see com.four.service.IUserService#login(com.four.model.User)
         */
	@Override
	public User login(User user) throws Exception {
		User login = loginDao.login(user);
		return login;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IUserService#getResourcesRoleList(com.four.model.User)    
	 */
	@Override
	public User getResourcesRoleList(User login) throws Exception {
		Set<String> resources = new HashSet<String>();
		//定义新的集合接收数据库查询到数据
				Set<Role> roles =new HashSet<Role>();
				
				//查询数据库返回数据
				List<Map<String,Object>> listMap =loginDao.getResourcesRoleListLogin(login);
				
				//把查询结果循环
				for (Map<String, Object> map : listMap) {
					if(map.get("url")!=null && !"".equals(map.get("url"))){
						resources.add(map.get("url").toString());
					}
					Role r =new Role();
					r.setRoleName(map.get("roleName").toString());
					r.setRoleId(Integer.parseInt(map.get("roleId").toString()));
					roles.add(r);
//					System.out.println("集合接收数据库查询到数据"+roles);
				}
				login.setRoles(roles);
				login.setResources(resources);
				return login;
	}
	
	

	/* (non-Javadoc)    
	 * @see com.four.service.ILoginService#checkLoginName(java.lang.String)    
	 */
	//验证登陆名是否已经存在
	@Override
	public User checkLoginName(User user) {
		
		return loginDao.checkUserNameLogin(user);
	}

	/* (non-Javadoc)     
	 * @see com.four.service.ILoginService#addUserLogin(com.four.model.User)    
	 */
	@Override
	public void addUserLogin(User user) {
		loginDao.addUserLogin(user);
	}

	//统计注册人数
	@Override
	public List<TongJi> tongJiRegNum() {

		List<TongJi> tongjiUser = loginDao.tongJiRegNum();

		return tongjiUser;
	}

	//后台公司登录
	@Override
	public Integer loginGS(LoginGs loginGs) {
		//填写不完整
		Integer GsFlag = 0;
		if(loginGs != null){

			//对密码进行加密
			String loginPwd = loginGs.getLoginPwd();
			loginGs.setLoginPwd(MD5.md5(loginPwd));
//			Jedis jedis = new Jedis("192.168.116.129",6379);

			if("137".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS137(loginGs);
				if(gsLogin != null){
					//登录成功
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}else if("138".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS138(loginGs);
				if(gsLogin != null){
					//登录成功
//					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}else if("187".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS187(loginGs);
				if(gsLogin != null){
					//登录成功
//					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}else if("185".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS185(loginGs);
				if(gsLogin != null){
					//登录成功
//					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}
		}

		return GsFlag;
	}

	//后台公司注册
	@Override
	public Integer addRegGS(LoginGs loginGs) {
		//填写不完整
		Integer GsFlag = 0;
		if(loginGs != null){

			//对密码进行加密
			String loginPwd = loginGs.getLoginPwd();
			loginGs.setLoginPwd(MD5.md5(loginPwd));

			//加入注册时间
			Date dateNow = new Date();
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			//为了方便后台统计,这里注册时间为年月日
			String formatTime = sim.format(dateNow);
			loginGs.setCreatetime(formatTime);

			if("137".equals(loginGs.getLoginTel().substring(0,3))){
				 loginDao.addRegGS137(loginGs);
			}else if("138".equals(loginGs.getLoginTel().substring(0,3))){
				 loginDao.addRegGS138(loginGs);
			}else if("187".equals(loginGs.getLoginTel().substring(0,3))){
				 loginDao.addRegGS187(loginGs);
			}else if("185".equals(loginGs.getLoginTel().substring(0,3))){
				 loginDao.addRegGS185(loginGs);
			}
			//注册成功
			return GsFlag = 1;
		}

		return GsFlag;
	}

}
