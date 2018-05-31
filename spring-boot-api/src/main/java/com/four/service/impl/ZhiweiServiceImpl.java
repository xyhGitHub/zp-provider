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
import com.four.model.*;
import com.four.service.ZhiweiService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Autowired
    private SolrClient httpSolrServer;

    @Override
    public List<ZhiweiGreat> querygreatzw() {
        return zhiweiDao.querygreatzw();
    }

    @Override
    public List<ZhiweiLittle> querylittlezw() {
        return zhiweiDao.querylittlezw();
    }

    @Override
    @Cacheable("querycomtree")
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
        Integer comids = zhiwei.getComid();
        GongSi comName = zhiweiDao.getComName(comids);

        SolrInputDocument doc =new SolrInputDocument();

        doc.addField("id", zhiwei.getId());
        doc.addField("comid", zhiwei.getComid());
        doc.addField("zhiweigreatid", zhiwei.getZhiweigreatid());
        doc.addField("zhiweiname",zhiwei.getZhiweiname());
        //由职位中的公司id查询出来的公司名字
        doc.addField("comname",comName.getComname());
        doc.addField("xinzi",zhiwei.getXinzi());
        doc.addField("xingzhi",zhiwei.getXingzhi());
        doc.addField("city",zhiwei.getCity());
        doc.addField("jingyan",zhiwei.getJingyan());
        doc.addField("xueli",zhiwei.getXueli());
        doc.addField("youhuo",zhiwei.getYouhuo());

        try {
            httpSolrServer.add(doc);
            httpSolrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Zhiwei> queryzhiweishowlist() {

        return zhiweiDao.queryzhiweishowlist();
    }

    @Override
    public List<User> quaryTouDilist(Integer comid) {


        return zhiweiDao.quaryTouDilist(comid);
    }

    @Override
    public List<Zhiwei> selectJianLIlist(Integer comid) {

        return zhiweiDao.selectJianLIlist(comid);

    }

    @Override
    public void deleteJianLIById(Integer zhiweiid, Integer xiaoid, Integer daid) {
        zhiweiDao.deleteJianLIById(zhiweiid,xiaoid,daid);
    }

    @Override
    public Zhiwei updatajianlihuixian(Integer zhiweiid, Integer xiaoid, Integer daid) {
        return zhiweiDao.updatajianlihuixian(zhiweiid,xiaoid,daid);
    }
}
