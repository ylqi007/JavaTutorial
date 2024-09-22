package com.ylqi007.reference;

import com.ylqi007.reference.data.Employee;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorReferenceTest {
    @Test
    public void test1() {
        // 1. Traditional way
        Supplier<Employee> supplier = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(supplier.get());

        // 2. Lambda
        Supplier<Employee> supplier1 = () -> new Employee();
        System.out.println(supplier1.get());

        // 3. Constructor Reference
        Supplier<Employee> supplier2 = Employee::new;   // 调用的是Employee的空参构造器
        System.out.println(supplier2.get());
    }

    // Function中的R apply(T t)
    @Test
    public void test2() {
        // 1. Traditional way
        Function<Integer, Employee> function = new Function<Integer, Employee>() {
            @Override
            public Employee apply(Integer id) {
                return new Employee(id);
            }
        };
        System.out.println(function.apply(1));

        // 2. Lambda
        Function<Integer, Employee> function1 = Employee::new;
        System.out.println(function1.apply(2));

        // 3. Constructor Reference
        Function<Integer, Employee> function2 = Employee::new;  // 调用的是Employee类中参数为Integer/int的构造器
        System.out.println(function2.apply(3));
    }

    // BiFunction中的 R apply(T t, U u)
    @Test
    public void test3() {
        // 1.
        BiFunction<Integer, String, Employee> function = new BiFunction<Integer, String, Employee>() {
            @Override
            public Employee apply(Integer id, String name) {
                return new Employee(id, name);
            }
        };
        System.out.println(function.apply(1, "Tom"));

        // 2.
        BiFunction<Integer, String, Employee> function1 = (id, name) -> new Employee(id, name);
        System.out.println(function1.apply(2, "Jerry"));

        // 3. Constructor Ref
        BiFunction<Integer, String, Employee> function2 = Employee::new;    // 调用的是Employee类中参数为Integer/int和String的构造器
        System.out.println(function2.apply(3, "Henly"));
    }

}
