package com.atguigu.reflection.applys.apply4;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ReflectAnnotationTest {
    // 获取类声明上的主机
    @Test
    public void test1() {
        Class clazz = Customer.class;

        Table declaredAnnotation = (Table) clazz.getDeclaredAnnotation(Table.class);

        System.out.println(declaredAnnotation.value());
    }

    //获取属性声明上的注解
    @Test
    public void test2() throws NoSuchFieldException {
        Class clazz = Customer.class;

        Field nameField = clazz.getDeclaredField("name");

        //获取属性声明上的注解
        Column nameColumn = nameField.getDeclaredAnnotation(Column.class);
        System.out.println(nameColumn.columnName());//cust_name
        System.out.println(nameColumn.columnType()); //varchar(15)
    }
}
