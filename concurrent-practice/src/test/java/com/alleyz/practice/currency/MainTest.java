package com.alleyz.practice.currency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * date: 2018-01-17
 * author: alleyz
 * email: alleyz@126.com
 */

public class MainTest {
    private Main main;

    @Before
    public void main() {
        main = new Main();
    }

    @Test
    public void testGet() {
        Assert.assertEquals("1", main.get());
    }

//    @Test
//    public void testSay() {
////        main.sayHello();
//        Assert.assertTrue(true);
//    }
}
