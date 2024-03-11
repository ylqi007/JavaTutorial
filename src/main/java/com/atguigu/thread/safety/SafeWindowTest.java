package com.atguigu.thread.safety;

/**
 * Description:
 *  1. 实现Runnable接口,创建线程对象
 *  2. 使用同步代码块: 代码块 + 锁，锁必须是唯一的
 *
 * @Author: ylqi007
 * @Create: 3/10/24 12:17
 */
public class SafeWindowTest {
    public static void main(String[] args) {
        SaleTicket1 saleTicket = new SaleTicket1();

        Thread t1 = new Thread(saleTicket);
        Thread t2 = new Thread(saleTicket);
        Thread t3 = new Thread(saleTicket);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}


class SaleTicket1 implements Runnable {
    int ticket = 100;
    Object obj = new Object();
    Dog dog = new Dog();

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // synchronized (obj) { // obj必须是唯一的,此处可行，因为只有一个SaleTicket1对象，也就只有一个Object对象。
            // synchronized (this)  // 即SaleTicket1对象，main()中只有一个SaleTicket1
            synchronized (dog) {    // obj必须是唯一的
                if(ticket > 0) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "售票，票号为: " + ticket--);
                } else {
                    break;
                }
            }
        }
    }
}

class Dog {}