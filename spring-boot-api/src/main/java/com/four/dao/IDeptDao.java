/** 
 * <pre>项目名称:four_group 
 * 文件名称:IDeptDao.java 
 * 包名:com.four.dao 
 * 创建日期:2018年3月13日下午7:28:05 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.four.model.Dept;

/** 
 * <pre>项目名称：four_group    
 * 类名称：IDeptDao    
 * 类描述：    
 * 创建人：薛怡衡  
 * 创建时间：2018年3月13日 下午7:28:05    
 * 修改人：薛怡衡    
 * 修改时间：2018年3月13日 下午7:28:05    
 * 修改备注：       
 * @version </pre>    
 */
@Mapper
public interface IDeptDao {

	/** <pre>queryCount(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午7:52:25    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午7:52:25    
	 * 修改备注： 
	 * @param dept
	 * @return</pre>    
	 */
	long queryCount(@Param("dept") Dept dept) throws Exception;

	/** <pre>queryDeptInfo(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午7:52:50    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午7:52:50    
	 * 修改备注： 
	 * @param start
	 * @param rows
	 * @param dept
	 * @return</pre>    
	 */
	List<Dept> queryDeptInfo(@Param("start") int start,
                             @Param("rows") int rows, @Param("dept") Dept dept) throws Exception;

	/** <pre>addDept(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午8:11:13    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午8:11:13    
	 * 修改备注： 
	 * @param dept
	 * @return</pre>    
	 */
	int addDept(Dept dept)throws Exception;

	/** <pre>deleteSome(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午8:21:43    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午8:21:43    
	 * 修改备注： 
	 * @param idLists
	 * @return</pre>    
	 */
	int deleteSome(List<String> idLists)throws Exception;

	/** <pre>queryDeptName(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月14日 上午10:31:30    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月14日 上午10:31:30    
	 * 修改备注： </pre>    
	 * @return 
	 */
	List<Dept> queryDeptName();

	/** <pre>queryDeptNum(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月16日 上午10:31:25    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月16日 上午10:31:25    
	 * 修改备注： 
	 * @param deptid
	 * @return</pre>    
	 */
	List queryDeptNum(Integer deptid);
	

}
