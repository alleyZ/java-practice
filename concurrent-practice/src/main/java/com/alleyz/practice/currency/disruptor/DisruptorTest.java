package com.alleyz.practice.currency.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * date: 2018-01-14
 * author: alleyz
 * email: alleyz@126.com
 */
public class DisruptorTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        int bufferSize = 8;
        Disruptor<DataBean> disruptor = new Disruptor<DataBean>(new DataFactory(),
                bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(new Consumer()//);
                ,
                new Consumer(),
                new Consumer(),new Consumer(),new Consumer(),new Consumer());
        disruptor.start();

        RingBuffer<DataBean> ringBuffer = disruptor.getRingBuffer();

        Producer producer = new Producer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        try {


            for (long i = 0L; i < 10000L; i++) {
                bb.putLong(0, i);
                producer.pushData(bb);
                Thread.sleep(100);

            }
            disruptor.shutdown();
            executor.shutdown();
        }catch (Exception e){e.printStackTrace();}
    }
}
