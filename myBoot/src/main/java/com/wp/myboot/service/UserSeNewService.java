package com.wp.myboot.service;

import com.wp.myboot.entity.ReturnT;
import com.wp.myboot.entity.UserSeNew;

import java.util.Map;

/**
 *
 *
 * Created by xuxueli on '2018-08-03 13:10:22'.
 */
public interface UserSeNewService {

    /**
     * 新增
     */
    public ReturnT<String> insert(UserSeNew userSeNew);

    /**
     * 删除
     */
    public ReturnT<String> delete(int id);

    /**
     * 更新
     */
    public ReturnT<String> update(UserSeNew userSeNew);

    /**
     * Load查询
     */
    public UserSeNew load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

    /**
     * 根据用户名密码和uid查询用户信息
     * @param name
     * @param passwd
     * @param uid
     * @return
     */
    UserSeNew findUser(String name, String passwd, String uid);
}
