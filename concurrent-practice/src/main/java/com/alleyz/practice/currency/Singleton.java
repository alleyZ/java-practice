package com.alleyz.practice.currency;

/**
 * date: 2018-01-13
 * author: alleyz
 * email: alleyz@126.com
 */
public class Singleton {

    private Singleton(){
        System.out.println("2");
    }
    private static class SingletonHolder{
        private static Singleton instance = new Singleton();
    }

    public static String A = "`";

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }


}
