/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ZhiweiServiceImpl
 * Author:   Administrator
 * Date:     2018/5/21 11:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;

import com.four.dao.ZhiweiDao;
import com.four.model.User;
import com.four.model.Zhiwei;
import com.four.model.ZhiweiGreat;
import com.four.model.ZhiweiLittle;
import com.four.service.ZhiweiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/21
 * @since 1.0.0
 */
@Service("zhiweiService")
public class ZhiweiServiceImpl implements ZhiweiService{

    @Autowired
    private ZhiweiDao zhiweiDao;

    @Override
    public List<ZhiweiGreat> querygreatzw() {
        return zhiweiDao.querygreatzw();
    }

    @Override
    public List<ZhiweiLittle> querylittlezw() {
        return zhiweiDao.querylittlezw();
    }

    @Override
    public List<ZhiweiGreat> querycomtree() {

        return zhiweiDao.querycomtree();
    }





    @Override
    public List<Zhiwei> queryzhiweishow() {

        return zhiweiDao.queryzhiweishow();
    }

    @Override
    public void savezhiwei(Zhiwei zhiwei) {
        zhiweiDao.savezhiwei(zhiwei);
    }

    @Override
    public List<Zhiwei> queryzhiweishowlist() {


        return zhiweiDao.queryzhiweishowlist();
    }

    @Override
    public List<User> quaryTouDilist(Integer comid) {


        return zhiweiDao.quaryTouDilist(comid);
    }
}
