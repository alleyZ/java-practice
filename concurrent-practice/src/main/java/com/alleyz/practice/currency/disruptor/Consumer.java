package com.alleyz.practice.currency.disruptor;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicLong;

/**
 * date: 2018-01-14
 * author: alleyz
 * email: alleyz@126.com
 */
public class Consumer implements WorkHandler<DataBean> {
    private static AtomicLong counter = new AtomicLong(0);
    @Override
    public void onEvent(DataBean event) throws Exception {
        long c = counter.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + "消费：" + event.toString() + "--" + c);
    }
}
