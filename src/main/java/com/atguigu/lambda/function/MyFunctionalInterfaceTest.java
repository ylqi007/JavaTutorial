package com.atguigu.lambda.function;

import org.junit.jupiter.api.Test;

public class MyFunctionalInterfaceTest {
    @Test
    public void test1() {
        MyFunctionalInterface myMethod = () -> System.out.println("Implements MyFunctionalInterface");
        myMethod.run();
    }
}
