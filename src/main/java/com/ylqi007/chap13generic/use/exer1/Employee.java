package com.ylqi007.chap13generic.use.exer1;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: Employee
 * Description:
 *      定义一个Employee类。
 *          该类包含：private成员变量name,age,birthday，其中 birthday 为 MyDate 类的对象；
 *          并为每一个属性定义 getter, setter 方法；
 *          并重写 toString 方法输出 name, age, birthday
 * @Author 尚硅谷-宋红康
 * @Create 17:03
 * @Version 1.0
 */
@Setter
@Getter
public class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=[" + birthday +
                "]}";
    }

    //按照name从低到高排序
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }
}
