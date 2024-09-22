package com.ylqi007.reflection.applys.exer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class FruitTest {

    @Test
    public void test1() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 1. 读取配置文件中的信息，获取全类名
        Properties properties = new Properties();
        File file = new File("src/main/java/com/atguigu/reflection/applys/exer/config.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        properties.load(fileInputStream);
        String fruitName = properties.getProperty("fruitName");
        System.out.println(fruitName);

        // 2. 通过反射，创建指定全类名对应的类实例
        Class<?> clazz = Class.forName(fruitName);
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Fruit fruit = (Fruit) declaredConstructor.newInstance();

        // 3. 通过榨汁机对象调用run
        Juicer juicer = new Juicer();
        juicer.run(fruit);
    }
}
