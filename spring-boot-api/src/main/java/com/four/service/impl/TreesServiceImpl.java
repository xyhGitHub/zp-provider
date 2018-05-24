/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TreeServiceImpl
 * Author:   Administrator
 * Date:     2018/5/16 17:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;

import com.four.dao.TreesMapper;
import com.four.model.Tree;
import com.four.service.TreesService;
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
@Service("treesService")
public class TreesServiceImpl implements TreesService {
    @Autowired
    private TreesMapper treeMapper;



    @Override
    public List<Tree> getTree() {
        return treeMapper.getTree();
    }
}
