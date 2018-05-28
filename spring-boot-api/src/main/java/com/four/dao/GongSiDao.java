package com.four.dao;

import com.four.model.GongSi;
import com.four.model.GongSiBoos;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
@Mapper
public interface GongSiDao {

    @Select("select * from  t_company")
    List<GongSi> selectgongsi();

    @Select(" select * from t_company  limit 0,3")
    List<GongSi> querygongsi(@Param("gongsi") GongSi gongsi, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from t_company " )
    long queryCount(@Param("gongsi") GongSi gongsi);

    @Delete("delete from t_company where comid = #{comid}")
    void delgongsi(@Param("comid") Integer comid);

    @Select("select * from com_boss where  comid=#{id}")
    List<GongSiBoos> queryall(@Param("id") Integer id);


    @Update("update t_company  set comshenhe ='2' where comid=#{id}")
    void updatatongguo(@Param("id") Integer id);

    @Insert("insert into  t_companay values (#{gongSi.comname})")
    void editgongshiname(GongSi gongSi);


    @Update(" UPDATE  t_company SET comphoto =#{photo} WHERE comid=#{comid}")
    void gongsieditphoto(@Param("comid") Integer comid, @Param("photo")  String photo);

    @Select("select t1.*,t3.name from t_company t1, t_zhiweigreat t2, t_zhiweilittle t3 where t1.comid=t2.comid and t2.id=t3.greatid")
    List<GongSi> querysshowbyidzhiwei();

    @Insert("insert into t_company (comid) values (#{loginId})")
    void addGongSiId(@Param("loginId") Integer loginId);

   /* @Insert("insert into t_guanggao values(#{guanggao.gid},#{guanggao.imageurl},#{guanggao.pid},#{guanggao.info},#{guanggao.companyid},#{guanggao.status})")
    void saveguanggao(@Param("guanggao") Guanggao guanggao);
    @Update("update  t_guanggao set status ='2' where gid = #{ggid}")
    void updatestatus(@Param("ggid") String ggid);
    @Delete("delete from t_guanggao where gid = #{gid}")
    void deleteguanggao(@Param("gid") String gid);
*/
}
