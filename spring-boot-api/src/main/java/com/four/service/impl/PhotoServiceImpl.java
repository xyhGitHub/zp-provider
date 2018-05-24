/** 
 * <pre>项目名称:four_group 
 * 文件名称:PhotoServiceImpl.java 
 * 包名:com.four.service.impl 
 * 创建日期:2018年3月12日下午9:10:24 
 * Copyright (c) 2018, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.four.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.four.dao.IPhotoDao;
import com.four.model.Photo;
import com.four.service.IPhotoService;
import com.four.util.FileUtil;

/** 
 * <pre>项目名称：four_group    
 * 类名称：PhotoServiceImpl    
 * 类描述：    
 * 创建人：薛怡衡  
 * 创建时间：2018年3月12日 下午9:10:24    
 * 修改人：薛怡衡    
 * 修改时间：2018年3月12日 下午9:10:24    
 * 修改备注：       
 * @version </pre>    
 */
@Service("photoService")
public class PhotoServiceImpl implements IPhotoService {
	
	@Autowired
	private IPhotoDao photoDao;

	/* (non-Javadoc)    
	 * @see com.four.service.IPhotoService#queryPhotoList()    
	 */
	@Override
	public List<Photo> queryPhotoList() throws Exception {

		List<Photo> list = photoDao.queryPhotoList();
		
		return list;
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IPhotoService#addPhoto(com.four.model.Photo)    
	 */
	@Override
	public void addPhoto(Photo photo) throws Exception {
		
		String upload = photo.getUploadtime();
		String photoname = photo.getPhotoname();
		
		String replace = photoname.replace("\"", "");
		photo.setPhotoname(replace);
		
		if(upload == ""){
			photo.setUploadtime(new Date().toString());
		}
		/*if(download == ""){
			photo.setDownloadtime();
			
		}*/
		photoDao.addPhoto(photo);
		
	}

	/* (non-Javadoc)    
	 * @see com.four.service.IPhotoService#queryPhotoLists()    
	 */
	@Override
	public List<Photo> queryPhotoLists() throws Exception {
		List<Photo> list = photoDao.queryPhotoLists();
		
		return list;
	}



}
