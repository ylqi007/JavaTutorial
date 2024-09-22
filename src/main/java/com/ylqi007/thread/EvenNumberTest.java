package com.ylqi007.thread;

/**
 * Description: 通过继承Thread类，创建线程对象
 *
 * @Author: ylqi007
 * @Create: 3/10/24 10:41
 */
public class EvenNumberTest {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();    // ThreadName: Thread-0 来自空参构造器
        printNumber.start();

        /*
        * 问题1: 能否使用t1.run()替换t1.start()的调用，实现分线程的创建和调用: No
        * 因为在main()方法中直接调用t1.run(),实际上main线程会把t1.run()当成一致普通方法，按照顺序执行
        * */
        // printNumber.run();

        /*
         * 问题2: 再提供一个分线程，用于100内的偶数遍历。
         * 注意: 不能用已经start()的线程，再次执行start()，否在报异常
         * */
        // printNumber.start();    // Exception in thread "main" java.lang.IllegalThreadStateException

        // Correct way: 再创建一个新的线程对象
        PrintNumber printNumber1 = new PrintNumber();   // ThreadName: Thread-1
        printNumber1.start();

        for(int i=0; i<100; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}


// PrintNumber继承了Thread类，并重写了run()方法
class PrintNumber extends Thread {
    @Override
    public void run() {
        for(int i=0; i<100; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
