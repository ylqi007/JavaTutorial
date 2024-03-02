package com.atguigu.reflection.applys.apply1;

import com.atguigu.reflection.applys.data.Person;
import org.junit.jupiter.api.Test;

/**
 * 反射应用：创建运行时类的对象
 */
public class NewInstanceTest {
    @Test
    public void test1() throws InstantiationException, IllegalAccessException {
        Class clazz = Person.class;

        // 创建Person类的实例
        Person person = (Person) clazz.newInstance();
        System.out.println(person);
    }
}
