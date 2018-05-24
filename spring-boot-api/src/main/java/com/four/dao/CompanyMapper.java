package com.four.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.four.model.Company;
@Mapper
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer Companyid);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer Companyid);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

	
	long querycount(Company Company);

	
	List<Company> queryCompany(@Param("Company") Company Company, @Param("start") int start, @Param("end") Integer rows);

	//新增
	int insertCompany(Company Company);
	
	//删除
	Integer deleteCompanyByIDs(@Param(value = "ar") String[] split);
//审核
	Integer updateCompanyById(Integer companyid);
}