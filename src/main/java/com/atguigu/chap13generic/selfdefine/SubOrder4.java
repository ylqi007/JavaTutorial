package com.atguigu.chap13generic.selfdefine;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: SubOrder4是一个范型类。
 *  在确定了Order类中T的类型之后，又在子类SubOrder中加入了额外的不确定性。
 *
 * @Author: ylqi007
 * @Create: 3/24/24 09:21
 */
@Getter
@Setter
public class SubOrder4<E> extends Order<Integer> {
    E e;

    public SubOrder4() {}

    public SubOrder4(E e) {
        this.e = e;
    }

    // 父类Order中的T已经定义为Integer
    public SubOrder4(Integer integer, int orderId, E e) {
        super(integer, orderId);
        this.e = e;
    }
}
