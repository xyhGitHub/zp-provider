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

import com.four.dao.NewsDao;
import com.four.model.NewTree;
import com.four.service.NewService;
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
@Service("newService")
public class NewServiceImpl implements NewService {

    @Autowired
    private NewsDao newsDao;



    @Override
    public List<NewTree> querynews(NewTree newTree, Integer offset, Integer size) {


        return newsDao.querynews(newTree,offset,size);
    }

    @Override
    public Long quentCount(NewTree newTree) {


        return newsDao.queryCount(newTree);
    }

    @Override
    public void delnews(Integer id) {
        newsDao.delnews(id);
    }
}
