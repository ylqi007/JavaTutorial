package com.ylqi007.collection.list.exer1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList list = new ArrayList<>();
        System.out.println("请录入学生信息");
        while(true) {
            System.out.println("0: 继续录入; 1.结束录入");
            int selection = scanner.nextInt();

            if(selection == 1) {
                break;
            }

            System.out.println("请录入学生姓名");
            String name = scanner.next();
            System.out.println("请录入学生年龄");
            int age = scanner.nextInt();

            Student student = new Student(name, age);
            list.add(student);
        }

        // 遍历学生信息
        System.out.println("遍历学生信息");
        for(Object s: list) {
            System.out.println(s);
        }

        scanner.close();
    }
}
