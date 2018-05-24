
package com.four.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.four.dao.DaoTree;
import com.four.model.Tree;
import com.four.model.User;
import com.four.service.IServiceTree;



@Service("treeService")
public class TreeerviceImpl implements IServiceTree{
	@Autowired
	private DaoTree daotree;
	
	
	/* (non-Javadoc)    
	 * @see com.smn.service.impl.IServiceTree#treelist(com.smn.model.Tree)    
	 */
	public List<Tree> treelist() throws Exception{

		List<Tree> treelist = daotree.treelist();
		return treelist;
	}


	/* (non-Javadoc)    
	 * @see com.smn.service.IServiceTree#insertSelective(com.smn.model.Tree)    
	 */
	public int insertSelective(Tree tree) {


		return daotree.insert(tree);
	}


	/* (non-Javadoc)    
	 * @see com.smn.service.IServiceTree#deleteByPrimaryKey(java.lang.Integer)    
	 */
	public void deleteByPrimaryKey(Integer id) {
		daotree.deleteByPrimaryKey(id);

		
	}


	/* (non-Javadoc)    
	 * @see com.smn.service.IServiceTree#selectByPrimaryKey(java.lang.Integer)    
	 */
	public Tree selectByPrimaryKey(Integer id) {

		return daotree.selectByPrimaryKey(id);
	}


	/* (non-Javadoc)    
	 * @see com.smn.service.IServiceTree#updateByPrimaryKey(com.smn.model.Tree)    
	 */
	public void updateByPrimaryKey(Tree tree) {
		daotree.updateByPrimaryKey(tree);
	}


	/* (non-Javadoc)    
	 * @see com.smn.service.IServiceTree#queryRoleTree(java.lang.String)    
	 */
	public List<Tree> queryRoleTree(int roleId) {

		
		
		return daotree.queryRoleTree(roleId);
	}


	/* (non-Javadoc)    
	 * @see com.smn.service.IServiceTree#selectTreeList(com.smn.model.Tree)    
	 */
	public List<Tree> selectTreeList(Tree tre) {
		List<Tree> list =daotree.selectTreeList(tre);

		return list;
	}


	/* (non-Javadoc)    
	 * @see com.four.service.IServiceTree#addTreeRoleRelation(java.lang.String, java.lang.String)    
	 */
	@Override
	public void addTreeRoleRelation(String ids, String roleId) {
		
		daotree.deleteTreeRoleRealtion(roleId);
		
		String[] idss=ids.split(",");
		List<Map<String,Object>> maps =new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < idss.length; i++) {
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("roleId", roleId);
			map.put("treeId", idss[i].trim());
			maps.add(map);
		}
		daotree.addTreeRoleRelation(maps);
		
	}


	/* (non-Javadoc)    
	 * @see com.four.service.IServiceTree#getRoleTreeList(com.four.model.User)    
	 */
	@Override
	@Cacheable(value="redis",key="'getRoleTreeList'+#user.getUserid()")
	public Set<Map<String, Object>> getRoleTreeList(User user) {
		
		
		
		return daotree.getRoleTreeList(user);
	}





}
