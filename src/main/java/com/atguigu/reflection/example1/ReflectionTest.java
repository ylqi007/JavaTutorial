package com.atguigu.reflection.example1;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {

    /**
     * 不使用Reflection
     */
    @Test
    public void test1() {
        // 1. 创建Person类的实例
        Person p1 = new Person();

        // 2. 调用属性
        p1.age = 10;
        System.out.println(p1.age);

        // 3. 调用方法
        p1.show();
    }

    /**
     * 使用Reflection
     */
    @Test
    public void test2() throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        // 1. 创建Person类的实例
        Class<Person> clazz = Person.class;
        Person p1 = clazz.newInstance();
        System.out.println(p1);

        // 2. 调用属性
        Field ageField = clazz.getField("age");
        ageField.set(p1, 10);
        System.out.println(p1);

        // 3. 调用方法
        Method showMethod = clazz.getMethod("show");
        showMethod.invoke(p1);
    }

    /**
     * 在Person类之外，不能直接调用Person类中声明的private权限修饰的结构
     * 但是，可以通过反射的形式，调用Person类中的私有结构
     */
    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> clazz = Person.class;
        Constructor<Person> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);    // 因为是私有结构，需要设置accessible to true，才可以调用
        Person person = declaredConstructor.newInstance("Tom", 12);
        System.out.println(person);

        // 2. 调用私有属性
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Jerry");
        System.out.println(person);
        System.out.println(nameField.get(person));

        // 3. 调用私有方法
        Method showNationMethod = clazz.getDeclaredMethod("showNation", String.class);
        showNationMethod.setAccessible(true);
        String nation = (String) showNationMethod.invoke(person, "USA");
        System.out.println(nation);
    }
}
