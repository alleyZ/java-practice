package com.alleyz.practice.currency;

import org.junit.Assert;
import org.junit.Test;

/**
 * date: 2018-01-17
 * author: alleyz
 * email: alleyz@126.com
 */
public class SingletonTest {

    @Test
    public void testGetInstance() {
        Assert.assertEquals(Singleton.getInstance(), Singleton.getInstance());
    }
}
