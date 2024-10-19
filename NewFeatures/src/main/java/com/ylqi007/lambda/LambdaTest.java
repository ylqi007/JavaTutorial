package com.ylqi007.lambda;

import org.junit.jupiter.api.Test;
import java.util.Comparator;

/**
 * 删掉所有可以删掉的地方: 因为Runnable接口只有一个方法，所以实现Runnable接口并实现run()方法的时候，
 * new Runable() {}, @Override, public void run，这些都是确定的，可以省略。
 *  Runnable接口只有一个方法，因此要实现它时，要实现的方法也是确定的，即只能实现run()
 * 方法参数的括号()要保留，但是有可能用()中的东西，因此保留
 */
public class LambdaTest {

    /**
     * 语法格式1: 无参数，无返回值
     */
    @Test
    public void test1() {
        // 匿名实现类: 传统的写法，Traditional way
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable in traditional way");
            }
        };
        r1.run();

        System.out.println("**************************************************");
        Runnable r2 = () -> {
            System.out.println("Runnable in Lambda way 1");
        };
        r2.run();

        System.out.println("**************************************************");
        Runnable r3 = () -> System.out.println("Runnable in Lambda way 2");
        r3.run();
    }

    @Test
    public void test02() {
        // Traditional way
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator1.compare(12, 21));

        System.out.println("********** Lambda表达式 *************************");
        Comparator<Integer> comparator2 = (o1, o2) -> {
            return Integer.compare(o1, o2);
        };
        System.out.println(comparator2.compare(21, 12));

        // 删除 return的写法
        Comparator<Integer> comparator3 = (o1, o2) -> Integer.compare(o1, o2);  // 有 "->"，Lambda表达式
        System.out.println(comparator3.compare(12, 12));

        System.out.println("********** 方法引用 *****************************");
        Comparator<Integer> comparator4 = Integer::compare;                     // 有"::"，方法引用
        System.out.println(comparator4.compare(15, 16));
    }

    public void test03() {
        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
    }

}
