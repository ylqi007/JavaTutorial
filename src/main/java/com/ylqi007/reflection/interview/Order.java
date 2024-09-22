package com.ylqi007.reflection.interview;

public class Order {
    static int orderId = 1;

    static {
        orderId = 2;
        System.out.println("Order Id in static block...");
    }
}
