package com.four.dao;

import com.four.model.JianLi;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */
@Mapper
public interface JianLiDao {
    @Update("update t_jianli  set qiwangchengshi =#{qiwangchengshi},qiwangzhiye=#{qiwangzhiye},qiwangzhiwei=#{qiwangzhiwei},qiwangyuexin=#{qiwangyuexin} where userid=#{jid}")
    void addJianLi1(@Param("jianLi")JianLi jianLi);
    @Update("update t_jianli  set gongzuogongsimingcheng =#{gongzuogongsimingcheng},gongzuozhiweimingcheng=#{gongzuozhiweimingcheng},gongzuokaishinianfen=#{gongzuokaishinianfen},gongzuokaishiyuefen=#{gongzuokaishiyuefen},gongzuojiesunainfen=#{gongzuojiesunainfen},gongzuojiesuyuefen=#{gongzuojiesuyuefen} where userid=#{jid}")
    void addJianLi2(@Param("jianLi")JianLi jianLi);
    @Update("update t_jianli  set xiangmumingcheng =#{xiangmumingcheng},xiangmuzhiwu=#{xiangmuzhiwu},xiangmukaishinianfen=#{xiangmukaishinianfen},xiangmukaishinianfen=#{xiangmukaishinianfen},xiangmujiesunianfen=#{xiangmujiesunianfen},xiangmujiesuyuefen=#{xiangmujiesuyuefen},xiangmumiaosu=#{xiangmumiaosu} where userid=#{jid}")
    void addJianLi3(@Param("jianLi")JianLi jianLi);

    @Update("update t_jianli  set xuexiaomingcheng =#{xuexiaomingcheng},xueli=#{xueli},zhuanyemingcheng=#{zhuanyemingcheng},xuexiaokaishinianfen=#{xuexiaokaishinianfen},xuexiaojiesunianfen=#{xuexiaojiesunianfen} where userid=#{jid}")
    void addJianLi4(@Param("jianLi")JianLi jianLi);

    @Update("update t_jianli  set ziwojieshao =#{ziwojieshao} where userid=#{jid}")
    void addJianLi5(@Param("jianLi")JianLi jianLi);

    @Update("update t_jianli  set xiangmulujing =#{xiangmulujing},zuopinshuoming=#{zuopinshuoming} where userid=#{jid}")
    void addJianLi6(@Param("jianLi")JianLi jianLi);

    @Insert("insert into t_jianli (jid) values (#{jianLi.userid})")
    void addJianLiInfo(@Param("jianLi") JianLi jianLi);
}
