package com.atguigu.thread.communication;

/**
 * Description:
 *  生产者(Producer)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，店员一次只能持有固定数量的产品(比如:20），
 *  如果生产者试图生产更多的产品，店员会叫生产者停一下，
 *  如果店中有空位放产品了再通知生产者继续生产；
 *  如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。
 *
 *  类似的场景，比如厨师和服务员等。
 *
 * 分析:
 *  1. 是否是多线程问题？是！生产者是一个线程，消费者是一个线程
 *  2. 是否有共享数据？有！共享数据是：产品
 *  3. 是否有线程安全问题？有！因为有共享数据
 *  4. 是否需要处理线程安全问题？是！
 *  5. 如何处理？使用同步机制
 *  6. 是否存在线程间的通信？存在
 *
 * @Author: ylqi007
 * @Create: 3/12/24 23:38
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer1 = new Producer(clerk);
        Consumer consumer1 = new Consumer(clerk);
        Consumer consumer2 = new Consumer(clerk);
        producer1.setName("生产者1");
        consumer1.setName("消费者1");
        consumer2.setName("消费者2");

        producer1.start();
        consumer1.start();
        consumer2.start();
    }
}


// 店员，持有产品，作为共享数据
class Clerk {
    private int productNumber = 0; // 产品的数量

    // 增加产品数量的方法
    public synchronized void addProduct() {
        if(productNumber >= 20) {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            productNumber++;
            System.out.println(Thread.currentThread().getName() + "生产了第" + productNumber + "个产品");

            // 唤醒
            notifyAll();    // 有多个消费者时
        }
    }

    // 减少产品数量的方法
    public synchronized void minusProduct() {
        if(productNumber <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {    // 将下面代码放入到else中，避免两个consumers同时唤醒后导致productNumber小于0的情况。
                    // 放入else后，如果productNumber<=0，wait()被唤醒后会结束minusProduct()方法，然后重新进入minusProduct()方法
            System.out.println(Thread.currentThread().getName() + "消费了第" + productNumber + "个产品");
            productNumber--;

            // 唤醒
            // notify();
            notifyAll();    // 有多个消费者时
        }
    }
}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Producer开始生成产品。。。");
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.addProduct();
        }
    }
}


class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("Consumer开始消耗产品");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.minusProduct();
        }
    }
}
