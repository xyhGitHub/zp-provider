/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LogsServiceImpl
 * Author:   Administrator
 * Date:     2018/5/25 16:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.four.service.impl;

import com.four.model.Logs;
import com.four.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/25
 * @since 1.0.0
 */
@Service("logService")
public class LogsServiceImpl implements LogService {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Logs> querylogs() {

        Query query = new Query();

        List<Logs> list = mongoTemplate.find(query, Logs.class);

        return list;
    }

    /* (non-Javadoc)
	 * 日志统计报表
	 */
    @Override
    public List selecttongji() {

        List list =new ArrayList();



        Query query = new Query();

        //求所有

        Query query1 = new Query();
        Query query2 = new Query();
        Query query3 = new Query();


        //发生异常的统计，异常中出的错
        long countz = mongoTemplate.count(query, Logs.class);
        String methodName = "methodName";//条件查询！！！如下：方法
        String excepName = "exception";//条件查询！！！如下：方法
        query.addCriteria(Criteria.where(excepName).regex(".*?\\发生.*"));

        //求异常方法
        query1.addCriteria(Criteria.where(methodName).in("save","add","login"));
//        Criteria criteria=Criteria.where("name").is("tom").and("studentNum").in(numberList);
        query2.addCriteria(Criteria.where(methodName).regex("del.*"));
        query3.addCriteria(Criteria.where(methodName).regex("update.*"));


        long county = mongoTemplate.count(query, Logs.class);

        //两者相减得差

        long countzeng = mongoTemplate.count(query1, Logs.class);
        long countshan = mongoTemplate.count(query2, Logs.class);
        long countgai = mongoTemplate.count(query3, Logs.class);
        long countcha = countz-countzeng-countshan-countgai;

        //查询的
        long countr = countz-county;

        list.add(countr);
        list.add(countz);
        list.add(county);

        //返回 list

        list.add(countzeng);
        list.add(countshan);
        list.add(countgai);
        list.add(countcha);
        return list;
    }

}
