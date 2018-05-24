package com.four.service.impl;

import com.four.dao.INoticeDao;
import com.four.model.Notice;
import com.four.model.Notice;
import com.four.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/20.
 */
@Service("noticeService")
public class INoticeServiceImpl implements INoticeService {

    @Autowired
    private INoticeDao noticeDao;

    @Override
    public Map<String, Object> queryNoticeInfo(int page, int rows, Notice notice) {
        Map<String, Object> map = new HashMap<String, Object>();

        //查询总条数
        long total = noticeDao.queryCount(notice);
        //计算开始位置和结束位置
        int start = (page-1)*rows;
        List<Notice> list=noticeDao.queryNoticeInfo(start,rows,notice);

        System.out.println(list.size()+"====================");

        map.put("total", total);
        map.put("rows", list);

        return map;
    }

    @Override
    public void addNotice(Notice notice) {
        noticeDao.addNotice(notice);
    }

    @Override
    public void deleteSome(String idList) {
        String[] idM=idList.split(",");
        List<String> idLists =new ArrayList<String>();
        for (String string : idM) {
            idLists.add(string);
        }
         noticeDao.deleteSome(idLists);
    }

    @Override
    public Notice queryNoticeById(String noticeId) {

        Notice notice =  noticeDao.queryNoticeById(noticeId);
        return notice;
    }

    @Override
    public void updateNoticeById(Notice notice) {
        noticeDao.updateNoticeById(notice);
    }

//    @Override
//    public List<Notice> queryNoticeType() {
//
//        List<Notice> noticeList =  noticeDao.queryNoticeType();
//
//        return noticeList;
//    }
}
