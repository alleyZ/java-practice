package com.alleyz.practice.redis.chapter02;

import redis.clients.jedis.Jedis;

/**
 * 使用redis来管理session
 *
 * date: 2018-01-31
 * @author alleyz
 */
public class Token {

    /**
     * redis key 常量，用于记录登录令牌的集合
     */
    public final static String LOGIN = "login:";
    /**
     * redis key 常量， 用于记录最近活跃的token
     */
    public final static String RECENT = "recent:";
    /**
     * redis key 常量，用于记录用户浏览项
     */
    public final static String VIEWED = "viewed:";

    /**
     * redis 客户端
     */
    private final IRedis jedis;

    public Token(IRedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 通过token获取用户
     * @param token token
     * @return 用户或null
     */
    String checkToken(String token) {
        return this.jedis.hget(LOGIN, token);
    }

    /**
     * 更新token信息
     * @param token token
     * @param user 用户
     * @param item 浏览项
     */
    void updateToken(String token, String user, String item) {
        long time = System.currentTimeMillis() / 1000;
        this.jedis.hset(LOGIN, token, user);
        this.jedis.zadd(RECENT, time, token);
        if(item != null) {
            this.jedis.zadd(VIEWED + token, time, item);
            // 保留最近的25个
            this.jedis.zremrangeByRank(VIEWED + token, 0, -26);
//            this.jedis.zincrby(VIEWED, -1, item);
        }
    }



}
