package com.atguigu.thread.safety;

/**
 * Description: 测试Thread.sleep()
 *
 * @Author: ylqi007
 * @Create: 3/10/24 13:56
 */
public class HappyNewYear {
    public static void main(String[] args) {
        for(int i=10; i>=0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(i > 0) {
                System.out.println(i);
            } else {
                System.out.println("Happy New Year!");
            }
        }
    }
}
