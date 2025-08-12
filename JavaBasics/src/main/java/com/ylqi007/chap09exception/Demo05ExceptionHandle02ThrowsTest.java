package com.ylqi007.chap09exception;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 异常处理机制2: throws + 异常类型
 *  1. "throws + 异常类型" 写在方法的声明处，指明此方法执行时，可能会抛出的异常类型。
 *      一旦方法体执行时，出现异常，仍会在异常代码处生成一个异常类型对象，此对象满足 throws 后异常类型时，就会被抛出。
 *      异常代码后续的代码，就不再执行！
 *
 *  2. 体会
 *      1. try-catch-finally: 真正将异常处理掉了。
 *      2. throws: 只是将异常抛给方法的调用者，并未真正将异常处理掉。
 *
 * 3. 开发中如何选择？
 *  3.1 如果父类中被重写的方法没有 throws 方法处理异常，则子类重写的方法也不能使用 throws。
 *      意味着，如果子类重写的方法中有异常，必须使用 try-catch-finally 方式处理。
 *  3.2 执行的方法A中，先后又调用了另外几个方法，这几个方法时递进关系。
 *      我们建议，这几个方法使用 throws 的方式处理。而执行方法A时，可以考虑使用 try-catch-finally 方式进行处理。
 */
public class Demo05ExceptionHandle02ThrowsTest {

    public static void main(String[] args) {
        // 在 main() 中再抛，抛给 JVM，JVM 容易挂
//        try {
//            method2();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        method3();
    }

    public static void method3() {
        try {
            method2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void method2() throws IOException {
        testReleaseFileInputStream();
    }

    public static void testReleaseFileInputStream() throws FileNotFoundException, IOException {
        File file = new File("./test1.txt");
        FileInputStream fis = new FileInputStream(file);    // FileNotFoundException 是 IOException 的子类

        int data = fis.read();
        while (data != -1) {
            System.out.print((char) data);
            data = fis.read();
        }

        System.out.println("hahahahahahahahah");
    }
}
