package com.ylqi007.thread.method_lifecycle;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/10/24 11:28
 */
public class EvenNumberTest {
    public static void main(String[] args) {
        PrintNumber printNumber = new PrintNumber("线程1");
        printNumber.setName("自线程1");
        printNumber.setPriority(Thread.MIN_PRIORITY);
        printNumber.start();

        Thread.currentThread().setName("主线程");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        for(int i=0; i<100; i++) {
            if(i % 5 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getPriority() + " **** " + i);
                try {
                    Thread.sleep(500);  // Add exception to method signature 或者 try-catch
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

//            if(i % 20 == 0) {
//                try {
//                    printNumber.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
        }

        System.out.println(printNumber.isAlive());
    }
}

class PrintNumber extends Thread {
    public PrintNumber() {
    }

    public PrintNumber(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i=0; i<100; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getPriority() + " ---> " + i);

            try {
                Thread.sleep(100);  // 只能try-catch
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(i % 20 == 0) {
                System.out.println(Thread.currentThread().getName() + "主动释放资源");
                Thread.yield(); // 主动释放CPU资源
            }
        }
    }
}
