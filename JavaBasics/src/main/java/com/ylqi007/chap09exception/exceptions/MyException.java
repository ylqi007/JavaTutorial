package com.ylqi007.chap09exception.exceptions;

/**
 * extends RuntimeException (运行时, 可以不用显式处理)
 * extends Exception (编译时)
 *
 * 1. 继承于现有的异常结构：RuntimeException, Exception
 * 2. 提供全局变量，serialVersionUID
 * 3. 提供重载的构造器
 */
public class MyException extends RuntimeException {

    // 序列号，序列化中会用到
    @java.io.Serial
    static final long serialVersionUID = -7034897190745766939L; // 唯一标识这个异常类

    public MyException() {}

    public MyException(String message) {
        super(message);
    }


}
