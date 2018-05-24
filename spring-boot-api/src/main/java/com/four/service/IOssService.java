package com.four.service;

import com.four.model.Oss;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by Administrator on 2018/5/17.
 */
public interface IOssService {
    String saveFile(MultipartFile file) throws Exception;
}
