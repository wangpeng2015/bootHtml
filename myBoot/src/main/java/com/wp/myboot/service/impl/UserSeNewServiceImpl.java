package com.wp.myboot.service.impl;

import com.wp.myboot.entity.ReturnT;
import com.wp.myboot.entity.UserSeNew;
import com.wp.myboot.mapper.UserSeNewMapper;
import com.wp.myboot.service.UserSeNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 *
 * Created by xuxueli on '2018-08-03 13:10:22'.
 */
@Service
public class UserSeNewServiceImpl implements UserSeNewService {

    @Autowired
    private UserSeNewMapper userSeNewMapper;

    /**
     * 新增
     */
    @Override
    public ReturnT<String> insert(UserSeNew userSeNew) {

        // valid
        if (userSeNew == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

        userSeNewMapper.insert(userSeNew);
        return ReturnT.SUCCESS;
    }

    /**
     * 删除
     */
    @Override
    public ReturnT<String> delete(int id) {
        int ret = userSeNewMapper.delete(id);
        return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

    /**
     * 更新
     */
    @Override
    public ReturnT<String> update(UserSeNew userSeNew) {
        int ret = userSeNewMapper.update(userSeNew);
        return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
    }

    /**
     * Load查询
     */
    @Override
    public UserSeNew load(int id) {
        return userSeNewMapper.load(id);
    }

    /**
     * 分页查询
     */
    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        Map<String,Object> maps=new HashMap<String,Object>();

        List<UserSeNew> pageList = userSeNewMapper.pageList(offset, pagesize);
        int totalCount = userSeNewMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        maps.put("pageList", pageList);
        maps.put("totalCount", totalCount);

        return result;
    }


    /**
     * 根据用户名密码和uid查询用户信息
     * @param name
     * @param passwd
     * @param uid
     * @return
     */
    @Override
    public UserSeNew findUser(String name, String passwd, String uid) {
        return null;
    }

}
