package com.atguigu.collection.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    @Test
    public void test1() {
        List list = getTestData();
    }


    private List getTestData() {
        List list = new ArrayList();
        list.add("AA");
        list.add(123);
        list.add("BB");
        list.add(new Person("Tom", 12));
        System.out.println(list);

        list.add(2, "CCC");
        System.out.println(list.toString());     // ArrayList.toString() --> AbstractCollection.toString();

        list.addAll(1, Arrays.asList(1, 2, 3));
        list.add(1, Arrays.asList(4, 5, 6));
        System.out.println(list);

        return list;
    }

    @Test
    public void test2() {
        List list = getTestData();
        System.out.println(list);
        list.remove(2); // remove(int index) 而不是 remove(Object object)
        System.out.println(list);

        // 删除数据2
        list.remove(Integer.valueOf(2));
        System.out.println(list);
    }

    @Test
    public void test3() {
        List list = getTestData();

        // 使用iterator
        System.out.println("使用iterator");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 增强for循环
        System.out.println("增强for循环");
        for(Object obj: list) {
            System.out.println(obj);
        }

        // 一般for循环
        System.out.println("一般for循环");
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
