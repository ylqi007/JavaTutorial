package com.atguigu.thread.communication;

/**
 * Description: 使用两个线程打印1～100，线程1和线程2"交替"打印
 *  线程1打印完之后，进入阻塞状态，不会再跟线程2争抢资源，然后线程2就可以进入同步代码块进行打印
 *
 * @Author: ylqi007
 * @Create: 3/12/24 21:40
 */
public class PrintNumberTest {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber();

        Thread thread1 = new Thread(printNumber, "线程1");
        Thread thread2 = new Thread(printNumber, "线程2");

        thread1.start();
        thread2.start();
    }

}


class PrintNumber implements Runnable {
    private int number = 1;
    private Object obj = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (obj) {    // java.lang.IllegalMonitorStateException: current thread is not owner. 同步资源监视器obj，与下面的调用this.notify()的对象不一致。
            //synchronized (this) {   // 此处this代表PrintNumber的实例，也就是line11的printNumber
                //this.notify();  // 当线程A执行完wait()之后，会进入等待状态。线程B进入同步代码块，然后唤醒线程A，
                                // 但是由于线程B已经占用同步资源监视器，线程A就无法进入此代码块。
                                // this可以省略

                obj.notify();   // 全部换成obj则可行。思考: 任何对象都可以当作同步资源监视器，则任何对象都要有能力调用这个方法，所以定义在Object中
                                // 在本线程A wait之前，把另外一个线程B叫醒，此时B线程无法访问资源，因为线程A持有锁。也可以用this, 省略了this.
                if(number <= 100) {
                    try {
                        Thread.sleep(50);   // sleep()并不会释放资源监视器
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        // this.wait(); // 让当前线程A进入等待状态，同时释放对同步监视器的调用，另一个线程B就可以获取资源，进行打印。this可以省略
                        obj.wait();     // 线程一旦执行此方法，就进入等待状态。同时，会释放对同步监视器的调用
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }
        }
    }
}
