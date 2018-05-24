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
import com.four.dao.ILoginUserDao;
import com.four.dao.JianLiDao;
import com.four.model.*;
import com.four.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.ILoginDao;
import com.four.service.ILoginService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


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
	private HttpServletRequest req;

	@Autowired
	private ILoginDao loginDao;

	@Autowired
	private JianLiDao jianLiDao;

	@Autowired
	private GongSiDao gongSiDao;

//	@Autowired
//	private ILoginUserDao loginUserDao;



	@Override
	public Integer laGouReg(LoginUser laGouUser) {

		//默认0失败   1是成功
		Integer regFlag = 0;

		if(laGouUser != null){

			String loginPwd = laGouUser.getLoginPwd();
			//对密码进行加密
			laGouUser.setLoginPwd(MD5.md5(loginPwd));

			//针对简历id以及公司id的处理
			Integer userid = laGouUser.getUserid();
			Integer comid = laGouUser.getComid();

			Date dateNow = new Date();
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			//为了方便后台统计,这里注册时间为年月日
			String formatTime = sim.format(dateNow);
			laGouUser.setCreatetime(formatTime);

			loginDao.addLoginUserInfo(laGouUser);

			if(laGouUser.getUserType() == 0){
				GongSi com = new GongSi();
				com.setComid(comid);
				gongSiDao.addGongSiInfo(com);

			}

			if(laGouUser.getUserType() == 1){
				JianLi jianLi = new JianLi();
				jianLi.setJid(userid);
				jianLiDao.addJianLiInfo(jianLi);
			}

		}

		return regFlag;
	}

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

	@Override
	public Integer laGouLogin(LoginUser laGouUser) {


		// 0 登录失败，1登录失败用户为空 2 登陆成功  3 验证码有误
		Integer loginFlag = 0;
		//获取session中的验证码
		String checkcode = (String) req.getSession().getAttribute("checkcode");
		Jedis jedis = new Jedis("192.168.116.129",6379);
		String checkcode1 = jedis.get("checkcode");
		if(laGouUser.getCheckCode() != null & "".equals(laGouUser.getCheckCode()) & checkcode1 == laGouUser.getCheckCode()){

			if(laGouUser != null){
				String loginPwd = laGouUser.getLoginPwd();
				laGouUser.setLoginPwd(MD5.md5(loginPwd));

				LoginUser laGouUsers = loginDao.laGouLogin(laGouUser);
				Integer userid = laGouUsers.getUserid();
				Integer comid = laGouUsers.getComid();

				jedis.select(2);

				if(laGouUsers == null){
					return loginFlag = 1;
				}else if(laGouUsers.getUserType() == 1){
					req.getSession().setAttribute("sessionLaGouUserId",userid);
					//为了session共享 存入redis
					jedis.setex(userid.toString(),150000,userid.toString());

					return loginFlag = 2;
				}else if(laGouUsers.getUserType() == 0){
					req.getSession().setAttribute("sessionLaGouCompanyId",comid);
					jedis.setex(comid.toString(),150000,comid.toString());
					//注销时需要移除
					return loginFlag = 2;
				}
				jedis.close();
			}
		}else{
			return loginFlag=3;
		}
		return loginFlag;
	}

	/* (non-Javadoc)
         * @see com.four.service.IUserService#login(com.four.model.User)
         */
	@Override
	public User login(User user) throws Exception {
		return loginDao.login(user);
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
					System.out.println("集合接收数据库查询到数据"+roles);
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

			Jedis jedis = new Jedis("192.168.116.129",6379);

			if("137".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS137(loginGs);
				if(gsLogin != null){
					//登录成功
					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}else if("138".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS138(loginGs);
				if(gsLogin != null){
					//登录成功
					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}else if("187".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS187(loginGs);
				if(gsLogin != null){
					//登录成功
					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
					return GsFlag =1;
				}else{
					//密码或者手机号不正确
					return GsFlag = 2;
				}
			}else if("185".equals(loginGs.getLoginTel().substring(0,3))){
				LoginGs gsLogin = loginDao.loginGS185(loginGs);
				if(gsLogin != null){
					//登录成功
					jedis.setex("gsLoginSession",15000,gsLogin.getLoginId().toString());
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
