package com.ylqi007.thread.threadsafemore;

/**
 * Description: 演示单例模式中存在的线程安全问题
 *  1. 饿汉式在类初始化的时候就直接创建单例对象，而类初始化过程是没有线程安全问题的。
 *  2. 懒汉式线程安全问题: 延迟创建对象，第一次调用getInstance()方法再创建对象。
 *
 * @Author: ylqi007
 * @Create: 3/11/24 11:33
 */
public class BankTest {
    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread(() -> b2 = Bank.getInstance());
        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);
    }
}


class Bank {
    private Bank() {}

    // 从JDK2.0开始，分配空间、初始化、调用构造器会在线程的工作存储区一次性完成，然后复制到主存储区。但是需要volatile关键字，避免指令重排
    private static volatile Bank instance = null;    // 共享数据是instance

    // 实现线程安全的方式1:
    //public static synchronized Bank getInstance() { // 静态方法，同步监视器默认为类本身，Bank.class
    //    if(instance == null) {
    //        try {
    //            Thread.sleep(100);
    //        } catch (InterruptedException e) {
    //            throw new RuntimeException(e);
    //        }
    //        instance = new Bank();
    //    }
    //    return instance;
    //}

    // 实现线程安全的方式2:
    //public static Bank getInstance() { // 静态方法，同步监视器默认为类本身，Bank.class
    //    synchronized (Bank.class) {
    //        if(instance == null) {
    //            try {
    //                Thread.sleep(100);
    //            } catch (InterruptedException e) {
    //                throw new RuntimeException(e);
    //            }
    //            instance = new Bank();
    //        }
    //        // return instance; // 可以放在外面，因为没有修改
    //    }
    //    return instance;
    //}

    // 实现线程安全的方式3: 提前判断instance是否为null，不为null则直接跳过synchronized部分，比方式1，2效率更高
    // 依旧存在的问题: 指令重排，因为intance=new Bank();是需要一系列步骤完成的。
    // 为了避免指令重排，要将instance声明为volatile
    /*
    注意: 方式3中有指令重排问题。
    mem = allocate();   为单例对象分配内存空间。
    instance = mem;     instance引用现在非空、但还未初始化
    ctorSingleton(instance); 为单例对象通过instance调用构造器
    从JDK2开始，分配空间、初始化、调用构造器会在线程的工作区存储一次性完成，然后复制到主存储区，但是需要volatile关键字
     */
    public static Bank getInstance() { // 静态方法，同步监视器默认为类本身，Bank.class
        if(instance == null) {
            synchronized (Bank.class) {
                if(instance == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    instance = new Bank();
                }
                // return instance; // 可以放在外面，因为没有修改
            }
        }
        return instance;
    }
}