package com.alleyz.practice.currency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * date: 2018-01-03
 * author: alleyz
 * email: alleyz@126.com
 */
public class ReadWriteLockTest implements Runnable{
    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static Lock readLock = readWriteLock.readLock(), writeLock = readWriteLock.writeLock();

    private boolean isRead;

    public ReadWriteLockTest(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public void run() {
        if(isRead) {
            readLock.lock();
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " read op");

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                readLock.unlock();
            }
        } else {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " write op");
            try {
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                writeLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup readGroup = new ThreadGroup("readG");
        Thread[] readArr = new Thread[5];
        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(new ReadWriteLockTest(true), "read-" + i);
            readArr[i] = t1;
        }
        Thread[] writeArr = new Thread[2];
        for (int i = 0; i < 2; i++) {
            writeArr[i] = new Thread(new ReadWriteLockTest(false), "write" + i);
        }
        for (Thread t: writeArr) t.start();
        for (Thread t: readArr
             ) {
            t.start();
        }
    }
}
