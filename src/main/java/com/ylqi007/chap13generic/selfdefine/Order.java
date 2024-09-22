package com.ylqi007.chap13generic.selfdefine;

import java.util.ArrayList;

/**
 * Description: 自定义范型类
 *  声明了类的范型参数后，就可以在类的内部使用此"范型参数"
 * @Author: ylqi007
 * @Create: 3/24/24 09:03
 */
public class Order<T> {
    // static T t;
    T t;
    int orderId;

    public Order() {}

    public Order(T t, int orderId) {
        this.t = t;
        this.orderId = orderId;
    }

    public T getT() {
        return t;
    }

    // T的Setter/Getter并不是范型方法
    public void setT(T t) {
        this.t = t;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "t=" + t +
                ", orderId=" + orderId +
                '}';
    }

    // 不可以在static方法中使用类的范型
    // 范型参数是在实例化时确定的，在实例化之前是不确定的，
    public static void method1() {
        // System.out.println(t);  // java: non-static variable t cannot be referenced from a static context
    }

    // 自定义范型方法: <E>指明E是范型参数，而不是类
    // 通常会在返回值or形参列表的位置会出现范型参数E
    public <E> E method(E e) {
        return null;
    }

    // 实现范型方法，将E[]数组元素添加到对应类型的ArrayList中，并返回
    // 范型方法可以是static，因为会在调用时指明范型类
    public static <E> ArrayList<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for(E e: arr) {
            list.add(e);
        }
        return list;
    }
}
