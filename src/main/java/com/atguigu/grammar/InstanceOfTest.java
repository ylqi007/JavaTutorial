package com.atguigu.grammar;

import org.junit.jupiter.api.Test;

public class InstanceOfTest {

    /*
     * 举例1：
     * JDK14之前
     * */
    @Test
    public void test1(){
        Object obj = new String("hello,Java14");

        if(obj instanceof String){
            String str = (String) obj;
            System.out.println(str.contains("Java"));
        }else{
            System.out.println("非String类型");
        }
    }

    /*
     * 举例1：
     * JDK14中
     * */
    @Test
    public void test2(){
        Object obj = new String("hello,Java14");

        if(obj instanceof String str){
            System.out.println(str.contains("Java"));
        }else{
            System.out.println("非String类型");
        }
    }
}


/*
 * 举例2
 * */
class Computer{
    private String model; //型号
    private double price;//价格

    //方式1：
//    public boolean equals(Object o){
//        if(o instanceof Computer){
//            Computer other = (Computer) o;
//            if(this.model.equals(other.model) && this.price == other.price){
//                return true;
//            }
//        }
//        return false;
//    }

    //方式2：
//    public boolean equals(Object o){
//        if(o instanceof Computer other){
//            return this.model.equals(other.model) && this.price == other.price
//        }
//        return false;
//    }

    public boolean equals(Object o){
        return o instanceof Computer other && this.model.equals(other.model)
                && this.price == other.price;
    }
}