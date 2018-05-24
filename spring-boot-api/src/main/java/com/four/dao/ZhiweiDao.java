package com.four.dao;

import com.four.model.Zhiwei;
import com.four.model.ZhiweiGreat;
import com.four.model.ZhiweiLittle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */
@Mapper
public interface ZhiweiDao {

//    @Select("select t1.* t2.comid  from t_zhiweigreat t1,t_company t2 where t1.comid=t2.comid")
    @Select("select *  from t_zhiweigreat ")
    List<ZhiweiGreat> querygreatzw();

//    @Select("select t2.*  from t_zhiweigreat t1,t_zhiweilittle t2 where t1.id= t2.greatid ")
    @Select( "select *  from t_zhiweilittle ")
    List<ZhiweiLittle> querylittlezw();

    @Select("select t2.*  from t_zhiweigreat t1,t_zhiweilittle t2 where t1.id= t2.greatid ")
    List<ZhiweiGreat> querycomtree();

    @Select("select t1.* ,t2.comname ,t4.name from t_zhiwei t1,t_company t2,t_zhiweigreat  t3,t_zhiweilittle t4 where t1.comid=t2.comid AND t1.zhiweigreatid=t3.id  AND t3.id=t4.greatid AND t2.comshenhe=2")
    List<Zhiwei> queryzhiweishow();

    @Insert("insert into t_zhiwei (comid,zhiweigreatid,xingzhi,xinzi,city,jingyan,xueli,youhuo) VALUES(#{zhiwei.comid},#{zhiwei.zhiweigreatid},#{zhiwei.xingzhi},#{zhiwei.xinzi},#{zhiwei.city},#{zhiwei.jingyan},#{zhiwei.xueli},#{zhiwei.youhuo})")
    void savezhiwei(@Param("zhiwei") Zhiwei zhiwei);

    @Select("select t1.* ,t2.comname  ,t4.name from t_zhiwei t1,t_company t2 ,t_zhiweigreat t3 ,t_zhiweilittle t4 where t1.comid= t2.comid and t1.zhiweigreatid=t3.id and t3.id =t4.greatid")
    List<Zhiwei> queryzhiweishowlist();
}
   /* @Insert("insert into t_guanggao values(#{guanggao.gid},#{guanggao.imageurl},#{guanggao.pid},#{guanggao.info},#{guanggao.companyid},#{guanggao.status})")
    void saveguanggao(@Param("guanggao") Guanggao guanggao);
    @Update("update  t_guanggao set status ='2' where gid = #{ggid}")
    void updatestatus(@Param("ggid") String ggid);
    @Delete("delete from t_guanggao where gid = #{gid}")
    void deleteguanggao(@Param("gid") String gid);
*/