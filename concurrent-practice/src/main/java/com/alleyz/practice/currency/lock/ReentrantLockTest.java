package com.alleyz.practice.currency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {


    public static class ConditionTest implements Runnable{
        public static ReentrantLock lock = new ReentrantLock();
        public static Condition cond = lock.newCondition();


        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                cond.awaitUninterruptibly();
                System.out.println(Thread.currentThread().getName() + " stop await");
            }catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " exp");
            }finally {
                System.out.println(Thread.currentThread().getName() + " finally unlock");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new ConditionTest(), "T1");
    //        Thread t2 = new Thread(new ConditionTest(), "T2");
            t1.start();
            Thread.sleep(1000);
    //        t2.start();
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
