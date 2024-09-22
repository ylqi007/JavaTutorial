package com.ylqi007.grammar;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Since JDK7
 */
public class TryCatchTest {
    /*
     * 举例1：
     * JDK7之前的写法
     * */
    @Test
    public void test01() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("test.txt");
            bw = new BufferedWriter(fw);

            bw.write("hello,世界");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 举例1: JDK7中的写法
     * BufferedWriter实现了AutoClosable接口
     * */
    @Test
    public void test2() {
        try (FileWriter fileWriter = new FileWriter("test.txt"); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
            bufferedWriter.write("Hello, 世界123!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 举例2：从test.txt(utf-8)文件中，读取内容，写出到abc.txt(gbk)文件中
     * JDK7之前的写法
     * */
    @Test
    public void test3() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("test.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            FileOutputStream fileOutputStream = new FileOutputStream("gkb.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gbk");
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 举例2：从test.txt(utf-8)文件中，读取内容，写出到abc.txt(gbk)文件中
     * JDK7中的写法
     * */
    @Test
    public void test4() {
        try (FileInputStream fileInputStream = new FileInputStream("test.txt"); InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8"); BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             FileOutputStream fileOutputStream = new FileOutputStream("gkb.txt"); OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gbk"); BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Since JDK9
     */
    @Test
    public void test5() {
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try (reader; writer) {
            // reader、writer是final的，不可再被赋值
            // reader = new InputStreamReader(System.in);  // Variable used as a try-with-resources resource should be final or effectively final
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
