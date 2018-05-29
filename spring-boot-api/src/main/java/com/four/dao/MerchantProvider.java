package com.four.dao;

import com.four.model.GuangGao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class MerchantProvider {
	//下载指定的字段的excel
	public String guangGaoManager(@Param("names") String names){
		return new SQL(){{
			SELECT("names");
			FROM("t_guanggao");
			WHERE(" 1=1 ");
	/*		if(guangGao.getGuangGaoName() != null && guangGao.getGuangGaoName() != ""){
				WHERE(" guangGaoName = #{guangGaoName}");
			}
			if(guangGao.getGuangGaoprice() != null && guangGao.getGuangGaoprice() != 0){
				WHERE(" guangGaoprice = #{guangGaoprice}");
			}
			if(guangGao.getGuangGaoStart() != null && guangGao.getGuangGaoStart() != ""){
				WHERE(" guangGaoStart = #{guangGaoStart}");
			}*/


		}}.toString();
	}
	public String updateGuangGaoById(@Param("G")GuangGao guangGao){
		SQL sql = new SQL(){{
			UPDATE("t_guanggao");
			if(guangGao.getGuangGaoName() != null){
				SET(" guangGaoName = #{G.guangGaoName} ");
			}
			if(guangGao.getGuangGaoShuoMing() != null){
				SET(" guanggaoshuoming = #{G.guangGaoShuoMing} ");
			}
			if(guangGao.getGuangGaoStart() != null){
				SET(" guanggaostart = #{G.guangGaoStart} ");
			}
			if(guangGao.getGuangGaoEnd() != null){
				SET(" GuangGaoEnd = #{G.guangGaoEnd} ");
			}
			if(guangGao.getGuangGaoPhoto() != null){
				SET(" GuangGaoPhoto = #{G.guangGaoPhoto} ");
			}
			if(guangGao.getGuangGaoprice() != null){
				SET(" GuangGaoprice = #{G.guangGaoprice} ");
			}
			WHERE("guanggaoid = #{G.guangGaoId}");
		}};
		return sql.toString();
	}
	public String saveGuangGaoByModel(GuangGao guangGao){
		SQL sql = new SQL(){{
			INSERT_INTO("t_guanggao");
			if(guangGao.getGuangGaoName() != null){
				VALUES("guangGaoName","#{guangGaoName}");
			}
			if(guangGao.getGuangGaoShuoMing() != null){
				VALUES(" guanggaoshuoming ","#{guangGaoShuoMing}");
			}
			if(guangGao.getGuangGaoStart() != null){
				VALUES(" guanggaostart  ","#{guangGaoStart}");
			}
			if(guangGao.getGuangGaoEnd() != null){
				VALUES(" GuangGaoEnd ","#{guangGaoEnd}");
			}
			if(guangGao.getGuangGaoPhoto() != null){
				VALUES(" GuangGaoPhoto "," #{guangGaoPhoto}");
			}
			if(guangGao.getGuangGaoprice() != null){
				VALUES(" GuangGaoprice ","#{guangGaoprice}");
			}
			if(guangGao.getGuangGaoNum() != null){
				VALUES(" guangGaoNum ","#{guangGaoNum}");
			}
			if(guangGao.getGuangGaoGuanWang() != null){
				VALUES(" guangGaoGuanWang ","#{guangGaoGuanWang}");
			}
			if(guangGao.getGuangGaoWidth() != null){
				VALUES(" guangGaoWidth ","#{guangGaoWidth}");
			}
			if(guangGao.getGuangGaoHeight() != null){
				VALUES(" guangGaoHeight ","#{guangGaoHeight}");
			}
			if(guangGao.getGuangGaoStatus() != null){
				VALUES(" guangGaoStatus ","#{guangGaoStatus}");
			}

		}};
		return sql.toString();
	}
	public String queryGuangGao(){

		return new SQL(){{
			SELECT("*");
			FROM("t_guanggao");
			WHERE(" 1=1 ");
	/*		if(guangGao.getGuangGaoName() != null && guangGao.getGuangGaoName() != ""){
				WHERE(" guangGaoName = #{guangGaoName}");
			}
			if(guangGao.getGuangGaoprice() != null && guangGao.getGuangGaoprice() != 0){
				WHERE(" guangGaoprice = #{guangGaoprice}");
			}
			if(guangGao.getGuangGaoStart() != null && guangGao.getGuangGaoStart() != ""){
				WHERE(" guangGaoStart = #{guangGaoStart}");
			}*/

		}}.toString();
	}

	public String queryTreeByPid(@Param("pid")Integer pid){
		return new SQL(){{
			SELECT("*");
			FROM("t_menu");
			WHERE("pid = #{pid}");
		}}.toString();
	}
}
