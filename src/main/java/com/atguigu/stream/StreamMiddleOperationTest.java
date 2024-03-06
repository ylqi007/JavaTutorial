package com.atguigu.stream;

import com.atguigu.reference.data.Employee;
import com.atguigu.reference.data.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StreamMiddleOperationTest {
    /**
     * 1. 筛选与切片
     */
    @Test
    public void test1() {
        // 1. filter(Predicate p) 接收Lambda，从stream中排除某些元素
        // 查询员工表中薪资大于7000的员工信息
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        stream.filter(employee -> employee.getSalary() > 7000).forEach(System.out::println);

        System.out.println("**************************************************");
        // 2. limit(n) 截断流，使其元素不超过给定数量
        // 因为stream已经执行了终止操作，就不可以再调用其他的中间操作or终止操作。
        // stream.limit(2).forEach(System.out::println);   // java.lang.IllegalStateException: stream has already been operated upon or closed, 需要创建新的stream
        employees.stream().limit(4).forEach(System.out::println);

        System.out.println("**************************************************");
        // 3. skip(n) 跳过元素，返回一个扔掉N个元素的流，若流中元素不足N个，则返回一个空流。与limit(n)互补
        employees.stream().skip(5).forEach(System.out::println);

        System.out.println("**************************************************");
        // 4. distict() 筛选，通过流中元素的hashCode()和equals()去除重复元素
        employees.add(new Employee(1009, "马斯克", 40, 4000.32));
        employees.add(new Employee(1009, "马斯克", 40, 4000.32));
        employees.add(new Employee(1009, "马斯克", 40, 4000.32));
        employees.add(new Employee(1009, "马斯克", 40, 4000.32));
        employees.stream().distinct().forEach(System.out::println);
    }

    /**
     * 2. 映射 map(Function f) 接收一个函数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素
     */
    @Test
    public void test2() {
        // 转换成大写
        List<String> list = Arrays.asList("a", "bb", "ccc", "dddd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);    // Method reference

        System.out.println("**************************************************");

        List<Employee> employees = EmployeeData.getEmployees();
        // 获取员工名字长度大于3的员工
        employees.stream().filter(emp -> emp.getName().length() > 3).forEach(System.out::println);

        System.out.println("**************************************************");
        // 获取员工名字长度大于3的员工姓名
        employees.stream().filter(emp -> emp.getName().length() > 3).map(Employee::getName).forEach(System.out::println);

        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);
    }

    /**
     * 3. 排序
     */
    @Test
    public void test3() {
        // sorted() 自然排序
        Integer[] arr = new Integer[]{5, 4, 3, 2, 1, 6, 7, 9, 8, 10};
        Arrays.stream(arr).sorted().forEach(System.out::println);
        System.out.println(Arrays.toString(arr));   // 原有的arr中，元素顺序并没有发生改变

        // sorted(Comparator com) 定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        // 因为Employee没有实现Comparable接口，所以报错！
        // java.lang.ClassCastException: class com.atguigu.reference.data.Employee cannot be cast to class java.lang.Comparable
        // employees.stream().sorted().forEach(System.out::println);
        employees.stream().sorted((a, b) -> Double.compare(a.getSalary(), b.getSalary())).forEach(System.out::println);
        System.out.println();
        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(System.out::println);
    }
}
