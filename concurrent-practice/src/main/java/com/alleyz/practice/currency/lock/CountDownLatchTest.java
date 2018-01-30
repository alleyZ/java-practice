package com.alleyz.practice.currency.lock;

import java.util.concurrent.CountDownLatch;

/**
 * date: 2018-01-04
 * author: alleyz
 * email: alleyz@126.com
 */
public class CountDownLatchTest implements Runnable {

    public static CountDownLatch latch = new CountDownLatch(10);
    public CountDownLatchTest(int count){
//        latch = new CountDownLatch(10);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " done ");
        latch.countDown();
        try{
            Thread.sleep(2000);
        }catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName() + " oth");
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new CountDownLatchTest(10), "COUNT-" + i);
        }

        new Thread(()->{
            try {
                CountDownLatchTest.latch.await();
            }catch (Exception e){}
            System.out.println(" mine start ");
        }, "ccc").start();

        for (Thread t: threads
             ) {
            t.start();
        }
    }
}
