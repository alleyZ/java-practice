package com.alleyz.practice.currency.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * date: 2018-01-04
 * author: alleyz
 * email: alleyz@126.com
 */
public class StampedLockTest{

    private double x,y;

    private final StampedLock lock = new StampedLock();

    void move(double x, double y) {
        long stamp = lock.writeLock(); // 获取读锁，阻塞 读写线程
        this.x = x;
        this.y = y;
        lock.unlock(stamp); // 释放读锁
    }

    double distance() {
        long stamp = lock.tryOptimisticRead(); // 乐观读

        double rX = x, rY = y;

        if(!lock.validate(stamp)){ // 判断乐观读的时候是否发生了写
            stamp = lock.readLock(); // 读锁，悲观读
            rX = x;
            rY = y;
            lock.unlock(stamp);
        }

        return rX + rY;
    }

    public static void main(String[] args) {

        StampedLockTest lockTest = new StampedLockTest();
        Thread[] reads = new Thread[8];
        Thread[] writes = new Thread[2];

        for (int i = 0; i < 8; i++) {
            reads[i] = new Thread(()-> {
                try{
//
                    System.out.println(lockTest.distance());
                    Thread.sleep(2);
                }catch (Exception e){
                }
                System.out.println(lockTest.distance());
            });
        }
        for (int i = 0; i < 2; i++) {
            int i1 = i + 10;
            writes[i] = new Thread(() -> {
                try{
//                    Thread.sleep(2);
                    lockTest.move(i1 / 1.0, i1 / 1.0);
                }catch (Exception e) {}
            });
        }

        for (Thread t :
                reads) {
            t.start();
        }
        for (Thread t:
             writes) {
            t.start();
        }
    }

}
