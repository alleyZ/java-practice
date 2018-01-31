package com.alleyz.practice.redis.chapter02;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * date: 2018-01-31
 * email: alleyz@126.com
 *
 * @author alleyz
 */
public class CleanSessionThread extends Thread {
    private final IRedis jedis;
    private long limit;
    private boolean quit;

    public CleanSessionThread(IRedis jedis, long limit) {
        this.jedis = jedis;
        this.limit = limit;
        this.quit = false;
    }

    @Override
    public void run() {
        try {
            while (!quit) {
                long size = this.jedis.zcard(Token.RECENT);
                if (size <= this.limit) {
                    Thread.sleep(1000);
                    continue;
                }

                long end = Math.min(size - this.limit, 100);

                Set<String> clearTokens = this.jedis.zrange(Token.RECENT, 0, end - 1);
                List<String> viewTokens = new ArrayList<>(clearTokens.size());
                clearTokens.forEach(s -> viewTokens.add(Token.VIEWED + s));

                this.jedis.del(viewTokens.toArray(new String[viewTokens.size()]));
                String[] cts = clearTokens.toArray(new String[clearTokens.size()]);
                this.jedis.hdel(Token.LOGIN, cts);
                this.jedis.zrem(Token.RECENT, cts);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exit() {
        this.quit = true;
    }
}
