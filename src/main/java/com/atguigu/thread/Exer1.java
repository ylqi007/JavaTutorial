package com.atguigu.thread;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/10/24 11:24
 */
public class Exer1 {
    public static void main(String[] args) {
        BB bb = new BB();

        new Thread(bb){
            //@Override
            //public void run() {
            //    System.out.println("CCC running");
            //}
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

