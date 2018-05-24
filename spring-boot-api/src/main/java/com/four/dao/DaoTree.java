
package com.four.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.four.model.Tree;
import com.four.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 
 * <pre>项目名称：four_group    
 * 类名称：DaoTree    
 * 类描述：    
 * 创建人：孙梦娜  
 * 创建时间：2018年3月12日 下午08:02:57    
 * 修改人：孙梦娜    
 * 修改时间：2018年3月12日 下午08:02:57    
 * 修改备注：       
 * @version </pre>
 */
@Mapper
public interface DaoTree {


	/**
	 * <pre>treelist(查询树的信息)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月12日 下午10:03:39    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月12日 下午10:03:39    
	 * 修改备注： 
	 * @return</pre>
	 */
	@Select("select * from t_tree")
	List<Tree> treelist();

	/** <pre>insertSelective(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月8日 下午7:25:00    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月8日 下午7:25:00    
	 * 修改备注： 
	 * @param tree
	 * @return</pre>    
	 */
	@Insert("insert into t_tree (id, pid, text, " +
			"      url)" +
			"    values (#{id,jdbcType=INTEGER}, #{pid,jdbcType=INTEGER}, #{text,jdbcType=VARCHAR}," +
			"      #{url,jdbcType=VARCHAR})")
	int insert(Tree tree);

	/**
	 * <pre>deleteByPrimaryKey(进行移除树)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月12日 下午10:04:18    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月12日 下午10:04:18    
	 * 修改备注： 
	 * @param id</pre>
	 */
	@Delete("delete from t_tree" +
			"    where id = #{id,jdbcType=INTEGER}")
	void deleteByPrimaryKey(Integer id);

	/**
	 * <pre>selectByPrimaryKey(根据id查询在进行修改)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月12日 下午10:04:48    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月12日 下午10:04:48    
	 * 修改备注： 
	 * @param id
	 * @return</pre>
	 */
	@Select("select " +
			"    <include refid=\"Base_Column_List\" />" +
			"    from t_tree" +
			"    where id = #{id,jdbcType=INTEGER}")
	Tree selectByPrimaryKey(Integer id);
/**
 * <pre>updateByPrimaryKey(修改)   
 * 创建人：孙梦娜  
 * 创建时间：2018年3月12日 下午10:05:18    
 * 修改人:孙梦娜        
 * 修改时间：2018年3月12日 下午10:05:18    
 * 修改备注： 
 * @param tree</pre>
 */
@Select("update t_tree" +
		"    set pid = #{pid,jdbcType=INTEGER}," +
		"      text = #{text,jdbcType=VARCHAR}," +
		"      url = #{url,jdbcType=VARCHAR}" +
		"    where id = #{id,jdbcType=INTEGER}")
	void updateByPrimaryKey(Tree tree);

	/** <pre>queryRoleTree(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月9日 下午9:35:31    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月9日 下午9:35:31    
	 * 修改备注： 
	 * @param roleId
	 * @return</pre>    
	 */
	@Select("       select * from role_tree rt,t_tree tt where rt.treeId = tt.id and rt.roleId=#{roleId}")
	List<Tree> queryRoleTree(int roleId);

	/** <pre>selectTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月9日 下午9:35:33    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月9日 下午9:35:33    
	 * 修改备注： 
	 * @param tre
	 * @return</pre>    
	 */
	@Select("select * from t_tree ")
	List<Tree> selectTreeList(Tree tre);

	/** <pre>deleteTreeRoleRealtion(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月13日 下午7:14:11    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月13日 下午7:14:11    
	 * 修改备注： 
	 * @param roleId</pre>    
	 */
	@Delete("delete from role_tree where roleId=#{roleId}")
	void deleteTreeRoleRealtion(String roleId);

	/** <pre>addTreeRoleRelation(这里用一句话描述这个方法的作用)   
	 * 创建人：苑鹏飞 1103304339@qq.com     
	 * 创建时间：2018年3月13日 下午7:15:08    
	 * 修改人：苑鹏飞  1103304339@qq.com   
	 * 修改人电话：17600552303
	 * 修改时间：2018年3月13日 下午7:15:08    
	 * 修改备注： 
	 * @param maps</pre>    
	 */
	@Insert("insert into role_tree (roleId,treeId) values" +
			"     <foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
			"         (#{item.roleId},#{item.treeId})" +
			"     </foreach>")
	void addTreeRoleRelation(List<Map<String, Object>> maps);

	/** <pre>getRoleTreeList(这里用一句话描述这个方法的作用)   
	 * 创建人：孙梦娜  
	 * 创建时间：2018年3月20日 下午4:36:53    
	 * 修改人:孙梦娜        
	 * 修改时间：2018年3月20日 下午4:36:53    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	@Select("select tt.* from t_user_role ur,role_tree rt ,t_tree tt where ur.roleid=rt.roleId and rt.treeid =tt.id" +
			"       and ur.userid = #{userid}")
	Set<Map<String, Object>> getRoleTreeList(User user);


}
