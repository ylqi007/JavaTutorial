package com.atguigu.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description: 创建并使用多线程的第四种方法：使用线程池
 *
 * @Author: ylqi007
 * @Create: 3/13/24 12:53
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 1. 提供指定线程数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;

        // 设置线程池的属性
        System.out.println(threadPoolExecutor.getClass());
        threadPoolExecutor.setMaximumPoolSize(50);  // 设置线程池中线程数的上限

        // 2. 执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        threadPoolExecutor.execute(new NumberThread1());
        threadPoolExecutor.execute(new NumberThread2());

        // threadPoolExecutor.submit(Callable<T> task); // 适合使用于Callable

        // 3. 关闭链接池
        threadPoolExecutor.shutdown();
    }
}


// NumberThread1 prints even number
// pool-1-thread-1: 0
class NumberThread1 implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<=100; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}


// NumberThread2 prints odd number
// pool-1-thread-2: 1
class NumberThread2 implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<=100; i++) {
            if(i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}