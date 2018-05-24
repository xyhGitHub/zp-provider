package com.four.dao;

import java.util.List;

import com.four.model.Role;
import com.four.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserDao {

	/** <pre>queryUserAllByuserId(通过用户ID查询该用户全部信息)   
	 * 创建人：JavaScript  
	 * 创建时间：2018年3月13日 下午12:54:44    
	 * 修改人：JavaScript 
	 * 修改时间：2018年3月13日 下午12:54:44    
	 * 修改备注： 
	 * @param id 用户ID
	 * @return
	 * 	用户全部信息
	 * </pre>    
	 */
	@Select("select * from t_user u " +
			"    left join t_user_role ur" +
			"    on u.userid = ur.userid" +
			"    left join t_role r" +
			"    on r.roleId = ur.roleid" +
			"    left join t_user_tree ut" +
			"    on u.userid = ut.userid" +
			"    left join t_tree t" +
			"    on t.id = ut.treeid" +
			"  where  u.userid = #{id}")
	List<User> queryUserAllByuserId(@Param("id") Integer id) throws Exception;

}
