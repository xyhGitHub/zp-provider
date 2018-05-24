package com.four.service.impl;

import com.four.dao.IOssDao;
import com.four.model.Oss;
import com.four.service.IOssService;
import com.four.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Administrator on 2018/5/17.
 */
@Service("ossService")
public class OssServiceImpl implements IOssService{

    @Autowired
    private IOssDao ossDao;

    @Autowired
    private OssUtil ossClient;

    @Override
    public String saveFile(MultipartFile  file) throws Exception {
        if (file == null) {
            throw new Exception("图片不能为空");
        }
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        Oss oss = new Oss();
        oss.setPhoto(imgUrl);
        ossDao.saveFile(oss);//只是本地上传使用的
        return imgUrl;
    }
}
