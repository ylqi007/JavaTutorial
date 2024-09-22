package com.ylqi007.thread;

/**
 * Description: 通过继承Thread类，创建一个Thread的子类，然后创建线程。
 *
 * @Author: ylqi007
 * @Create: 3/10/24 10:55
 */
public class PrintNumberTest {
    public static void main(String[] args) {
        // 方式一：子类
        //EvenNumber evenNumber = new EvenNumber();
        //OddNumber oddNumber = new OddNumber();
        //evenNumber.start();
        //oddNumber.start();

        // 方式二: 匿名子类
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i=0; i<100; i++) {
                    if(i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " *** " + i);
                    }
                }
            }
        };
        t1.start();

        // 使用Lambda的方式创建Thread类
        new Thread(() -> {
           for(int i=0; i<100; i++) {
               if(i % 2 != 0) {
                   System.out.println(Thread.currentThread().getName() + " --> " + i);
               }
           }
        }).start();
    }
}


// EventNumber是继承Thread的子类
class EvenNumber extends Thread {
    @Override
    public void run() {
        for(int i=0; i<100; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "---> " + i);
            }
        }
    }
}


// OddNumber是继承Thread的子类
class OddNumber extends Thread {
    @Override
    public void run() {
        for(int i=0; i<100; i++) {
            if(i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
