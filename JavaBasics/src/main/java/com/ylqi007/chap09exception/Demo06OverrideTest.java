package com.ylqi007.chap09exception;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 方法重写的规则之一：
 *  子类重写的方法抛出的异常类型，不大于父类被重写的方法抛出的异常类型。
 *  如果父类没有抛异常，则子类重写方法时，也不能抛异常。
 *
 *
 */
public class Demo05OverrideTest {

    public static void main(String[] args) {
        Demo05OverrideTest demo05OverrideTest = new Demo05OverrideTest();
        demo05OverrideTest.display(new SubClass());
    }

    public void display(SuperClass s) {
        try {
            s.method();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class SuperClass {
    public void method() throws IOException {
        System.out.println("SuperClass.method()");
    }
}

class SubClass extends SuperClass {
    @Override
    public void method() throws FileNotFoundException {
        System.out.println("SubClass.method()");
    }
}
