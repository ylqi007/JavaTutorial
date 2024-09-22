package com.ylqi007.thread.deadlock;

/**
 * Description: 死锁测试
 *  不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁。
 *  一旦出现死锁，整个程序既不会发生异常，也不会给出任何提示，只是所有线程都处于阻塞状态，无法继续。
 *
 * @Author: ylqi007
 * @Create: 3/11/24 18:28
 */
public class DeadLockTest1 {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread(){
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(10);  // 便于演示死锁问题
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(() -> {
            synchronized (s2) {
                s1.append("c");
                s2.append("3");

                try {
                    Thread.sleep(100);  // 便于演示死锁问题
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (s1){
                    s1.append("d");
                    s2.append("4");

                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }).start();
    }
}
