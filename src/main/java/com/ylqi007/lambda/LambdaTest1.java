package com.ylqi007.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class LambdaTest1 {

    // 语法格式1: 无参数，无返回值
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable in traditional way");
            }
        };
        r1.run();

        System.out.println("********** Lambda表达式 ******************************");
        Runnable r2 = () -> {
            System.out.println("Runnable in Lambda way 1");
        };
        r2.run();

        Runnable r3 = () -> System.out.println("Runnable in Lambda way 2");
        r3.run();
    }

    // 语法格式2: 需要一个参数，但无返回值
    @Test
    public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言和誓言的区别是什么？");

        System.out.println("********** Lambda表达式 ******************************");
        Consumer<String> consumer1 = (String s) -> System.out.println(s);  // 类型推断->省略类型，String s --> s
        consumer1.accept("一个是说的人当真了，一个是听的人当真了");
    }

    // 语法格式3: 数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
    @Test
    public void test3() {
        Consumer<String> consumer1 = (String s) -> System.out.println(s);  // 类型推断->省略类型，String s --> s
        consumer1.accept("一个是说的人当真了，一个是听的人当真了");

        Consumer<String> consumer2 = s -> System.out.println(s);  // 只有一个参数的时候可以省略小括号
        consumer2.accept("哈哈哈哈");
    }

    // 类型推断例子
    @Test
    public void test3_1() {
        int[] arr = new int[]{1, 2, 3};
        int[] arr1 = {3, 4, 5, 6};  // 类型推断，省略了 new int[]

        HashMap<String, Integer> map = new HashMap<>(); // 类型推断

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        var entries1 = map.entrySet();  // Since JDK10, 类型推断
    }

    // 语法格式4: Lambda 若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test4() {
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("世界那么大，我想去看看");

        System.out.println("*******************");

        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("世界那么大，我想去看看");
    }

    // 语法格式5: Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test5() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator.compare(12, 21));

        System.out.println("********** Lambda表达式 ******************************");
        Comparator<Integer> comparator1 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
        System.out.println(comparator1.compare(12, 12));
    }

    // 语法格式6: 当 Lambda 体只有一条语句时，return 与大括号若有，都可以省略
    @Test
    public void test6() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator.compare(12, 21));

        System.out.println("********** Lambda表达式 ******************************");
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator1.compare(12, 21));
    }
}
