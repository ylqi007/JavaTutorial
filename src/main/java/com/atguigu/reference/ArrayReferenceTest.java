package com.atguigu.reference;

import com.atguigu.reference.data.Employee;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class ArrayReferenceTest {
    // 数组引用
    // Function中的R apply(T t)
    @Test
    public void test1() {
        // 1
        Function<Integer, Employee[]> function = new Function<Integer, Employee[]>() {
            @Override
            public Employee[] apply(Integer length) {
                return new Employee[length];
            }
        };
        System.out.println(function.apply(10).length);

        // 2.
        Function<Integer, Employee[]> function1 = length -> new Employee[length];
        System.out.println(function1.apply(20).length);

        // 3.
        Function<Integer, Employee[]> function2 = Employee[]::new;
        System.out.println(function2.apply(30).length);
    }
}
