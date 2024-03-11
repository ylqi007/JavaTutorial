package com.atguigu.thread.runnable;

/**
 * Description: 通过实现Runnable接口，创建线程对象
 *
 * @Author: ylqi007
 * @Create: 3/10/24 11:04
 */
public class EvenNumberTest {
    public static void main(String[] args) {
        // 1. 创建实现了Runnable接口的对象
        EvenNumber evenNumber = new EvenNumber();
        Thread thread = new Thread(evenNumber); // 多态
        thread.start();

        // 2. 再创建一个线程
        Thread thread1 = new Thread(evenNumber);    // 此时对象evenNumber可以共用
        thread1.start();

        // 方式3: 使用实现Runnable接口的方式，提供了Runnable接口匿名实现类的匿名对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100; i++) {
                    if(i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " === " + i);
                    }
                }
            }
        }).start();

        // 使用Lambda function替代匿名类
        new Thread(() -> {
            for(int i=0; i<100; i++) {
                if(i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + " === " + i);
                }
            }
        }).start();

        // main() 方法对应的主线程
        for(int i=0; i<100; i++) {
            if(i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " *** " + i);
            }
        }
    }
}


// 实现Runnable接口，Runnable接口是函数式接口，@FunctionalInterface
class EvenNumber implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<100; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + " *** " + i);
            }
        }
    }
}
