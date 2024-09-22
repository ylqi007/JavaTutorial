package com.ylqi007.thread.safety;

/**
 * Description:
 *  使用继承Thread
 *  线程不安全
 *
 * @Author: ylqi007
 * @Create: 3/10/24 12:23
 */
public class WindowTest1 {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window extends Thread {
    // int ticket = 100;   // 实例变量，每个实例都有一个
    static int ticket = 100;
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
