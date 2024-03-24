package com.atguigu.chap13generic.selfdefine;

/**
 * Description: SubOrder3是范型类
 *  注意: SubOrder3<T>后面要有<T>
 * @Author: ylqi007
 * @Create: 3/24/24 09:18
 */
public class SubOrder3<T> extends Order<T>{

    public void show(T t) {
        System.out.println(t);
    }
}
