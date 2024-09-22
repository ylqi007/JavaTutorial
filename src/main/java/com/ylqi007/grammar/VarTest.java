package com.ylqi007.grammar;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * JDK10的新特性
 */
public class VarTest {

    @Test
    public void test1() {
        // 1. 局部变量的实例化
        var list = new ArrayList<String>();
        var set = new LinkedHashSet<Integer>();

        // 2. 增强的for循环
        for (var v : list) {
            System.out.println(v);
        }

        // 3. 传统for循环
        for (var i = 0; i < 10; i++) {
            System.out.println(i);
        }

        // 4. 返回值类型含有复杂范型结构
        Iterator<Integer> iterator = set.iterator();
        var iterator1 = set.iterator();

        HashMap<String, Integer> map = new HashMap<>();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        var entries1 = map.entrySet();
    }

    /* 不适用场景
    - 声明一个成员变量
    - 声明一个数组变量，并为数组静态初始化（省略new的情况下）
    - 方法的返回值类型
    - 方法的参数类型
    - 没有初始化的方法内的局部变量声明
    - 作为catch块中异常类型
    - Lambda表达式中函数式接口的类型
    - 方法引用中函数式接口的类型
    * */
    @Test
    public void test2() {
        var arr = new int[]{1, 2, 3};   // OK
        //var arr1 = {1, 2, 3};   // No
    }

    // Wrong
    @Test
    public void test3() {
        //try {
        //    System.out.println(10 / 0);
        //} catch (var e) {   // exception种类太多，无法准确匹配
        //    e.printStackTrace();
        //}
        //var com = (s1,s2)-> s1.compareTo(s2);
    }
}
