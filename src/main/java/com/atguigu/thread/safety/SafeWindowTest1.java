package com.atguigu.thread.safety;

/**
 * Description:
 *  1. 使用继承Thread方式创建线程对象
 *  2. 使用同步代码块: 代码块 + 锁，锁必须是唯一的
 *
 * @Author: ylqi007
 * @Create: 3/10/24 12:23
 */
public class SafeWindowTest1 {
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
    // int ticket = 100;   // 实例变量，每个实例都有一个
    static int ticket = 100;
    Object obj = new Object();
    static Object staticObj = new Object();

    @Override
    public void run() { // 直接锁在这里，肯定不行，会导致只有一个窗口买票
        while(true) {
            // synchronized (this) {   // this分别指w1, w2, w3 --> 错误，不能保证锁的唯一性
            // synchronized (obj) {    // 每个Window对象都有一份，不能保证唯一性
            // synchronized (staticObj) {   // 可行
            synchronized (Window1.class) {  // 可行 Class clazz = Window1.class
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
