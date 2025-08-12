package com.ylqi007.chap09exception;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 1. 使用 try 将可能出现异常的代码包装起来，在执行过程中，一旦出现异常，就会生成一个对应异常类的对象，根据此对象的类型，去 catch 中进行匹配。
 * 2. 一旦 try 中的异常对象匹配到某一个 catch 时，就进入 catch 中进行异常的处理。一旦处理完成，就跳出当前的 try-catch 结构 (在没有写 finally 的情况下)。继续处理其后的代码。
 * 3. 多个 catch 时，如果异常类型没有父子类关系，则谁声明在上、在下，都无所谓。
 *    catch 中的异常类型如果满足父子关系，则要求子类声明一定在父类上面，否在，报错。
 * 4. 常用的异常对象处理方式
 *      1. e.getMessage()
 *      2. e.printStackTrace()
 * 5. 在 try 结构中声明的变量，再出了 try 结构以后，就不能再被调用。
 *
 *
 * 体会：
 * 1. 使用 try-catch-finally 处理编译时异常，使得程序在编译时不再报错，但是运行时仍可能报错。
 *      相当于我们使用 try-catch-finally 将编译时可能出现的异常，延迟到运行时出现。
 *
 * 2. 开发中，由于运行时异常比较常见，所以通常不针对运行时异常编写 try-catch-finally
 *      针对编译时异常，我们一定要考虑异常的处理。
 */
public class Demo04ExceptionHandle01TryCatchFinallyTest {

    @Test
    public void test1() {
        String str = "123";
        str = "abc";
        try {
            int num = Integer.parseInt(str);    // 生成一个异常类对象，即 NumberFormatException 对象
            System.out.println("===== Hello 1");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("===== Hello 2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===== Hello 3");
    }

    @Test
    public void test02() {
        File file = new File("./test.txt");

        FileInputStream fis = null;
        try {
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
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
