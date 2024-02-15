package com.atguigu.oop3._static;

public class OrderTest {
    public static void main(String[] args) {
        Order order = null;

        order.sayHello();   // 并不会出现NullPointerException
        System.out.println(order.count);
    }
}

class Order {
    public static int count = 1;

    public static void sayHello() {
        System.out.println("Hello!");
    }
}
