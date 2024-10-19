package com.ylqi007.reference;

import com.ylqi007.reference.data.Employee;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 */
public class MethodReferenceTest {
    /**
     * 情况1: 对象::实例方法
     *  Consumer中的void accept(T t)
     *  PrintStream中的void println(T t)
     */
    @Test
    public void test1() {
        // 1. 传统的写法
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {  // accept()方法的形参列表和返回值类型与System.out.println()的形参列别与返回值类型均一致
                System.out.println(s);      // 因此看作println()是对accept()的替换/覆盖
            }
        };
        consumer.accept("Hello, traditional way");

        // 2. Lambda 表达式
        System.out.println("********** Lambda表达式 *************************");
        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer1.accept("Hello, Lambda expression 1");

        // 3. Lambda + Method Reference
        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("Hello, Method Ref");

        // or
        PrintStream ps = System.out;
        Consumer<String> consumer3 = ps::println;
        consumer3.accept("PrintStream");
    }

    // Supplier中的T get()
    // Employee中的String getName()
    @Test
    public void test2() {
        Employee employee = new Employee(1001, "马化腾", 34, 6000.38);
        // 1. 匿名实现类的写法
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return employee.getName();
            }
        };
        System.out.println(supplier.get());

        // 2. Lambda 表达式
        System.out.println("********** Lambda表达式 *************************");
        Supplier<String> supplier1 = () -> employee.getName();
        System.out.println(supplier1.get());

        // 3. Lambda --> Method Reference
        System.out.println("********** Lambda表达式 + Method Ref *************************");
        Supplier<String> supplier2 = employee::getName; // 对象::实例方法
        System.out.println(supplier2.get());
    }

    /**
     * 情况2: 类::静态方法
     *  Comparator中的int compare(T t1, T t2)
     *  Integer中的int compare(T t1, T t2)
     */
    @Test
    public void test3() {
        // 1. 匿名实现类的写法。Traditional way
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {    // compare()的形参列表和返回值类型与Integer.compare()的形参列表和返回值一致(包括多态的兼容)
                return Integer.compare(o1, o2);             // 因此可以使用方法引用
            }
        };
        System.out.println(comparator.compare(12, 21));

        // 2. Lambda 表达式
        System.out.println("********** Lambda表达式 *************************");
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator1.compare(12, 12));

        // 3. Lambda --> Method Reference
        System.out.println("********** Lambda表达式 + Method Ref *************");
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(12, 123));
    }

    // Function中的R apply(T t)
    // Math中的Long round(Double d)
    @Test
    public void test4() {
        // 1. 匿名实现类的写法。Traditional way
        Function<Double, Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };

        // 2. Lambda 表达式
        Function<Double, Long> function1 = aDouble -> Math.round(aDouble);

        // 3. Lambda --> Method Reference
        Function<Double, Long> function2 = Math::round;
        System.out.println(function2.apply(11.3));
    }

    /**
     * 情况3: 类::实例方法
     *  Comparator中的int compare(T t1, T t2)
     *  String中的int t1.compareTo(t2)
     */
    @Test
    public void test5() {
        // 1. Traditional way
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {  // compare()与compareTo()的返回值类型一致
                return o1.compareTo(o2);                // compare()有N个参数，其中参数作为compareTo()的调用者，剩余N-1个是compareTo()的参数
            }
        };
        System.out.println(comparator.compare("abc", "123"));

        // 2. Lambda 表达式
        Comparator<String> comparator1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator1.compare("123", "abc"));

        // 3. Lambda --> Method Reference
        Comparator<String> comparator2 = String::compareTo;
        System.out.println(comparator2.compare("123", "1234"));
    }

    // BiPredicate中的boolean test(T t1, T t2)
    // String中的boolean t1.equals(t2)
   @Test
    public void test6() {
        // 1
        BiPredicate<String, String> biPredicate = new BiPredicate<String, String>() {
            @Override
            public boolean test(String s, String s2) {
                return s.equals(s2);
            }
        };
        System.out.println(biPredicate.test("123", "123"));

        // 2. Lambda 表达式
        BiPredicate<String, String> biPredicate1 = (s1, s2) -> s1.equals(s2);
        System.out.println(biPredicate1.test("abc", "abcd"));

        // 3. Lambda --> Method Reference
        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("aaa", "bbb"));
    }

    @Test
    public void test7() {
        // 1
        Employee employee = new Employee(1001, "马化腾", 34, 6000.38);
        Function<Employee, String> function = new Function<Employee, String>() {
            @Override
            public String apply(Employee employee) {
                return employee.getName();
            }
        };
        System.out.println(function.apply(employee));

        // 2. Lambda 表达式
        Function<Employee, String> function1 = emp -> emp.getName();
        System.out.println(function1.apply(employee));

        // 3. Lambda --> Method Reference
        Function<Employee, String> function2 = Employee::getName;
        System.out.println(function2.apply(employee));
    }
}
