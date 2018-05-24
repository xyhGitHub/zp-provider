/** 
 * <pre>项目名称:four_group 
 * 文件名称:DeptServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月13日下午7:26:55 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.four.dao.IDeptDao;
import com.four.model.Dept;
import com.four.service.IDeptService;

/** 
 * <pre>项目名称：four_group    
 * 类名称：DeptServiceImpl    
 * 类描述：    
 * 创建人：薛怡衡  
 * 创建时间：2018年3月13日 下午7:26:55    
 * 修改人：薛怡衡    
 * 修改时间：2018年3月13日 下午7:26:55    
 * 修改备注：       
 * @version </pre>    
 */
@Service("deptService")
public class DeptServiceImpl implements IDeptService {
	
	@Autowired
	private IDeptDao deptDao;

	/* (non-Javadoc)    
	 * @see com.four.service.IDeptService#queryDept(int, int, com.four.model.Dept)    
	 */
	@Override
	@Cacheable(value="redis",key="'queryDeptInfo'+#dept.getDeptid()")
	public Map<String, Object> queryDeptInfo(int page, int rows, Dept dept) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//查询总条数
		long total = deptDao.queryCount(dept);
		//计算开始位置和结束位置
		int start = (page-1)*rows;
		List<Dept> list=deptDao.queryDeptInfo(start,rows,dept);
		
		map.put("total", total);
		map.put("rows", list);
		
		return map;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IDeptService#addDept(com.four.model.Dept)    
	 */
	@Override
	public int addDept(Dept dept) throws Exception {

		int a = deptDao.addDept(dept);
		
		return a;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IDeptService#deleteSome(java.lang.String)    
	 */
	@Override
	public int deleteSome(String idList) throws Exception {

		String[] idM=idList.split(",");
		List<String> idLists =new ArrayList<String>();
		for (String string : idM) {
			idLists.add(string);
		}
		int a = deptDao.deleteSome(idLists);
		return a;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IDeptService#queryDeptName()    
	 */
	@Override
	public List<Dept> queryDeptName() {

		List<Dept> list = deptDao.queryDeptName();
		return list;
		
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IDeptService#queryDeptNum(java.lang.Integer)    
	 */
	@Override
	public List queryDeptNum(Integer deptid) {

		List list = deptDao.queryDeptNum(deptid);
		
		return list;
	}



}
