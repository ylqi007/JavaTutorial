package com.ylqi007.oop3._static;

public class ChineseTest {

    public static void main(String[] args) {
        Chinese c1 = new Chinese();
        c1.name = "Yao";
        c1.age = 10;
        c1.nation = "China";

        Chinese c2 = new Chinese();
        c2.name = "Liu";
        c2.age = 11;

        System.out.println(c1.nation);
        System.out.println(c2.nation);
    }
}


class Chinese {
    static String nation; // 静态变量, 类变量
    String name;    // 非静态变量, 实例变量
    int age;        // 非静态变量, 实例变量

    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}