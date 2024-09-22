package com.ylqi007.chap13generic.more;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: GenericTest
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 10:57
 * @Version 1.0
 */
public class GenericTest {
    @Test
    public void test1(){
        //1.
        Object obj = null;
        String str = "AA";

        obj = str;  //基于继承性的多态的使用

        //2.
        Object[] arr = null;
        String[] arr1 = null;

        arr = arr1;//基于继承性的多态的使用
    }

    /*
    * 类SuperA是类A的父类，则G<SuperA>与G<A>的关系: G<SuperA>和G<A>是并列的两个类，没有任何子父类的关系。
    * 下面例子中的ArrayList<Object>和ArrayList<String>是两个不同的类，是并列关系，并没有子父类关系。
    */
    @Test
    public void test2(){
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();

        // list1 = list2;  // java: incompatible types: java.util.ArrayList<java.lang.String> cannot be converted to java.util.ArrayList<java.lang.Object>

        /*
        * 反证法：
        * 假设list1 = list2是可以的。
        * list2.add("AA");
        * list1.add(123);
        *
        * String str = list2.get(1); //相当于取出的123赋值给了str，错误的。
        */

        method1(list1);
        // method1(list2);//错误的，java: incompatible types: java.util.ArrayList<java.lang.String> cannot be converted to java.util.ArrayList<java.lang.Object>
    }

    private void method1(ArrayList<Object> list){

    }

    @Test
    public void test3(){
        Person<Object> per = null;
        Person<String> per1 = null;
        // per = per1; // java: incompatible types: com.atguigu.chap13generic.more.Person<java.lang.String> cannot be converted to com.atguigu.chap13generic.more.Person<java.lang.Object>
    }

    /*
    * 类SuperA是类A的父类或接口，SuperA<G>与A<G>的关系: SuperA<G>与A<G>有继承或实现的关系。
    *  即A<G>的实例可以赋值给SuperA<G>类型的引用（或变量）
    * 下面例子中，ArrayList<String>和List<String>之间存在继承关系。
    */
    @Test
    public void test4(){
        List<String> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();
        list1 = list2;
        list1.add("AA");
        method2(list2);
    }

    private void method2(List<String> list){

    }
}
