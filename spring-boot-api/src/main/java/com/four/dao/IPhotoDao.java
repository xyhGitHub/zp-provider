/** 
 * <pre>项目名称:four_group 
 * 文件名称:IPhotoDao.java 
 * 包名:com.four.dao 
 * 创建日期:2018年3月12日下午9:12:11 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.four.model.Photo;

/** 
 * <pre>项目名称：four_group    
 * 类名称：IPhotoDao    
 * 类描述：    
 * 创建人：薛怡衡  
 * 创建时间：2018年3月12日 下午9:12:11    
 * 修改人：薛怡衡    
 * 修改时间：2018年3月12日 下午9:12:11    
 * 修改备注：       
 * @version </pre>    
 */
@Mapper
public interface IPhotoDao {

	/** <pre>queryPhotoList(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午12:01:22    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午12:01:22    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Photo> queryPhotoList() throws Exception;

	/** <pre>addPhoto(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午1:37:16    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午1:37:16    
	 * 修改备注： 
	 * @param photo</pre>    
	 */
	void addPhoto(Photo photo) throws Exception;

	/** <pre>queryPhotoLists(这里用一句话描述这个方法的作用)   
	 * 创建人：薛怡衡
	 * 创建时间：2018年3月13日 下午4:18:31    
	 * 修改人:薛怡衡      
	 * 修改时间：2018年3月13日 下午4:18:31    
	 * 修改备注： 
	 * @return</pre>    
	 */
	List<Photo> queryPhotoLists() throws Exception;


}
