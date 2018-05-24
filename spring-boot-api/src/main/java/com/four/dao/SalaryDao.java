/** 
 * <pre>项目名称:four_group 
 * 文件名称:SalaryDao.java 
 * 包名:com.four.dao 
 * 创建日期:2018年3月13日下午8:00:30 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.four.model.Salary;
import com.four.model.User;

/** 
 * <pre>项目名称：four_group    
 * 类名称：SalaryDao    
 * 类描述：    
 * 创建人：孙梦娜  
 * 创建时间：2018年3月13日 下午8:00:30    
 * 修改人：孙梦娜    
 * 修改时间：2018年3月13日 下午8:00:30    
 * 修改备注：       
 * @version </pre>    
 */
@Mapper
public interface SalaryDao {

	/** <pre>selectCount(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月13日 下午8:00:41    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月13日 下午8:00:41    
	 * 修改备注： 
	 * @param salary
	 * @return</pre>    
	 */
	Integer selectCount(@Param("salary") Salary salary)throws Exception;

	/** <pre>salarylist(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月13日 下午8:00:44    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月13日 下午8:00:44    
	 * 修改备注： 
	 * @param start
	 * @param rows
	 * @param salary
	 * @return</pre>    
	 */
	List<Salary> salarylist(@Param("start") int start, @Param("rows") int rows, @Param("salary") Salary salary)throws Exception;

	/** <pre>addsalary(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月13日 下午9:51:34    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月13日 下午9:51:34    
	 * 修改备注： 
	 * @param salary
	 * @return</pre>    
	 */
	int addsalary(Salary salary);

	/** <pre>deleteByPrimaryKey(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月14日 上午10:05:47    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月14日 上午10:05:47    
	 * 修改备注： 
	 * @param list</pre>    
	 */
	void deleteByPrimaryKey(List<String> list);

	/** <pre>selectByPrimaryKey(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月14日 上午10:31:46    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月14日 上午10:31:46    
	 * 修改备注： 
	 * @param salaryid
	 * @return</pre>    
	 */
	Salary selectByPrimaryKey(String salaryid);

	/** <pre>updateByPrimaryKey(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月14日 上午10:49:43    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月14日 上午10:49:43    
	 * 修改备注： 
	 * @param salary</pre>    
	 */
	void updateByPrimaryKey(Salary salary);

	
}
