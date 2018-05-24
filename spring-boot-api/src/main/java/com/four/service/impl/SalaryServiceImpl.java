/** 
 * <pre>项目名称:four_group 
 * 文件名称:SalaryServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月13日下午7:52:54 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.IUserMapper;
import com.four.dao.SalaryDao;
import com.four.model.Salary;
import com.four.model.User;
import com.four.service.ISalaryService;

/** 
 * <pre>项目名称：four_group    
 * 类名称：SalaryServiceImpl    
 * 类描述：    
 * 创建人：孙梦娜  
 * 创建时间：2018年3月13日 下午7:52:54    
 * 修改人：孙梦娜    
 * 修改时间：2018年3月13日 下午7:52:54    
 * 修改备注：       
 * @version </pre>    
 */
@Service("salarService")
public class SalaryServiceImpl implements ISalaryService {

	@Autowired
	private SalaryDao salarydao;
	
	@Autowired
	private IUserMapper userDao;
	
	/* (non-Javadoc)    
	 * @see com.four.service.ISalaryService#salarylist(int, int, com.four.model.Salary)    
	 */
	@Override
	public JSONObject salarylist(int page, int rows, Salary salary) throws Exception {
		//分页查询
		//查询总条数
		Integer total = salarydao.selectCount(salary);
		//计算开始位置和结束位置
		int start = (page-1)*rows;
		List<Salary> list=salarydao.salarylist(start,rows,salary);
		
		JSONObject json =new JSONObject();
		json.put("total", total);
		json.put("rows", list);
		
		return json;
	}


	/* (non-Javadoc)   新增 
	 * @see com.four.service.ISalaryService#addsalary(com.four.model.Salary)    
	 */
	@Override
	public int addsalary(Salary salary) throws Exception {
		Integer a = salary.getSalarybase();
		Integer b = salary.getSalaryjn();
		Integer c = salary.getSalaryqq();
		Integer d= a+b+c;
		salary.setSalarysum(d);
		return salarydao.addsalary(salary);
	}


	/* (non-Javadoc)    
	 * @see com.four.service.ISalaryService#deleteByPrimaryKey(java.lang.String)    
	 */
	@Override
	public void deleteByPrimaryKey(String salaryid) throws Exception {
		String[] id=salaryid.split(",");
		List<String> list =new ArrayList<String>();
		for (String string : id) {
			list.add(string);
		}
		
		salarydao.deleteByPrimaryKey(list);;
		
		
		
	}


	/* (non-Javadoc)    
	 * @see com.four.service.ISalaryService#selectByPrimaryKey(java.lang.String)    
	 */
	@Override
	public Salary selectByPrimaryKey(String salaryid) throws Exception {

		
		
		
		return salarydao.selectByPrimaryKey(salaryid);
	}


	/* (non-Javadoc)    
	 * @see com.four.service.ISalaryService#updateByPrimaryKey(com.four.model.Salary)    
	 */
	@Override
	public void updateByPrimaryKey(Salary salary) {
		Integer a = salary.getSalarybase();
		Integer b = salary.getSalaryjn();
		Integer c = salary.getSalaryqq();
		Integer d= a+b+c;
		salary.setSalarysum(d);
		salarydao.updateByPrimaryKey(salary);
	}

	
	@Override
	public List<User> selectuser(Integer salarysum,Integer salarysum2) {
		List<User> list=userDao.selectuser(salarysum,salarysum2);
		
		return list;
	}
	
	

}
