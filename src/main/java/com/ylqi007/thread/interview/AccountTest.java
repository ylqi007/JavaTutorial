package com.ylqi007.thread.interview;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/10/24 14:04
 */
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        Customer customer1 = new Customer("甲", account);
        Customer customer2 = new Customer("乙", account);

        customer1.start();
        customer2.start();
    }
}


class Account {
    private double balance; // 余额

    public synchronized void deposit(double amt) {  // this为account对象,是唯一的，line11
    //public void deposit(double amt) {
        if(amt > 0) {
            balance += amt;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "存钱1000块，余额为: " + balance);
    }
}


class Customer extends Thread {
    Account account;

    public Customer(Account account) {
        this.account = account;
    }

    public Customer(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.deposit(1000);
        }
    }
}