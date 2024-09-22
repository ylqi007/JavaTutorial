package com.ylqi007.stream;

import com.ylqi007.reference.data.Employee;
import com.ylqi007.reference.data.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreationTest {
    /**
     * 创建Stream的方式1: 通过集合
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // default Stream<E> stream(): 返回一个顺序流，来自接口java.util.Collection的default方法
        Stream<Employee> stream = employees.stream();

        // default Stream<E> parallelStream(): 返回一个并行流，来自接口java.util.Collection的default方法
        Stream<Employee> employeeStream = employees.parallelStream();

        System.out.println(stream);
        System.out.println(employeeStream);
    }

    /**
     * 方式二: 调用Arrays类的 public static <T> Stream<T> stream(T[] array) : 返回一个stream
     */
    @Test
    public void test2() {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        Stream<Integer> stream = Arrays.stream(arr);

        int[] arr1 = new int[]{1, 2, 3, 4};
        IntStream intStream = Arrays.stream(arr1);  // 对基础数据类型，由特定的stream。可以理解为不能写成Stream<int>, 所以用IntStream代替
    }

    /**
     * 方式三: 通过Stream.of(...)
     * 当多个数据没有呈现集合或数组的形式的时候，可以使用Stream.of(...)
     */
    @Test
    public void test3() {
        Stream<String> stringStream = Stream.of("A", "BB", "CCC", "DDDD");
    }
}
