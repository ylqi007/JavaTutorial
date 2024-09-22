package com.ylqi007.chap13generic.selfdefine;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: SubOrder5是范型类
 *
 * @Author: ylqi007
 * @Create: 3/24/24 09:25
 */
@Getter
@Setter
public class SubOrder5<T, E> extends Order<T> {
    E e;

    public SubOrder5() {
    }

    // 此时，构造器中有两个范型类型: T 和 E
    public SubOrder5(T t, int orderId, E e) {
        super(t, orderId);
        this.e = e;
    }
}
