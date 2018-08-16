package com.wp.myboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.mapper.Mp4Mapper;
import com.wp.myboot.service.Mp4Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Mp4ServiceImpl implements Mp4Service {

    private final Logger log = LoggerFactory.getLogger(Mp4ServiceImpl.class);

    @Autowired
    private Mp4Mapper mp4Mapper;

    @Override
    public SpringResult getMp4_category() {
        SpringResult springResult = new SpringResult();
        List<Map<String, String>> res=mp4Mapper.getMp4_category();
        if(res!=null && res.size()>0){
            springResult.setResult(res);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public SpringResult findMp4_data(String type, Integer pageStart, Integer pageSize) {
        SpringResult springResult = new SpringResult();
        JSONObject object=new JSONObject();
        List<Map<String, String>> res=mp4Mapper.findMp4_data(type,pageStart,pageSize);
        object.put("list", res);
        /*总条数*/
        int totalCount=mp4Mapper.getTotleCount(type);
        object.put("totalCount", totalCount);
        if(object!=null){
            springResult.setResult(object);
            springResult.setResultCode("200");
        }else {
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public SpringResult findMp4Novel() {
        SpringResult springResult = new SpringResult();
        List<Map<String, String>> res=mp4Mapper.findMp4Novel();
        if(res!=null && res.size()>0){
            springResult.setResult(res);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }

        return springResult;
    }

    /**
     * 获取app的版本和version
     * @return
     */
    @Override
    public SpringResult getAppVersionAndUrl() {
        SpringResult springResult = new SpringResult();
        Map<String, String> map= mp4Mapper.getAppVersionAndUrl();
        if(map!=null && map.size()>0){
            springResult.setResult(map);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }

        return springResult;
    }

    /**
     * 获取图片轮播连接
     * @return
     */
    @Override
    public SpringResult getImgLink() {
        SpringResult springResult = new SpringResult();
        List<Map<String, String>> res=mp4Mapper.getImgLink();
        if(res!=null && res.size()>0){
            springResult.setResult(res);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public SpringResult saveFileNameMul(String randStr,String fileType, String fileContentType, String fileName) {
        SpringResult springResult = new SpringResult();
        try {
            Integer res=mp4Mapper.saveFileNameMul(randStr,fileType,fileContentType,fileName);
            springResult.setResult(res);
            springResult.setResultCode("200");
            springResult.setMessage("文件上传成功");
        }catch (Exception e){
            springResult.setResult(null);
            springResult.setResultCode("300");
            springResult.setMessage("文件上传失败");
            log.error("保存文件错误"+e.toString());
        }
        return springResult;
    }
}
