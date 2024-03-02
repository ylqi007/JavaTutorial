package com.atguigu.reflection.applys.apply3;

import com.atguigu.reflection.applys.data.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RelectTest {
    /***************** 调用指定的属性 *********************/
    @Test
    public void test1() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> personClass = Person.class;
        
        Person person = personClass.newInstance();
        
        // 1. 获取运行时类指定名的属性
        Field ageField = personClass.getField("age");
        System.out.println(ageField.get(person));

        // 2. 获取or设置运行时类指定名的属性
        ageField.set(person, 2);
        System.out.println(ageField.get(person));
    }

    @Test
    public void test2() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<Person> personClass = Person.class;

        Person person = personClass.newInstance();

        // 1. 通过Class实例调用getDeclaredField(String fieldName)，获取运行时类指定名的属性
        Field nameField = personClass.getDeclaredField("name"); // private
        // 2. 确保此属性是可以访问的
        nameField.setAccessible(true);
        System.out.println(nameField.get(person));

        // 3. 通过Field类的实例调用set/get方法获取or设置运行时类指定名的属性
        nameField.set(person, "Tome");
        System.out.println(nameField.get(person));
    }

    /**
     * Person类的info是static field
     */
    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        // 1. 通过Class实例调用getDeclaredField(String fieldName)，获取运行时类指定名的属性
        Field infoField = personClass.getDeclaredField("info"); // private

        // 2. 确保此属性是可以访问的
        infoField.setAccessible(true);

        // 3. 通过Field类的实例调用set/get方法获取or设置运行时类指定名的属性
        System.out.println("=== 方式1 ===");
        infoField.set(Person.class, "我是一个人");
        System.out.println(infoField.get(Person.class));

        // 方式2, 仅限于类变量
        System.out.println("=== 方式2 ===");
        infoField.set(null, "我是一个人");
        System.out.println(infoField.get(null));
    }

    /***************** 调用指定的方法 *********************/
    @Test
    public void test4() throws Exception {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();

        // 1. 通过Class的实例调用getDeclaredMethod(String methodName, Class... args)，获取指定的方法
        // 注意：必须是int.class, 而不是Integer.class。值可以自动装箱，类型不行
        Method showNation = personClass.getDeclaredMethod("showNation", String.class, int.class);

        // 2. 确保此属性是可以访问的
        showNation.setAccessible(true);

        // 3. 通过Method类的实例调用invoke(Object... ojb)，即为对Method对应方法的调用
        // invoke()的返回值即为Method对应方法的返回值
        // 特别的，如果Method对应的方法的返回值类型为void，则invoke()返回值为null
        Object returnValue = showNation.invoke(person, "USA", 10);
        System.out.println(returnValue);
    }

    /**
     * 静态方法的调用
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        Class<Person> personClass = Person.class;

        // 1. 通过Class的实例调用getDeclaredMethod(String methodName, Class... args)，获取指定的方法
        // 注意：必须是int.class, 而不是Integer.class。值可以自动装箱，类型不行
        Method showInfo = personClass.getDeclaredMethod("showInfo");

        // 2. 确保此属性是可以访问的
        showInfo.setAccessible(true);

        // 3. 通过Method类的实例调用invoke(Object... ojb)，即为对Method对应方法的调用
        // invoke()的返回值即为Method对应方法的返回值
        // 特别的，如果Method对应的方法的返回值类型为void，则invoke()返回值为null
        Object returnValue = showInfo.invoke(null);
        System.out.println(returnValue);
    }

    /***************** 调用指定的构造器 *********************/
    /**
     * 调用私有化的构造器 private Person(String name, int age)
     */
    @Test
    public void test6() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        // 1. 调用Class实例调用getDeclaredConstructor(Class... args)，获取指定参数类型的构造器
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);

        // 2. 确保此属性是可以访问的
        declaredConstructor.setAccessible(true);

        // 3. 通过调用Constructor实例调用newInstance(Object... arg)，返回一个运行时类的实例
        Person person = declaredConstructor.newInstance("Tom", 12);
        System.out.println(person);
    }

    /**
     * 使用Constructor提花原有的使用Class调用newInstance()的方式创建对象
     */
    @Test
    public void test7() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Person> personClass = Person.class;

        // 1. 调用Class实例调用getDeclaredConstructor(Class... args)，获取指定参数类型的构造器
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor();

        // 2. 确保此属性是可以访问的
        declaredConstructor.setAccessible(true);

        // 3. 通过调用Constructor实例调用newInstance(Object... arg)，返回一个运行时类的实例
        // 原来的方式是： Person person = personClass.newInstance(); // deprecated since JDK9
        Person person = declaredConstructor.newInstance();
        System.out.println(person);
    }
}
