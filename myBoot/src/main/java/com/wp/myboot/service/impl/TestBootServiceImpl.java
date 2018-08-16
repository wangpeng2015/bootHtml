package com.wp.myboot.service.impl;


import com.wp.myboot.entity.TestUser;
import com.wp.myboot.mapper.WReUserMapper;
import com.wp.myboot.service.TestBootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestBootServiceImpl implements TestBootService {

    @Autowired
    public WReUserMapper wReUserMapper;


    @Override
    public List<TestUser> selectALL() {
        return wReUserMapper.selectALL();
    }


    @Transactional(propagation = Propagation.REQUIRED)//开启事务:没有则创建  有则不创建
    @Override
    public Integer createUser(TestUser user) {
        return wReUserMapper.createUser(user);
    }
}
