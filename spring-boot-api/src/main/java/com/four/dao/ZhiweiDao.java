package com.four.dao;

import com.four.model.*;
import org.apache.ibatis.annotations.*;

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



    @Select("select * from (select uj.userid,t2.zhiweiname from (select u.userid,t1.zhiweiname from t_userjianli u ,(SELECT g.userid,z.zhiweiname from t_zhiwei z,t_gsjl g where z.comid=1 and z.id=g.comid) t1 where u.jianliid = t1.userid ) t2, t_userjianli uj where t2.userid= uj.userid) t3, t_user u3  where t3.userid=u3.userid")
    List<User> quaryTouDilist(Integer comid);

    //根据职位的公司id查询出公司标的公司名
    @Select("select comname from t_company where comid=#{comids}")
    GongSi getComName(@Param("comids") Integer comids);

    @Select("select t1.* ,t2.comname  ,t4.name ,t4.id xiaoid,t3.id daid  from t_zhiwei t1,t_company t2 ,t_zhiweigreat t3 ,t_zhiweilittle t4 where t1.comid= t2.comid and t1.zhiweigreatid=t3.id and t3.id =t4.greatid  and  t2.comid =#{comid}")
    List<Zhiwei> selectJianLIlist(@Param("comid")Integer comid);

    @Delete("DELETE  t3 FROM t_zhiwei t1,t_zhiweigreat t2,t_zhiweilittle t3   WHERE   t1.id=#{zhiweiid} and t2.id=#{daid} and t3.id=#{daid}")
    void deleteJianLIById(@Param("zhiweiid")Integer zhiweiid,@Param("xiaoid") Integer xiaoid,@Param("daid") Integer daid);

    @Select("SELECT  t1.*,t2.name daname ,t3.name  xiaoname FROM t_zhiwei t1,t_zhiweigreat t2,t_zhiweilittle t3   WHERE   t1.id=#{zhiweiid} and t2.id=#{daid} and t3.id=#{xiaoid}    ")
    Zhiwei updatajianlihuixian(@Param("zhiweiid")Integer zhiweiid, @Param("xiaoid")Integer xiaoid,@Param("daid") Integer daid);
}
