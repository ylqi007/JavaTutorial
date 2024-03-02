package com.atguigu.reflection.example2;

import org.junit.jupiter.api.Test;
import java.lang.annotation.ElementType;

public class ClassTest {
    /**
     * 获取Class实例的几种方式(掌握前三种)
     * @throws ClassNotFoundException
     */
    @Test
    public void test1() throws ClassNotFoundException {
        // 1. 调用运行时类的静态属性：class
        Class clazz1 = User.class;
        System.out.println(clazz1);

        // 2. 调用运行时类的对象的getClass()
        User user = new User();
        Class clazz2 = user.getClass();
        System.out.println(clazz2);
        System.out.println(clazz1 == clazz2);

        // 3. 调用Class的静态方法forName(String className) --> 用的更多，更体现动态性
        String className = "com.atguigu.reflection.example2.User"; // 全类民
        Class clazz3 = Class.forName(className);
        System.out.println(clazz3);
        System.out.println(clazz3 == clazz1);
        
        // 4. 使用类的加载器的方式(了解)
        Class clazz4 = ClassLoader.getSystemClassLoader().loadClass("com.atguigu.reflection.example2.User");
        System.out.println(clazz4);
        System.out.println(clazz4 == clazz1);
    }

    @Test
    public void test2() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
