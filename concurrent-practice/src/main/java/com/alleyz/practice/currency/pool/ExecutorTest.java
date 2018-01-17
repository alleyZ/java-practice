package com.alleyz.practice.currency.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * date: 2018-01-04
 * author: alleyz
 * email: alleyz@126.com
 */
public class ExecutorTest {
//    ExecutorTest

    public static class Task implements Runnable {
        @Override
        public void run() {
//            System.out.println(Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis());
            try{Thread.sleep(5000);}catch (Exception e){}
            System.out.println("end");
        }
    }

    public static void main(String[] args) {
        Task task = new Task();
//        ExecutorService service = Executors.newCachedThreadPool();
//        for (int i = 0; i < 40; i++) {
//            service.submit(task);
//        }

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
    }
}
