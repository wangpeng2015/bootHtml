package com.wp.myboot.mapper;


import com.boot.commons.entity.CommonMapper;
import com.wp.myboot.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WReUserMapper extends CommonMapper<Log,Integer> {
    public List<TestUser> selectALL();
    public Integer createUser(@Param("user") TestUser user);
}
