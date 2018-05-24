/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: GongSiServiceImpl
 * Author:   Administrator
 * Date:     2018/5/16 14:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;


import com.four.dao.GongSiDao;
import com.four.model.GongSi;
import com.four.model.GongSiBoos;
import com.four.service.GongsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/16
 * @since 1.0.0
 */
@Service("gongsiService")
public class GongSiServiceImpl implements GongsiService {

        @Autowired
        private GongSiDao gongsidao;


        public List<GongSi> selectgongsi(){

                return  gongsidao.selectgongsi();
        }

        /**
         * .公司查询
         * @param gongsi
         * @param offset
         * @param pageSize
         * @return
         */
        @Override
        public List<GongSi> querygongsi(GongSi gongsi, Integer offset,Integer pageSize) {


                return gongsidao.querygongsi(gongsi,offset,pageSize);
        }

        /**
         * 条数
         * @param gongsi
         * @return
         */
        @Override
        public long queryCount(GongSi gongsi) {
                return gongsidao.queryCount(gongsi);
        }

        /**
         * 删除公司
         * @param comid
         */
        @Override
        public void delgongsi(Integer comid) {
                gongsidao.delgongsi(comid);
        }

        @Override
        public List<GongSiBoos> queryall(Integer id) {
                return gongsidao.queryall(id);
        }

        @Override
        public void updatatongguo(Integer id) {
                gongsidao.updatatongguo(id);
        }

}
