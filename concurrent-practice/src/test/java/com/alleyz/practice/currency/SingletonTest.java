package com.alleyz.practice.currency;

import com.alleyz.practice.currency.lock.CyclicBarrierTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * date: 2018-01-17
 * author: alleyz
 * email: alleyz@126.com
 */
public class SingletonTest {

//    private CyclicBarrierTest cyclicBarrierTest;

//    @Before
//    public void abd() {
//        cyclicBarrierTest = new CyclicBarrierTest();
//    }

    @Test
    public void testGetInstance() {
        Assert.assertEquals(Singleton.getInstance(), Singleton.getInstance());
        // 断言
        // 输入 -> 断言(判断) -> 输出  ： 推断 我的输入 得到结果(程序输出) 是否和我预期的一样
        // 正确的输入、正确输出    错误输入 错误的输出
        // 被测试方法中有流程控制(if else   switch  while do for) 必须构造能执行到流程控制语句每一行的条件
//        cyclicBarrierTest.hashCode();
        // if ("1".eq(a)){  // }
        // else if ("2".eq(a)) {}
        // else if ("3".eq(a)) {}
        // else {}
//        Assert.assertEquals(Singleton.getInstance("1"), "6"); // 没有问题
//        Assert.assertEquals(Singleton.getInstance("2"), "6"); // 没有问题
//        Assert.assertEquals(Singleton.getInstance("3"), "6"); // 没有问题
//        Assert.assertEquals(Singleton.getInstance("4"), "6"); // 没有问题

    }

//    @After
//    public void clear() {
//
//    }

}
