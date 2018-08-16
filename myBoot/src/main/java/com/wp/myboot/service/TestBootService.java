package com.wp.myboot.service;

import com.wp.myboot.entity.TestUser;

import java.util.List;

public interface TestBootService {

    public List<TestUser> selectALL();

    public Integer createUser(TestUser user);
}
