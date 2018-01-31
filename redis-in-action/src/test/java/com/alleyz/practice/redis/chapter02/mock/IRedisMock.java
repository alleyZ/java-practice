package com.alleyz.practice.redis.chapter02.mock;

import com.alleyz.practice.redis.chapter02.IRedis;

import java.util.Set;

/**
 * date: 2018-01-31
 * email: alleyz@126.com
 *
 * @author alleyz
 */
public class IRedisMock implements IRedis {
    @Override
    public String hget(String key, String field) {
        return null;
    }

    @Override
    public Long hset(String key, String field, String val) {
        return null;
    }

    @Override
    public Long zadd(String key, double score, String member) {
        return null;
    }

    @Override
    public Long zremrangeByRank(String key, long start, long end) {
        return null;
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long del(String... keys) {
        return null;
    }

    @Override
    public Long hdel(String key, String... fields) {
        return null;
    }

    @Override
    public Long zrem(String key, String... members) {
        return null;
    }

    @Override
    public Long zcard(String key) {
        return null;
    }

    @Override
    public void close() {

    }
}
