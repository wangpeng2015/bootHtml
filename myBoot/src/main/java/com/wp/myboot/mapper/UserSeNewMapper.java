package com.wp.myboot.mapper;

import com.boot.commons.entity.CommonMapper;
import com.wp.myboot.entity.UserSeNew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 * Created by xuxueli on '2018-08-03 13:10:22'.
 */
@Mapper
public interface UserSeNewMapper extends CommonMapper<Log,Integer> {

    /**
     * 新增
     */
    public void insert(@Param("userSeNew") UserSeNew userSeNew);

    /**
     * 删除
     */
    public int delete(@Param("id") int id);

    /**
     * 更新
     */
    public int update(@Param("userSeNew") UserSeNew userSeNew);

    /**
     * Load查询
     */
    public UserSeNew load(@Param("id") int id);

    /**
     * 分页查询Data
     */
    public List<UserSeNew> pageList(@Param("offset") int offset,
                                    @Param("pagesize") int pagesize);

    /**
     * 分页查询Count
     */
    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize);

}
