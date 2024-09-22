package com.ylqi007.thread.deadlock;

/**
 * Description:
 *  1. 使用继承Thread类的方式，实现卖票
 *  2. 没有机制确保线程安全，会出现错票和重复票的问题
 *
 * @Author: ylqi007
 * @Create: 3/12/24 00:40
 */
public class DeadLockTest2 implements Runnable {
    A a = new A();
    B b = new B();

    public void init() {
        Thread.currentThread().setName("主线程");
        // 调用a对象的foo()方法
        a.foo(b);
        System.out.println("进入主线程之后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        // 调用b对象的bar方法
        b.bar(a);
        System.out.println("进入副线程之后");
    }

    public static void main(String[] args) {
        DeadLockTest2 deadLock = new DeadLockTest2();
        new Thread(deadLock).start();   // 分线程调用deadLock.run()
        deadLock.init();    // 主线程调用deadLock.init()
    }
}

class A {
    public synchronized void foo(B b) {
        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 进入A实例的foo()");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 企图调用实例B的last()");
        b.last();
    }

    public synchronized void last() {
        System.out.println("进入实例A的last()方法内部");
    }
}

class B {
    public synchronized void bar(A a) {
        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 进入B实例的foo()");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 企图调用实例A的last()");
        a.last();
    }

    public synchronized void last() {
        System.out.println("进入实例B的last()方法内部");
    }
}
