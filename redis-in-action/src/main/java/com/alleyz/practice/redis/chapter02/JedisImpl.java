package com.alleyz.practice.redis.chapter02;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * date: 2018-01-31
 * email: alleyz@126.com
 *
 * @author alleyz
 */
public class JedisImpl implements IRedis {

    private final Jedis jedis;

    public JedisImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public String hget(String key, String field) {
        return jedis.hget(key, field);
    }

    @Override
    public Long hset(String key, String field, String val) {
        return jedis.hset(key, field, val);
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return jedis.zadd(key, score, member);
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return jedis.zremrangeByRank(key, start, end);
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return jedis.zrange(key, start, end);
    }

    @Override
    public Long del(String... keys) {
        return jedis.del(keys);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return jedis.hdel(key, fields);
    }

    @Override
    public Long zrem(String key, String... members) {
        return jedis.zrem(key, members);
    }

    @Override
    public Long zcard(String key) {
        return jedis.zcard(key);
    }

    @Override
    public void close() {
        this.jedis.close();
    }
}
