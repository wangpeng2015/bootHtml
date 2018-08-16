package com.wp.myboot;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisAutoTest {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis(){
        //验证key是否存在；true存在，false不存在
        //redis.hasKey("key");
        //设置key=1
        //redis.opsForValue().set("key", "1");
        //读取key的值
        //redis.opsForValue().get("key");
        //将key的值+1 ；  如果要减1 就填入 -1
        //redis.boundValueOps("key").increment(1);
        //删除某key
        //redis.delete("key");

//        stringRedisTemplate.opsForValue().set("zzp","bigz");
//        Assert.assertEquals("bigz",stringRedisTemplate.opsForValue().get("zzp"));
    }
}
