/** 
 * <pre>项目名称:ssmsolrdemo 
 * 文件名称:SolrServiceImpl.java 
 * 包名:com.jk.service.impl 
 * 创建日期:2018年5月10日下午7:19:27 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */
package com.four.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.four.model.Zhiwei;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.solr.client.solrj.SolrClient;

import com.four.service.SolrService;
import com.four.model.PageModel;
import org.springframework.util.StringUtils;

/**
 * <pre>
 * 项目名称：ssmsolrdemo    
 * 类名称：SolrServiceImpl    
 * 类描述：    
 * 创建人：周录军     
 * 创建时间：2018年5月10日 下午7:19:27    
 * 修改人：周录军 
 * 修改时间：2018年5月10日 下午7:19:27    
 * 修改备注：       
 * @version
 * </pre>
 */
@Service("solrService")
public class SolrServiceImpl implements SolrService {
	@Autowired
	private SolrClient httpSolrServer;

	@Override
	public PageModel<Zhiwei> getSolrList(PageModel pageModel, Zhiwei productAndCate) throws Exception {

		//获取query 对象
		SolrQuery query = new SolrQuery();
		
		//设置查询关键字  职位名称
		if (!StringUtils.isEmpty(productAndCate.getZhiweiname())) {
			query.set("q", "zhiweiname:" + productAndCate.getZhiweiname());
		} else {
			query.set("q", "*:*");
			
		}
//		//设置查询关键字  职位性质
//		if (!StringUtils.isEmpty(productAndCate.getXingzhi())) {
//			query.set("q", "xingzhi:" + productAndCate.getXingzhi());
//		} else {
//			query.set("q", "*:*");
//
//		}
//		//设置查询关键字  职位薪资
//		if (!StringUtils.isEmpty(productAndCate.getXinzi())) {
//			query.set("q", "xinzi:" + productAndCate.getXinzi());
//		} else {
//			query.set("q", "*:*");
//
//		}
//		//设置查询关键字  城市
//		if (!StringUtils.isEmpty(productAndCate.getCity())) {
//			query.set("q", "city:" + productAndCate.getCity());
//		} else {
//			query.set("q", "*:*");
//
//		}
//		//设置查询关键字  职位经验
//		if (!StringUtils.isEmpty(productAndCate.getJingyan())) {
//			query.set("q", "jingyan:" + productAndCate.getJingyan());
//		} else {
//			query.set("q", "*:*");
//
//		}
//		//设置查询关键字  职位学历
//		if (!StringUtils.isEmpty(productAndCate.getXueli())) {
//			query.set("q", "xueli:" + productAndCate.getXueli());
//		} else {
//			query.set("q", "*:*");
//
//		}
//		//设置查询关键字  职位诱惑
//		if (!StringUtils.isEmpty(productAndCate.getYouhuo())) {
//			query.set("q", "youhuo:" + productAndCate.getYouhuo());
//		} else {
//			query.set("q", "*:*");
//
//		}
		//开始条数
		query.setStart(0);
//		query.setStart(pageModel.getStart());
		//每页几条
		query.setRows(pageModel.getPageSize());
		// 开启高亮
		query.setHighlight(true);
		//query.setParam("hl", "true"); //highlighting    
		query.addHighlightField("zhiweiname");
		
		query.setHighlightSimplePre("<font color='red'>"); // 高亮单词的前缀
		
		query.setHighlightSimplePost("</font>"); // 高亮单词的后缀
		
		
		query.setSort("id", ORDER.desc); 
		// 发起请求,获取response  
		QueryResponse queryResponse = httpSolrServer.query(query);
		//根据QueryResponse 对象获取结果集
		SolrDocumentList results = queryResponse.getResults();
		//查询总条数
		long count = results.getNumFound();
		if(count!=0) {
			pageModel.setPageSum((int)count);
		}
		
		 Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		 
		 
		//新建一个list集合承载数据
		List<Zhiwei> products = new ArrayList<Zhiwei>();

		for (SolrDocument solrDocument : results) {
			Zhiwei zhiweiLittle1 =new Zhiwei();

			
			zhiweiLittle1.setId(Integer.valueOf(solrDocument.get("id").toString()));
			 
			 if (highlighting.get(solrDocument.get("id")) != null && highlighting.get(solrDocument.get("id")).get("zhiweiname") != null) {
	             //设置高亮
               String name = highlighting.get(solrDocument.get("id")).get("zhiweiname").get(0);
               
               zhiweiLittle1.setZhiweiname(name);
			 }else {
				  zhiweiLittle1.setZhiweiname(solrDocument.get("zhiweiname").toString());
			 }
		   
           zhiweiLittle1.setComid(Integer.parseInt(solrDocument.get("comid").toString()));
           zhiweiLittle1.setZhiweigreatid(Integer.parseInt(solrDocument.get("zhiweigreatid").toString()));
           zhiweiLittle1.setXinzi(solrDocument.get("xinzi").toString());
           zhiweiLittle1.setXingzhi(solrDocument.get("xingzhi").toString());
           zhiweiLittle1.setCity(solrDocument.get("city").toString());
           zhiweiLittle1.setJingyan(solrDocument.get("jingyan").toString());
           zhiweiLittle1.setXueli(solrDocument.get("xueli").toString());
           zhiweiLittle1.setYouhuo(solrDocument.get("youhuo").toString());
           products.add(zhiweiLittle1);
		} 
		pageModel.setPageList(products);
		return pageModel;
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.SolrService#deletesolr(java.lang.String)    
	 */
	@Override
	public void deletesolr(String id){
		try {
			
			httpSolrServer.deleteById("3");
			httpSolrServer.commit();
			
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.SolrService#addsolr(com.jk.model.ZhiweiLittle)    
	 */
	@Override
	public void addsolr(Zhiwei productAndCate) {
		SolrInputDocument doc =new SolrInputDocument();
		doc.addField("id", productAndCate.getId());
		doc.addField("comid", productAndCate.getComid());
		doc.addField("zhiweigreatid", productAndCate.getZhiweigreatid());
		doc.addField("zhiweiname",productAndCate.getZhiweiname());
		doc.addField("xinzi",productAndCate.getXinzi());
		doc.addField("xingzhi",productAndCate.getXingzhi());
		doc.addField("city",productAndCate.getCity());
		doc.addField("jingyan",productAndCate.getJingyan());
		doc.addField("xueli",productAndCate.getXueli());
		doc.addField("youhuo",productAndCate.getYouhuo());

       
		try {
			httpSolrServer.add(doc);
			httpSolrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
