package com.ylqi007.thread.safety;

/**
 * Description:
 *  使用实现Runnable接口的方式，创建线程对象
 *  线程不安全
 *
 * @Author: ylqi007
 * @Create: 3/10/24 12:17
 */
public class WindowTest {
    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket();

        // 三个线程对象t1, t2, t3共享资源对象saleTicket
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


class SaleTicket implements Runnable {
    int ticket = 100;
    @Override
    public void run() {
        while(true) {
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
