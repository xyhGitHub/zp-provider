package com.four.dao;

import com.four.model.Oss;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2018/5/17.
 */
@Mapper
public interface IOssDao {
    @Insert("insert into t_oss" +
            "  <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">" +
            "  <if test=\"id !=null and id != ''\">" +
            "  id," +
            "  </if>" +
            "  <if test=\"photo !=null and photo != ''\">" +
            "  photo," +
            "  </if>" +
            "  </trim>")
    void saveFile(Oss oss);

}
