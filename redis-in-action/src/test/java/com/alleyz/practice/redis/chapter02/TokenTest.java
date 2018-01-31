package com.alleyz.practice.redis.chapter02;

import com.alleyz.practice.redis.chapter02.mock.IRedisMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 2018-01-31
 * email: alleyz@126.com
 *
 * @author alleyz
 */
public class TokenTest {

    private List<String> users;
    private List<String> items;
    private JedisPool jedisPool;
    private Token token;
    private CleanSessionThread cleanSessionThread;
    private IRedis tokenRedis, clearRedis;
    private Jedis jedis;
    @Before
    public void init() {
        this.jedisPool = new JedisPool("localhost");
        this.tokenRedis = new JedisImpl(this.jedisPool.getResource());
        this.jedis = jedisPool.getResource();
        int cap = 10;
        this.users = new ArrayList<>(cap);
        this.items = new ArrayList<>(cap);

        for (int i = 0; i <cap; i++) {
            this.users.add("user-" + i);
            this.items.add("item-" + i);
        }
        this.token = new Token(this.tokenRedis);
        this.clearRedis = new JedisImpl(this.jedisPool.getResource());
        this.cleanSessionThread = new CleanSessionThread(this.clearRedis, 5);
    }


    @Test
    public void testUpdateToken() {
        this.users.forEach(s -> {
            token.updateToken("token-" + s, s, items.get(0));
        });
        Assert.assertEquals((long)this.jedis.hlen(Token.LOGIN), 10L);
        this.cleanSessionThread.start();
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals(5L, (long)this.jedis.hlen(Token.LOGIN));
        this.cleanSessionThread.exit();
    }

    @Test
    public void testCheckToken() {
        Assert.assertNull(this.token.checkToken("token-user-1"));
        Assert.assertEquals(this.token.checkToken("token-user-7"), "user-7");
    }

    @After
    public void clear() {
        this.tokenRedis.close();
        this.clearRedis.close();
        this.jedis.close();
        this.jedisPool.close();
    }


}
