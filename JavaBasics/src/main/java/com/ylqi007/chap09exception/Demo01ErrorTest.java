package com.ylqi007.chap09exception;

/**
 * Exception in thread "main" java.lang.StackOverflowError
 * 	at com.ylqi007.chap07exception.ErrorTest.main(ErrorTest.java:5)
 */
public class Demo01ErrorTest {
    public static void main(String[] args) {
        // 1. 栈溢出 java.lang.StackOverflowError
        // main(args);


        /*
        数组长度 = 1024 × 1024 × 1024 = 2^30 = 1,073,741,824
        2^10 Byte ==> 1KB
        2^20 Byte ==> 1MB
        2^30 Byte ==> 1GB
        每个引用大小 = 4 字节
        总引用大小 = 1,073,741,824 × 4 = 4,294,967,296 字节 ≈ 4 GB
        数组对象头 + 对齐：约 16 字节（可以忽略不计相对于4G）
         */
        // 2. 堆溢出 java.lang.OutOfMemoryError (OOM)
        Integer[] arr = new Integer[1024 * 1024 * 1024];
    }
}
