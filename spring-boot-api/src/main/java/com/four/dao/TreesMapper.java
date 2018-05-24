package com.four.dao;

import com.four.model.Tree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */
@Mapper
public interface TreesMapper {


    @Select("select  *  from t_trees")
    List<Tree> getTree();
}
