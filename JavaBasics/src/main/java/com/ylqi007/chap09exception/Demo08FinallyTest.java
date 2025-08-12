package com.ylqi007.chap09exception;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 1. finally 是可选的
 * 2. finally 中声明的是一定会被执行的代码，即是 catch 中又出现异常，try or catch 中有 return 语句
 * 3. 对于物理连接，比如数据库连接、输入输出流、Socket连接等，JVM是不能自动回收的，都是必须要手动的执行资源释放操作。
 *      此时，资源释放就要声明在 finally 中。
 * 4. try-catch-finally 可以相互嵌套
 */
public class Demo08FinallyTest {

    @Test
    public void test01() {
        try {
            int a = 10;
            int b = 0;
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("我好帅啊啊啊🤔");
        }
    }

    @Test
    public void test02() {
        try {
            int a = 10;
            int b = 0;
            System.out.println(a / b);
        } catch (ArithmeticException e) {
            int[] arr = new int[10];
            System.out.println(arr[10]);    // java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 10
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("我好帅啊啊啊🤔");
        }
    }

    @Test
    public void test03() {
        int num = testReturn01();
        System.out.println(num);
    }

    public int testReturn01() {
        try {
            int[] arr = new int[10];
            System.out.println(arr[10]);
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 2;
        } finally {
            System.out.println("我一定会被执行");
        }
    }

    public int testReturn02() {
        try {
            int[] arr = new int[10];
            System.out.println(arr[10]);
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 2;
        } finally {
            System.out.println("我一定会被执行");
            return -1;
        }
    }

    @Test
    public void testReleaseFileInputStream() {
        FileInputStream fis = null;
        try {
            File file = new File("./test.txt");
            fis = new FileInputStream(file);

            int data = fis.read();
            while (data != -1) {
                System.out.print((char) data);
                data = fis.read();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } finally {
            try {
//                assert fis != null;
//                fis.close();
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
