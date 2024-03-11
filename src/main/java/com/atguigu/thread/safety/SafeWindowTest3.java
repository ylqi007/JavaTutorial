package com.atguigu.thread.safety;

/**
 * Description: 使用同步方法解决继承Thread类中的线程安全问题
 *  1. 使用继承Thread类的方式创建线程对象
 *  2. 使用同步方法: 方法 + 锁，锁必须是唯一的
 *
 * @Author: ylqi007
 * @Create: 3/10/24 12:23
 */
public class SafeWindowTest3 {
    public static void main(String[] args) {
        Window3 w1 = new Window3();
        Window3 w2 = new Window3();
        Window3 w3 = new Window3();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window3 extends Thread {
    static int ticket = 100;   // 实例变量，每个实例都有一个
    static boolean isFlag = true;
    @Override
    public void run() {
        while (isFlag) {
            saleTicket();
        }
    }

    public synchronized void saleTicket() { // 非静态方法，此时同步监视器为this, 此实现中分别问w1, w2, w3, 不安全
        if (ticket > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "售票，票号为: " + ticket--);
        } else {
            isFlag = false;
        }
    }

    public static synchronized void saleTicket1() { // 静态方法，此时同步监视器为类本身,即为Window3.class, 安全。
        if (ticket > 0) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "售票，票号为: " + ticket--);
        } else {
            isFlag = false;
        }
    }
}
