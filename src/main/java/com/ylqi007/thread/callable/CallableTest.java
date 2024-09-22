package com.ylqi007.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description: 创建多线程的方式三: 实现Callable接口(JDK5.0新增)
 *  步骤如下:
 *      1. 创建一个实现Callable接口的实现类
 *      2. 实现call方法，将此线程需要执行的操作声明在call()中
 *      3. 创建Callable接口实现类的对象
 *      4. 将Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
 *      5. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
 *      6. 获取Callalbe中call方法的返回值
 * @Author: ylqi007
 * @Create: 3/13/24 12:20
 */
public class CallableTest {
    public static void main(String[] args) {
        // 3. 创建Callable接口实现类的对象
        NumberThread numberThread = new NumberThread();

        // 4. 将Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(numberThread);

        // 5. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("主线程: " + Thread.currentThread().getName());

        try {
            // 6. 获取Callalbe中call方法的返回值
            // get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            Object sum = futureTask.get();  // 会等到call()返回结果，当前线程(即主线程)被阻塞
            System.out.println("总和为: " + sum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}


// 1. 创建一个实现Callable的实现类
class NumberThread implements Callable {
    // 2. 实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception { // call()方法可以有返回值，可以抛出异常，可以接收范型
        int sum = 0;
        for(int i=0; i<=100; i++) {
            if(i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
            Thread.sleep(50);
        }
        return sum;
    }
}
