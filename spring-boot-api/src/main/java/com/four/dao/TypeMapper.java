package com.four.dao;

import java.util.List;

import com.four.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TypeMapper {    int deleteByPrimaryKey(Integer typeid);

    int insert(Type record);

    int insertSelective(Type record);

    @Select(" select *  from t_type" +
			"    where typeid = #{typeid,jdbcType=INTEGER}")
    Type selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

	/** <pre>queryType(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月14日 下午2:45:34    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月14日 下午2:45:34    
	 * 修改备注： 
	 * @return</pre>    
	 */
	@Select("select * from t_type")
	List<Type> queryType();
}