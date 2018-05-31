package com.four.service;

import com.four.model.User;
import com.four.model.Zhiwei;
import com.four.model.ZhiweiGreat;
import com.four.model.ZhiweiLittle;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */
public interface ZhiweiService {
    List<ZhiweiGreat> querygreatzw();

    List<ZhiweiLittle> querylittlezw();
    List<ZhiweiGreat> querycomtree();

    List<Zhiwei> queryzhiweishow();

    void savezhiwei(Zhiwei zhiwei);


    List<Zhiwei> queryzhiweishowlist();
    //人力查看
    List<User>  quaryTouDilist(Integer comid);

    List<Zhiwei> selectJianLIlist(Integer comid);

    void deleteJianLIById(Integer zhiweiid,Integer xiaoid,Integer daid);

    /**
     * 简历回显
     * @param zhiweiid
     * @param xiaoid
     * @param daid
     * @return
     */
    Zhiwei updatajianlihuixian(Integer zhiweiid, Integer xiaoid, Integer daid);

}
