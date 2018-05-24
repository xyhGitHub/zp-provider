package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.four.model.Role;

@Mapper
public interface RoleMapper {


	/** <pre>querycount(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月12日 下午8:59:24    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月12日 下午8:59:24    
	 * 修改备注： 查询角色所有条数，准备分页
	 * @param role
	 * @return</pre>    
	 */
	@Select("select count(*) from t_role")
	long querycount(Role role);

	/** <pre>querySales(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月12日 下午8:59:27    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月12日 下午8:59:27    
	 * 修改备注： 查询角色所有数据，分页
	 * @param role
	 * @param start
	 * @param rows
	 * @return</pre>    
	 */
	@Select("select * from t_role limit #{start},#{end}")
	List<Role> queryRole(@Param("role") Role role, @Param("start") int start, @Param("end") Integer rows);

	/** <pre>insertRole(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月12日 下午9:13:57    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月12日 下午9:13:57    
	 * 修改备注： 增加角色
	 * @param role
	 * @return</pre>    
	 */
	@Insert("insert into t_role (roleId, roleName) values (#{roleId,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR})")
	int insertRole(Role role);

	/** <pre>deleteRoleByIDs(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月12日 下午10:15:50    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月12日 下午10:15:50    
	 * 修改备注： 删除角色/批量删
	 * @param split
	 * @return</pre>    
	 */
	@Delete("delete from t_role\n" +
			"    where roleId in\n" +
			"    <foreach item=\"item\" index=\"index\" collection=\"ar\" open=\"(\" separator=\",\" close=\")\"> \n" +
			"    #{item} \n" +
			"    </foreach>")
	Integer deleteRoleByIDs(@Param(value = "ar") String[] split);

	/** <pre>toRoleById(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月13日 上午11:24:44    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月13日 上午11:24:44    
	 * 修改备注： 修改回显
	 * @param role
	 * @return</pre>    
	 */
	@Select("select \n" +
			"    <include refid=\"Base_Column_List\" />\n" +
			"    from t_role\n" +
			"    where roleId = #{roleId,jdbcType=INTEGER}")
	Role toRoleById(Role role);

	/** <pre>updateRoleById(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月13日 上午11:50:45    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月13日 上午11:50:45    
	 * 修改备注： 根据ID修改用户信息
	 * @param role</pre>    
	 */
	@Update("update t_role\n" +
			"\t\t<set>\n" +
			"\t\t\t<if test=\"roleName != null\">\n" +
			"\t\t\t\troleName = #{roleName,jdbcType=VARCHAR},\n" +
			"\t\t\t</if>\n" +
			"\t\t</set>\n" +
			"\t\twhere roleId = #{roleId,jdbcType=DECIMAL}")
	void updateRoleById(Role role);
}