package com.atguigu.thread;

/**
 * Description: 分析Thread.run()方法到底run()了什么
 *
 * @Author: ylqi007
 * @Create: 3/10/24 11:24
 */
public class Exer1 {
    public static void main(String[] args) {
        BB bb = new BB();

        // Without override, it runs Thread.run(), i.e. run target.run(), and target is bb here
        //@Override
        //public void run() {
        //    if (target != null) {
        //        target.run();
        //    }
        //}
        //
        // With override, the original Thread.run() is override like the following
        new Thread(bb){
            @Override
            public void run() {
                System.out.println("CCC running");
            }
        }.start();
    }
}

class AA extends Thread {
    @Override
    public void run() {
        System.out.println("A running");
    }
}

class BB extends Thread {
    @Override
    public void run() {
        System.out.println("BB running");
    }
}

