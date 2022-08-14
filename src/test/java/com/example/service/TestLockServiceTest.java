package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.DemoApplication;
import com.example.demo.entity.TestLockEntity;
import com.example.demo.service.TestLockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: luozijian
 * @date: 2022/4/17
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestLockServiceTest {


    @Autowired
    private TestLockService testLockService;

    @Test
    public void test(){
//        TestLockEntity testLockEntity = testLockService.getBaseMapper().selectOne(new QueryWrapper<TestLockEntity>().eq("a", "0").last("limit 10"));
//        TestLockEntity testLockEntity1 = testLockService.getOne(new QueryWrapper<TestLockEntity>().eq("id", 4));
//        TestLockEntity testLockEntity = testLockService.getOne(new QueryWrapper<TestLockEntity>().eq("a", "0"));

//        TestLockEntity testLockEntity = testLockService.getOne(Wrappers.lambdaQuery(TestLockEntity.class).eq(TestLockEntity::getA, "0").last("limit 10"));
        TestLockEntity testLockEntity = testLockService.lambdaQuery().eq(TestLockEntity::getA, "0").last("limit 10").one();
        System.out.println(testLockEntity);
    }
}