/** 
 * <pre>项目名称:four_group 
 * 文件名称:TypeServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月14日上午11:26:18 
 * Copyright (c) 2018, 1103304339@qq.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.TypeMapper;
import com.four.model.Type;
import com.four.service.TypeService;

/** 
 * <pre>项目名称：four_group    
 * 类名称：TypeServiceImpl    
 * 类描述：    账户类型实现类
 * 创建人：苑鹏飞  1103304339@qq.com   
 * 创建时间：2018年3月14日 上午11:26:18    
 * 修改人：苑鹏飞  1103304339@qq.com      
 * 修改时间：2018年3月14日 上午11:26:18    
 * 修改备注：       
 * @version </pre>    
 */
@Service("typeService")
public class TypeServiceImpl implements TypeService {
	@Autowired
	private TypeMapper typesDao;

	/**
	 * 普通 查询类型
	 */
	@Override
	public List<Type> queryType() {
		
		return typesDao.queryType();
	}
}
