package com.ylqi007.reflection.interview;

import org.junit.jupiter.api.Test;

public class OrderTest {
    @Test
    public void test1() throws ClassNotFoundException {
        String className = "com.atguigu.reflection.interview.Order";
        Class.forName(className);   // 会执行类构造器的<clinit>()方法
        Class.forName(className);   // 这行不会执行，因为类已经加载了，不会再次加载
    }

    /**
     * JVM的面试题
     * 通过类加载器加载类的时候，并没有执行初始化
     */
    @Test
    public void test2() throws ClassNotFoundException {
        String className = "com.atguigu.reflection.interview.Order";
        ClassLoader.getSystemClassLoader().loadClass(className);    //
    }
}
