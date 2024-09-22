package com.ylqi007.reflection.example2;

public class User {
    private String name;
    public int age;

    public User(){
        // System.out.println("User()...");
    }

    public User(int age){
        this.age = age;
    }

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void show(){
        System.out.println("你好，我是一个User");
    }

    private String showNation(String nation){
        return "我的国籍是：" + nation;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
