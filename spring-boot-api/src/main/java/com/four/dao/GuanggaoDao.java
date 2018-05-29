package com.four.dao;

import com.four.model.Company;
import com.four.model.GuangGao;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GuanggaoDao {

	/**
	 * 广告查询展示
	 * @return
	 */
//	@Select(" select * from t_guanggao ")
	@SelectProvider(type = MerchantProvider.class,method = "queryGuangGao")
	List<GuangGao> queryGuangGao();

	/**
	 * 删除广告
	 *
	 */
	@Delete(" delete from t_guanggao where guangGaoId = #{guangGaoId} ")
	void deleteGuangGao(String guangGaoId);

	/**
	 * 回显
	 * @param guangGao
	 */
	@Select(" select * from t_guanggao where  guangGaoId = #{guangGaoId} ")
	GuangGao queryGuangGaoById(GuangGao guangGao);


	/**
	 * 修改保存的方法
	 * @param guangGao
	 */
	@UpdateProvider(type = MerchantProvider.class,method = "updateGuangGaoById")
	void updateGuangGaoById(@Param("G") GuangGao guangGao);

	/**
	 * 广告的保存方法
	 * @param guangGao
	 */
	@InsertProvider(type = MerchantProvider.class,method = "saveGuangGaoByModel")
	void saveGuangGaoByModel(GuangGao guangGao);


	//	分页加查询
	@Select("  SELECT    count(*)   FROM   t_guanggao  ")
	Long getSum();
	@Select("   SELECT    *  FROM   t_guanggao    LIMIT  #{offset},#{limit}    ")
	List<GuangGao> seriesList(@Param("offset") Integer offset, @Param("limit") Integer limit);

	//查询前台展示广告的方法
	@Select(" SELECT * FROM t_guanggao G WHERE G.guangGaoStatus=#{ggzt} ")
	List<GuangGao> queryGuangGaoQianTai(Integer ggzt);

	@Select(" SELECT * from t_guanggao ")
	List<GuangGao> guangGaoManager();


	/*//导出广告,先查询所有广告
//	@Select(" SELECT #{names} from t_guanggao  ")
	@SelectProvider(type = MerchantProvider.class,method = "guangGaoManager")
	List<GuangGao> guangGaoManager(@Param("names") String names);*/
}
