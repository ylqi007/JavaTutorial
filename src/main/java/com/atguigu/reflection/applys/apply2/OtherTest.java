package com.atguigu.reflection.applys.apply2;

import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {
    // 1. 熟悉: 获取运行时类的内部结构2: 父类，接口们，包，带范型的父类，父类的范型等
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atguigu.reflection.applys.data.Person");
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass); // class com.atguigu.reflection.applys.data.Creature
    }

    // 2. 获取运行时类的接口们
    @Test
    public void test2() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atguigu.reflection.applys.data.Person");
        Class[] interfaces = clazz.getInterfaces();
        //interface java.lang.Comparable
        //interface com.atguigu.reflection.applys.data.MyInterface
        for(Class c: interfaces) {
            System.out.println(c);
        }
    }

    // 3. 获取运行时类的包
    @Test
    public void test3() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atguigu.reflection.applys.data.Person");
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);   // package com.atguigu.reflection.applys.data
    }

    // 4. 获取运行时类的带范型的父类
    @Test
    public void test4() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atguigu.reflection.applys.data.Person");
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass); // com.atguigu.reflection.applys.data.Creature<java.lang.String>
    }

    /**
     * 5. 获取运行时类的父类的范型 (难)
     * 平时写代码：
     *  类型1: 业务逻辑代码 (平时多关注，你的价值就体现在此处，需要时间沉淀)
     *  类型2: 算法逻辑代码 (多积累)
     */
    @Test
    public void test5() throws ClassNotFoundException {
        Class clazz = Class.forName("com.atguigu.reflection.applys.data.Person");
        // 获取带范型的父类(Type是一个接口, Class实现了此接口)
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass); // com.atguigu.reflection.applys.data.Creature<java.lang.String>

        // 如果父类是带范型的，则可以强转为ParameterizedType
        ParameterizedType genericSuperclass1 = (ParameterizedType) genericSuperclass;
        // 调用getActualTypeArguments()获取范型的参数，结果是一个数组，因为可能由多个范型参数
        Type[] actualTypeArguments = genericSuperclass1.getActualTypeArguments();
        for(Type type: actualTypeArguments) {
            // 获取范型参数的名称
            System.out.println(type + ": " + type.getTypeName());
            System.out.println("## " + ((Class)type).getName());
        }
    }
}
