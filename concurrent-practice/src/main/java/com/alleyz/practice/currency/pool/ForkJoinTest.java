package com.alleyz.practice.currency.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * date: 2018-01-04
 * author: alleyz
 * email: alleyz@126.com
 */
public class ForkJoinTest extends RecursiveTask<Long>{

    private static final int THRESHOLD = 1000;

    private long start, end;

    public ForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCom = (end - start) < THRESHOLD;
        if(canCom) {
            for (long i = start; i <=end; i ++) {
                sum += i;
            }
        }else{
            long step = (end - start) / THRESHOLD + 1;
            List<ForkJoinTest> taskList = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < step; i++) {
                long lastOne = pos + step;
                if(pos > end) break;
                if(lastOne > end) {
                    lastOne = end;
                }


                ForkJoinTest subTask = new ForkJoinTest(pos, lastOne);
                pos += step + 1;
                taskList.add(subTask);
                subTask.fork();
            }
            for (ForkJoinTest task :
                    taskList) {
                sum += task.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> reslut = pool.submit(new ForkJoinTest(12L, 20000000L));
        try {
            long sum = reslut.get();
            System.out.println("sum=" + sum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
