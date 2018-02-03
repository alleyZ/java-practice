package com.alleyz.practice.redis.chapter02;

import java.util.Set;

/**
 * date: 2018-01-31
 * email: alleyz@126.com
 *
 * @author alleyz
 */
public interface IRedis {

    String hget(String key, String field);
    Long hset(String key, String field, String val);
    Long zadd(String key, double score, String member);
    Long zremrangeByRank(String key, long start, long end);
    Set<String> zrange(String key, long start, long end);
    Long del(String ... keys);
    Long hdel(String key, String ... fields);
    Long zrem(String key, String ... members);
    Long zcard(String key);
    void close();
}
