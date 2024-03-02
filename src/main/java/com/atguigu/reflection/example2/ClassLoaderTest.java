package com.atguigu.reflection.example2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 说明：在视频中，宋红康用的是JDK8版本，而此处用的是JDK17，所以输出结果会有不一致
 */
public class ClassLoaderTest {
    @Test
    public void test1() {
        // 获取系统类加载器
        ClassLoader classLoader1 = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader1);   // jdk.internal.loader.ClassLoaders$AppClassLoader@251a69d7

        // 在JDK8中，是获取扩展类加载器
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);   // jdk.internal.loader.ClassLoaders$PlatformClassLoader@6cc7b4de

        // 获取引导类加载器: 失败
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);   // null
    }

    @Test
    public void test2() throws ClassNotFoundException {
        // 说明用户自定义的类使用的是系统类加载器加载的
        Class clazz1 = User.class;
        ClassLoader classLoader1 = clazz1.getClassLoader();
        System.out.println(classLoader1);    // jdk.internal.loader.ClassLoaders$AppClassLoader@251a69d7

        // java的核型API，使用的是引导类加载器
        Class clazz2 = Class.forName("java.lang.String");
        ClassLoader classLoader2 = clazz2.getClassLoader();
        System.out.println(classLoader2);   // null
    }

    // 需求: 通过ClassLoader加载指定的配置文件
    // Properties: 处理属性文件
    @Test
    public void test3() throws IOException {
        Properties properties = new Properties();
        // 读取文件的默认路径为：当前的module
        FileInputStream inputStream = new FileInputStream(new File("info.properties"));

        properties.load(inputStream);
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        System.out.println(name);
        System.out.println(password);
    }

    @Test
    public void test4() throws IOException {
        Properties properties = new Properties();
        // 此时通过类的加载器读取文件的默认路径为：当前module下的src/main/resources
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("info1.properties");

        properties.load(resourceAsStream);
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        System.out.println(name);
        System.out.println(password);
    }
}
