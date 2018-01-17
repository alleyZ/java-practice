package com.alleyz.practice.currency.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * date: 2018-01-14
 * author: alleyz
 * email: alleyz@126.com
 */
public class Producer {
    private final RingBuffer<DataBean> buffer;

    Producer(RingBuffer<DataBean> buffer) {
        this.buffer = buffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long seq = buffer.next();

        DataBean db = buffer.get(seq);
        db.setName("ab" + byteBuffer.getLong(0));
        db.setValue(byteBuffer.getLong(0));

        buffer.publish(seq);
    }

}
