package com.ylqi007.thread.deadlock;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/12/24 01:03
 */
public class DeadLockTest3 {
    public static void main(String[] args) {
        Object good = new Object();
        Object money = new Object();
        Owner owner = new Owner(good, money);
        Customer customer = new Customer(good, money);
        new Thread(customer).start();
        new Thread(owner).start();
    }
}


class Owner implements Runnable {
    private Object goods;
    private Object money;

    public Owner(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (goods) {
            System.out.println("先给钱");
            synchronized (money) {
                System.out.println("发货");
            }
        }
    }
}


class Customer implements Runnable {
    private Object goods;
    private Object money;

    public Customer(Object goods, Object money) {
        this.goods = goods;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (money) {
            System.out.println("先发货");
            synchronized (goods) {
                System.out.println("再给钱");
            }
        }
    }
}