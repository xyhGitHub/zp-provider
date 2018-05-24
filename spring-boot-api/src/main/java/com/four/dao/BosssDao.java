package com.four.dao;

import com.four.model.GongSi;
import com.four.model.GongSiBoos;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */
@Mapper
public interface BosssDao {

    @Select("select t1.*,t2.comname from com_boss t1, t_company t2 where t1.comid=t2.comid limit 0,3 ")
    List<GongSiBoos> queryboss(@Param("boss") GongSiBoos boss, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from com_boss")
    long queryCount(@Param("boss") GongSiBoos boss);

    @Delete("delete from com_boss where id=#{id} ")
    void delboss(@Param("id") Integer id);
}
