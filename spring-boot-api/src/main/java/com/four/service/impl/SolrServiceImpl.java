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

import com.four.model.ZhiweiLittle;
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
import com.four.util.PageModel;
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
	public PageModel<ZhiweiLittle> getSolrList(PageModel pageModel,
			 ZhiweiLittle productAndCate) throws Exception {

		System.out.println(productAndCate);
		System.out.println(pageModel);

		//获取query 对象
		SolrQuery query = new SolrQuery();
		
		//设置查询关键字
		if (!StringUtils.isEmpty(productAndCate.getName())) {
			query.set("q", "name:" + productAndCate.getName());
		} else {
			query.set("q", "*:*");
			
		}
		//开始条数
		query.setStart(pageModel.getStart());
		//每页几条
		query.setRows(pageModel.getPageSize());
		// 开启高亮
		query.setHighlight(true);
		//query.setParam("hl", "true"); //highlighting    
		query.addHighlightField("name");
		
		query.setHighlightSimplePre("<font color='red'>"); // 高亮单词的前缀
		
		query.setHighlightSimplePost("</font>"); // 高亮单词的后缀
		
		
		query.setSort("id", ORDER.desc); 
		// 发起请求,获取response  
		QueryResponse queryResponse = httpSolrServer.query(query);
		//根据QueryResponse 对象获取结果集
		SolrDocumentList results = queryResponse.getResults();
		//查询总条数
		long count = results.getNumFound();
		if(!StringUtils.isEmpty(count))
		{
		pageModel.setPageSum((int)count);
		}
		
		 Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		 
		 
		//新建一个list集合承载数据
		List<ZhiweiLittle> products = new ArrayList<ZhiweiLittle>();
		for (SolrDocument solrDocument : results) {
			
			ZhiweiLittle zhiweiLittle1 =new ZhiweiLittle();
			
			zhiweiLittle1.setId(Integer.valueOf(solrDocument.get("id").toString()));
			 
			 if (highlighting.get(solrDocument.get("id")) != null && highlighting.get(solrDocument.get("id")).get("name") != null) {
	             //设置高亮
               String name = highlighting.get(solrDocument.get("id")).get("name").get(0);
               
               zhiweiLittle1.setName(name);		
			 }else
			 {
				  zhiweiLittle1.setName(solrDocument.get("name").toString());	 
			 }
		   
           zhiweiLittle1.setGreatid((Integer)solrDocument.get("greateid"));	
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
	public void addsolr(ZhiweiLittle productAndCate) {
		SolrInputDocument doc =new SolrInputDocument();
		doc.addField("id", productAndCate.getId());
		doc.addField("greatid", productAndCate.getGreatid());
		doc.addField("name",productAndCate.getName() );

       
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
