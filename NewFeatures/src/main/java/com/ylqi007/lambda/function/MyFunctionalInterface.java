package com.ylqi007.lambda.function;

/**
 * Annotation `@FunctionalInterface`只是起到检查的作用。
 * 没有这个注解，只有一个抽象方法时，它也是函数式接口
 */
@FunctionalInterface
public interface MyFunctionalInterface {
    void run();

    //void run2();
}
