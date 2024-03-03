package com.atguigu.reflection.applys.dynamic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class ReflectionTest {
    // 体会：静态性
    public Person getInstance() {
        return new Person();
    }

    // 体会: 反射的动态性
    public <T> T getInstance(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(className);

        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        return (T) declaredConstructor.newInstance();
    }

    // 体会: 反射的动态性
    public Object invoke(String className, String methodName) throws Exception {
        // 1. 创建全类名对应的运行时类的对象
        Class clazz = Class.forName(className);

        Constructor declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);

        Object obj = declaredConstructor.newInstance();

        // 2. 获取运行时类中指定的方法，并调用
        Method declaredMethod = clazz.getDeclaredMethod(methodName);
        declaredMethod.setAccessible(true);

        return declaredMethod.invoke(obj);
    }

    @Test
    public void test1() throws Exception {
        Person p1 = getInstance();
        System.out.println(p1);

        String className = "com.atguigu.reflection.applys.dynamic.Person";
        Person p2 = getInstance(className);
        System.out.println(p2);

        String className1 = "java.util.Date";
        Date date = getInstance(className1);
        System.out.println(date);
    }

    @Test
    public void test2() throws Exception {
        String className = "com.atguigu.reflection.applys.dynamic.Person";
        String methodName = "show";

        System.out.println(invoke(className, methodName));
    }
}
