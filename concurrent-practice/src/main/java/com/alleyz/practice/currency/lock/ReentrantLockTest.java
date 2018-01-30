package com.alleyz.practice.currency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    public static class ConditionTest implements Runnable{
        public static ReentrantLock lock = new ReentrantLock();
        public static Condition cond1 = lock.newCondition();
        public static Condition cond2 = lock.newCondition();


        private volatile boolean is = false;
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                if(!is)
                    cond1.awaitUninterruptibly(); // 可状态 释放锁
                System.out.println(Thread.currentThread().getName() + " stop await");
            }catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " exp");
            }finally {
                System.out.println(Thread.currentThread().getName() + " finally unlock");
                lock.unlock();
            }
        }

        public void lock() {
//            lock.lock();
            is = true;
            cond1.signalAll();
//            Object b = new Object();
//            b.wait();
//            b.notifyAll();
//            lock.unlock();
        }
    }


    public static void main(String[] args) {
        try {
//            ConditionTest ct = new ConditionTest();
            Thread t1 = new Thread(new ConditionTest(), "T1");
    //        Thread t2 = new Thread(new ConditionTest(), "T2");
            t1.start();
            Thread.sleep(1000);
    //        t2.start();
//            ConditionTest.cond.signal();
    //        ConditionTest.lock.lock();
    //        ConditionTest.lock.
    //        ConditionTest.cond.signal();
    //        ConditionTest.lock.unlock();
            t1.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
