package com.ylqi007.thread.lock;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/12/24 20:58
 */
public class LockUnsafeTest {
    public static void main(String[] args) {
        Window1 w1 = new Window1();
        Window1 w2 = new Window1();
        Window1 w3 = new Window1();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}



class Window1 extends Thread {
    static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if(ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "售票，票号为：" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}