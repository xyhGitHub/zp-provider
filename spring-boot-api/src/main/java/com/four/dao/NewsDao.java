package com.four.dao;

import com.four.model.NewTree;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */
@Mapper
public interface NewsDao {

    @Select(" select * from com_news limit 0,3")
    List<NewTree> querynews(@Param("newTree") NewTree newTree, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from com_news")
    Long queryCount(@Param("newTree") NewTree newTree);

    @Delete("delete from com_news where id=#{id}")
    void delnews(@Param("id") Integer id);
}
