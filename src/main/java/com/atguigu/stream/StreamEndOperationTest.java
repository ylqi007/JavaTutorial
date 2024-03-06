package com.atguigu.stream;

import com.atguigu.reference.data.Employee;
import com.atguigu.reference.data.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamEndOperationTest {
    /**
     * 1. 匹配与查找
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 1. allMatch(Predicate p) 检查所有匹配所有元素
        boolean allMatch = employees.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(allMatch);

        // 2. anyMatch(Predicate p) 检查是否至少匹配一个元素
        System.out.println(employees.stream().anyMatch(employee -> employee.getAge() > 30));

        // 是否存在员工的工资大于10000
        System.out.println(employees.stream().anyMatch(employee -> employee.getSalary() > 1000));

        // 3. findFirst()
        System.out.println(employees.stream().findFirst()); // Optional[Employee{id=1001, name='马化腾', age=34, salary=6000.38}]
    }

    /**
     * 2.
     */
    @Test void test2() {
        List<Employee> employees = EmployeeData.getEmployees();

        // count
        System.out.println(employees.stream().count());

        // max(Comparator cmp) -- 返回流中最大值
        // 返回最高的工资
        System.out.println(employees.stream().map(Employee::getSalary).sorted(Double::compareTo).findFirst());  // 返回最低工资
        System.out.println(employees.stream().map(Employee::getSalary).sorted((a, b) -> (int) (b - a)).findFirst());
        System.out.println(employees.stream().map(Employee::getSalary).max(Double::compareTo));
        System.out.println(employees.stream().map(Employee::getSalary).max(Double::compare));

        // min(Comprator cmp) -- 返回流中最小值
        System.out.println(employees.stream().map(Employee::getSalary).min(Double::compare));

        // forEach(Consumer c) -- 内部迭代
        employees.stream().forEach(System.out::println);

        System.out.println("**************************************************");
        // 针对集合，JDK8中增加了一个遍历的方法
        // 针对List而言，遍历的方式有
        // 1. 使用Iterator
        // 2. 增强for循环
        // 3. forEach(Consumer consumer)
        employees.forEach(System.out::println);
    }

    /**
     * 3. 归约 reduce
     */
    @Test void test3() {
        // 1. reduce(T identity, BinaryOperator) -- 类哦空啊每个stream中元素反复结合起来，得到一个值，返回T
        // 计算1-10的自然数之和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().reduce(0, (a, b) -> a + b));
        System.out.println(list.stream().reduce(10, (a, b) -> a + b));
        System.out.println(list.stream().reduce(10, (a, b) -> Integer.sum(a, b)));
        System.out.println(list.stream().reduce(10, Integer::sum));

        // 2. reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值，返回Optional<T>
        // 计算所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        System.out.println(employees.stream().map(Employee::getSalary).reduce((salary1, salary2) -> salary1 + salary2));
        System.out.println(employees.stream().map(Employee::getSalary).reduce(Double::sum));
    }

    /**
     * 4. 收集
     */
    @Test
    public void test4() {
        List<Employee> employees = EmployeeData.getEmployees();

        // collect(Collector c) -- 将stream转换成其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        // 1. 查找工资大于6000的员工，结果返回一个List或Set
        List<Employee> list = employees.stream().filter(employee -> employee.getSalary() > 5000).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("**************************************************");

        // 2. 按照员工年龄排序，返回一个新的List
        List<Employee> list1 = employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).collect(Collectors.toList());
        list1.forEach(System.out::println);
    }
}
