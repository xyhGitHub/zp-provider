package com.four.dao;

import com.four.model.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */
@Mapper
public interface INoticeDao {
    @Update("update t_notice" +
            "        set noticeTitle = #{noticeTitle,jdbcType=VARCHAR},\n" +
            "         noticeContent = #{noticeContent,jdbcType=VARCHAR},\n" +
            "         noticeType = #{noticeType,jdbcType=VARCHAR},\n" +
            "        where noticeId = #{noticeId,jdbcType=INTEGER}")
    void updateNoticeById(@Param("notice")Notice notice);

    @Select("select * from t_notice " +
            "        where noticeId = #{noticeId}")
    Notice queryNoticeById(@Param("noticeId") String noticeId);

    @Select("select count(*) from t_notice where 1=1")
    long queryCount(@Param("notice") Notice notice);

    @Select(" select * from t_notice where 1=1 limit #{start},#{rows}")
    List<Notice> queryNoticeInfo(@Param("start") int start, @Param("rows") int rows,@Param("notice") Notice notice);

    @Insert("insert into t_notice (noticeId, noticeTitle, noticeContent,noticeType)\n" +
            "        values\n" +
            "        (#{noticeId,jdbcType=INTEGER},\n" +
            "        #{noticeTitle,jdbcType=VARCHAR},\n" +
            "        #{noticeContent,jdbcType=VARCHAR},\n" +
            "        #{noticeType,jdbcType=VARCHAR})")
    void addNotice(@Param("notice") Notice notice);

    @Delete("delete from t_notice\n" +
            "        where noticeId in\n" +
            "        <foreach collection=\"list\" item=\"idList\" separator=\",\" open=\"(\" close=\")\">\n" +
            "            #{idList}\n" +
            "        </foreach>")
    void deleteSome(@Param("idLists")List<String> idLists);

//    List<Notice> queryNoticeType();
}
