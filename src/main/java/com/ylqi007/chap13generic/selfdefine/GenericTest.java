package com.ylqi007.chap13generic.selfdefine;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author: ylqi007
 * @Create: 3/24/24 09:00
 */
public class GenericTest {
    @Test
    public void test1() {
        List list = new ArrayList();    // 向下兼容，在JDK5.0之前，集合是没有声明为范型的
        list.add(123);
        list.add("abc");
    }

    @Test
    public void test2() {
        // 实例化时，可以指定类的范型参数的类型
        Order order = new Order();  // ⚠️注意: 不等价于 Order<Object> order = new Order<>();
        Object t = order.getT();

        // 范型参数在指明时，T必须是引用数据类型，不能是基本数据类型。但可以使用包装类
        // 在实例化时，可以指明类的范型参数的具体类型。一旦指明了范型的类型，则在范型类中凡是使用范型参数的位置，都替换为执行的类型。
        Order<Integer> order1 = new Order<>();
        Integer t1 = order1.getT();

        Order<String> order2 = new Order<>();
        String t2 = order2.getT();
    }

    // 测试Order的子类
    @Test
    public void test3() {
        // 实例化SubOrder1
        SubOrder1 subOrder1 = new SubOrder1();
        Object t = subOrder1.getT(); // 因为并不是范型类，即未指定范型类，则默认为Object类

        // 编译错误，因为SubOrder1并不是范型类
        // SubOrder1<Integer> subOrder2 = new SubOrder1<>();   // java: type com.atguigu.chap13generic.selfdefine.SubOrder1 does not take parameters
    }

    @Test
    public void test4() {
        SubOrder2 subOrder = new SubOrder2();
        Integer t = subOrder.getT();
    }

    @Test
    public void test5() {
        SubOrder3<String> subOrder = new SubOrder3<>();
        subOrder.show("abc");
    }

    @Test
    public void test6() {
        SubOrder4<String> subOrder = new SubOrder4<>();
        Integer t = subOrder.getT();
        String e = subOrder.getE();
    }

    @Test
    public void test7() {
        SubOrder5<String, Integer> suborder = new SubOrder5<>();
        String t = suborder.getT();
        Integer e = suborder.getE();
    }

    // 测试范型方法的使用
    @Test
    public void test8() {
        Order<String> order = new Order<>();

        Integer[] arr = new Integer[]{1, 2, 3};
        ArrayList<Integer> integers = order.copyFromArrayToList(arr);
        for(Integer integer: integers) {
            System.out.println(integer);
        }
    }
}
