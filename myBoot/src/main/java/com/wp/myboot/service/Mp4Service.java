package com.wp.myboot.service;

import com.wp.myboot.controller.result.SpringResult;

import java.util.List;
import java.util.Map;

public interface Mp4Service {

    SpringResult getMp4_category();

    SpringResult findMp4_data(String type, Integer pageStart, Integer pageSize);

    SpringResult findMp4Novel();

    /**
     * 获取app的版本和version
     * @return
     */
    SpringResult getAppVersionAndUrl();

    /**
     * 获取图片轮播连接
     * @return
     */
    SpringResult getImgLink();

    SpringResult saveFileNameMul(String randStr,String fileType, String fileContentType, String fileName);

}
