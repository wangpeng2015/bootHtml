package com.wp.myboot.mapper;

import com.boot.commons.entity.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface Mp4Mapper extends CommonMapper<Log,Integer> {


    List<Map<String,String>> getMp4_category();

    List<Map<String,String>> findMp4_data(@Param("type")String type,@Param("pageStart")Integer pageStart,@Param("pageSize")Integer pageSize);

    List<Map<String,String>> findMp4Novel();

    Map<String,String> getAppVersionAndUrl();

    int getTotleCount(@Param("type")String type);

    List<Map<String,String>> getImgLink();

    Integer saveFileNameMul(@Param("randStr")String randStr, @Param("fileType")String fileType,@Param("fileContentType")String fileContentType,@Param("fileName")String fileName);
}
