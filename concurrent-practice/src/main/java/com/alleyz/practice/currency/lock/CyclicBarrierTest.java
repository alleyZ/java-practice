package com.alleyz.practice.currency.lock;

import java.util.concurrent.CyclicBarrier;

/**
 * date: 2018-01-04
 * author: alleyz
 * email: alleyz@126.com
 */
public class CyclicBarrierTest{


    public static class Soldier implements Runnable {
        private CyclicBarrier barrier;
        public Soldier(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("I`m solider-" + Thread.currentThread().getName());
            try {
                barrier.await();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " solider done");
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " task done");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10, new Task());
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Soldier(barrier), "solider-" + i);
        }
        for (Thread t :
                threads) {
            t.start();
        }
    }

}
