/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NewServiceImpl
 * Author:   Administrator
 * Date:     2018/5/18 21:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;

import com.four.dao.JianLiDao;
import com.four.model.JianLi;
import com.four.service.JianLiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/18
 * @since 1.0.0
 */
@Service("jianLiService")
public class JianLiServiceImpl implements JianLiService {

    @Autowired
    private JianLiDao jianLiDao;


    @Override
    public void addJianLi1(JianLi jianLi) {
        jianLiDao.addJianLi1(jianLi);
    }

    @Override
    public void addJianLi2(JianLi jianLi) {
        jianLiDao.addJianLi2(jianLi);
    }

    @Override
    public void addJianLi3(JianLi jianLi) {
        jianLiDao.addJianLi3(jianLi);
    }

    @Override
    public void addJianLi4(JianLi jianLi) {
        jianLiDao.addJianLi4(jianLi);
    }

    @Override
    public void addJianLi5(JianLi jianLi) {
        jianLiDao.addJianLi5(jianLi);
    }

    @Override
    public void addJianLi6(JianLi jianLi) {
        jianLiDao.addJianLi6(jianLi);
    }
}
