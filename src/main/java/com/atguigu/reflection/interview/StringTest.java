package com.atguigu.reflection.interview;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class StringTest {
    /**
     * 在JDK17中会fail，因为JDK17的新特性
     * 403: Strong Encapsulate JDK Internals 强封装JDK的内部API，无法暴力反射
     * 针对核心源码的API，内部私有的结构在JDK17中就不可以通过反射访问了
     */
    @Test
    public void test1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = Class.forName("java.lang.String");

        String str = (String) clazz.newInstance();

        // 获得value属性，并获取其值
        Field valueField = clazz.getDeclaredField("value");
        valueField.setAccessible(true);
        // JDK11: [B@6f03482
        // JDK17: fail，因为JDK17的新特性，强封装JDK的内部API： java.lang.reflect.InaccessibleObjectException: Unable to make field private final byte[] java.lang.String.value accessible: module java.base does not "opens java.lang" to unnamed module @26ba2a48
        System.out.println(valueField.get(str));
    }
}
