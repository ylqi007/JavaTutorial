package com.ylqi007;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Ref1: https://www.cnblogs.com/fortunely/p/14121276.html
 */
public class ArrayAndList {

    /**
     * Array --> List
     * 这是最基本、最通用的方法，适合任意基本元素类型和语言。即遍历 array，然后加入 list。
     * 1. 利用遍历数组每个元素，将元素一个个加入List
     * 2. 利用数组工具类Arrays的内置方法asList
     * 3. 利用集工具类Collections.addAll方法
     */
    @Test
    public void test01() {
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[]{3,8,20,7,11,25};

        for (Integer d: arr) {
            list.add(d);
        }

        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 对象数组转化为List
        Integer[] arr1 = new Integer[]{3,8,20,7,11,25};
        List<Integer> list1 = new ArrayList<>(Arrays.asList(arr1));

        /*
         * 基本类型数组先用流对每个元素装箱, 转化为对应包装类型数组
         * 再通过Arrays.asList转化为列表视图
         * 通过新建List的构造器, 新建列表List
         */
        int[] b = new int[]{3,8,20,7,11,25};
        Integer[] boxB = Arrays.stream(b).boxed().toArray(Integer[]::new);
        List<Integer> list2 = new ArrayList<>(Arrays.asList(boxB));
    }

    @Test
    public void test03() {
        Integer[] a = new Integer[]{3,8,20,7,11,25};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, a);
    }

    /**
     * List --> Array
     */
    @Test
    public void test04() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, new Integer[]{1,2,3,4,5});

        int[] a = new int[list.size()];
        int i = 0;
        for (Integer d: list) {
            a[i++] = d;
        }

        for (int j = 0; j < a.length; j++) {
            System.out.println(a[j]);
        }
    }

    @Test
    public void test05() {
        // 构建列表list, 并插入数据用于测试
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            list.add(i);
        }

        // List转Object[]
        Object[] objs = list.toArray();

        // Object[] 转Integer[]
        Integer[] nums = Arrays.stream(objs).toArray(Integer[]::new);

        // Integer[] 转int[]
        int[] arr = Arrays.stream(nums).mapToInt(Integer::valueOf).toArray();
    }

    @Test
    public void test06() {
        // 构建列表list, 并插入数据用于测试
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            list.add(i);
        }

        int[] arr1 = list.stream().mapToInt(Integer::intValue).toArray();
        // 也可以用下面的语句
        int[] arr2 = list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
