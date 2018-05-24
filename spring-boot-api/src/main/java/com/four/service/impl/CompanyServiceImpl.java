package com.four.service.impl;

import java.util.List;

import com.alibaba.dubbo.common.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.four.dao.CompanyMapper;
import com.four.model.Company;
import com.four.service.CompanyService;


@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyMapper CompanyDao;

	@Override
	public JSONObject queryCompany(Company Company, Integer rows, Integer page) {
		long total=CompanyDao.querycount(Company);
		int start = page*rows-rows;
		List<Company> list= CompanyDao.queryCompany(Company,start,rows);
		
		JSONObject jss= new JSONObject();
		jss.put("total", total);
		jss.put("rows", list);
		return jss;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.CompanyService#insertCompany(com.four.model.Company)    
	 */
	
	//新增
	@Override
	public int insertCompany(Company Company) {
		// TODO Auto-generated method stub
		return CompanyDao.insertCompany(Company);
	}
//	角色删除批量删
	@Override
	public Integer deleteCompanyByIDs(String ids) {
		String[] split = ids.split(",");
		return CompanyDao.deleteCompanyByIDs(split);
	}
//审核
	@Override
	public Integer updateCompanyById(Integer companyid) {
		// TODO Auto-generated method stub
		return CompanyDao.updateCompanyById(companyid);
	}


}
