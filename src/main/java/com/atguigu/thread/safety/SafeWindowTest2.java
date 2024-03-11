package com.atguigu.thread.safety;

/**
 * Description:
 *  1. 使用实现Runnable接口的方式创建线程对象
 *  2. 使用同步方法: 方法 + 锁，锁必须是唯一的
 *
 * @Author: ylqi007
 * @Create: 3/10/24 12:17
 */
public class SafeWindowTest2 {
    public static void main(String[] args) {
        SaleTicket2 saleTicket = new SaleTicket2();

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


class SaleTicket2 implements Runnable {
    int ticket = 100;
    boolean isFlag = true;

    @Override
    public void run() {
        while (isFlag) {
            saleTicket();
        }
    }

    // 同步方法
    public synchronized void saleTicket() { // 默认的同步监视器: this，此时即为saleTicket，是唯一的，安全
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
