/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BossServiceImpl
 * Author:   Administrator
 * Date:     2018/5/18 20:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;

import com.four.dao.BosssDao;
import com.four.model.GongSi;
import com.four.model.GongSiBoos;
import com.four.service.BosssService;
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
@Service("bosssService")
public class BosssServiceImpl implements BosssService {

    @Autowired
    private BosssDao bossDao;

    @Override
    public List<GongSiBoos> queryboss(GongSiBoos boss, Integer offset, Integer pageSize) {

        return bossDao.queryboss(boss,offset,pageSize);
    }
    @Override
    public long queryCount(GongSiBoos boss) {
        return bossDao.queryCount(boss);
    }

    @Override
    public void delboss(Integer id) {
        bossDao.delboss(id);
    }
}
