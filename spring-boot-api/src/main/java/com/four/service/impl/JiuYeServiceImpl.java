/** 
 * <pre>项目名称:four_group 
 * 文件名称:TongjiServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月16日上午9:51:55 
 * Copyright (c) 2018, 1103304339@qq.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.JiuYeMapper;
import com.four.model.Role;
import com.four.model.JiuYe;
import com.four.service.JiuYeService;

/** 
 * <pre>项目名称：four_group    
 * 类名称：TongjiServiceImpl    
 * 类描述：    
 * 创建人：苑鹏飞  1103304339@qq.com   
 * 创建时间：2018年3月16日 上午9:51:55    
 * 修改人：苑鹏飞  1103304339@qq.com      
 * 修改时间：2018年3月16日 上午9:51:55    
 * 修改备注：       
 * @version </pre>    
 */
@Service("jiuyeService")
public class JiuYeServiceImpl implements JiuYeService{

	@Autowired
	private JiuYeMapper tongJiDao;
	/** <pre>queryTongJi(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:03:56    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:03:56    
	 * 修改备注： 
	 * @param tongji
	 * @param rows
	 * @param page
	 * @return</pre>    
	 */
	public JSONObject queryTongJi(JiuYe tongji, Integer rows, Integer page) {
//		查询角色信息的总条数
		long total=tongJiDao.querycount(tongji);
//		计算分页
		int start = page*rows-rows;
//		查询分页
		List<JiuYe> list= tongJiDao.queryTongJi(tongji,start,rows);
		JSONObject jss= new JSONObject();
		jss.put("total", total);
		jss.put("rows", list);
		return jss;
	}
	
	
	/**
	 * <pre>updateQiuZhiCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:44:00    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:44:00    
	 * 修改备注： 增加求职人员，并且修改就业率
	 */
	@Override
	public void updateQiuZhiCount() {
		tongJiDao.updateQiuZhiCount();
		tongJiDao.updateJiuYeLv();
	}
	/**
	 * <pre>updateRuZhiCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:44:50    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:44:50    
	 * 修改备注： 增加入职人员，并且修改就业率
	 */
	@Override
	public void updateRuZhiCount() {
		tongJiDao.updateRuZhiCount();
		tongJiDao.updateJiuYeLv();
	}


	@Override
	public void insertJiuYe() {	
		JiuYe jiuye=new JiuYe();
		 jiuye.setQiuzhicount(tongJiDao.qiuzhiCount());
		 jiuye.setRizhicount(tongJiDao.ruzhiCount());
		 jiuye.setStarttime(new Date());
		 tongJiDao.insert(jiuye);
	}

}
