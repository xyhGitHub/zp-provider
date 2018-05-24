package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.four.model.Role;
import com.four.model.JiuYe;
@Mapper
public interface JiuYeMapper {
    int deleteByPrimaryKey(Integer tjid);

    int insert(JiuYe record);

    int insertSelective(JiuYe record);

    JiuYe selectByPrimaryKey(Integer tjid);

    int updateByPrimaryKeySelective(JiuYe record);

    int updateByPrimaryKey(JiuYe record);

	/** <pre>querycount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:06:42    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:06:42    
	 * 修改备注： 
	 * @param tongji
	 * @return</pre>    
	 */
	long querycount(JiuYe tongji);

	/** <pre>queryRole(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:06:49    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:06:49    
	 * 修改备注： 
	 * @param tongji
	 * @param start
	 * @param rows
	 * @return</pre>    
	 */
	List<JiuYe> queryTongJi(@Param("tongji") JiuYe tongji, @Param("start") int start, @Param("end") Integer rows);

	/** <pre>updateQiuZhiCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:29:44    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:29:44    
	 * 修改备注： </pre>    
	 */
	void updateQiuZhiCount();

	/** <pre>updateRuZhiCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:33:23    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:33:23    
	 * 修改备注： </pre>    
	 */
	void updateRuZhiCount();

	/** <pre>updateJiuYeLv(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月16日 上午10:43:48    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月16日 上午10:43:48    
	 * 修改备注： </pre>    
	 */
	void updateJiuYeLv();

	/** <pre>qiuzhiCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月19日 下午6:56:27    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月19日 下午6:56:27    
	 * 修改备注： 
	 * @return</pre>    
	 */
	int qiuzhiCount();

	/** <pre>ruzhiCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月19日 下午6:56:33    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月19日 下午6:56:33    
	 * 修改备注： 
	 * @return</pre>    
	 */
	int ruzhiCount();

	/** <pre>jiuyelvCount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月19日 下午7:39:44    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月19日 下午7:39:44    
	 * 修改备注： 
	 * @return</pre>    
	 */
	Double jiuyelvCount();
}