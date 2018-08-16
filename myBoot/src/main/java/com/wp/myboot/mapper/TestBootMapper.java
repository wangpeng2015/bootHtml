package com.wp.myboot.mapper;


import com.boot.commons.entity.CommonMapper;
import com.wp.myboot.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestBootMapper extends CommonMapper<Log,Integer> {

    List<TestUser> selectALL();
}
