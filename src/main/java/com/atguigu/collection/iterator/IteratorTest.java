package com.atguigu.collection.iterator;

import com.atguigu.collection.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorTest {

    @Test
    public void test1() {
        Collection collection = getData();
        Iterator iterator = collection.iterator();
        System.out.println(iterator.getClass());

        // 方式1
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());
        //System.out.println(iterator.next());    // 超出length，java.util.NoSuchElementException

        // 方式2
        //for(int i=0; i<collection.size(); i++) {
        //    System.out.println(iterator.next());
        //}

        // 方式3 -- prefer
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2() {
        Collection collection = getData();

        // ❌错误
        //Iterator iterator = collection.iterator();
        //while((iterator.next()) != null) {
        //    System.out.println(iterator.next());
        //}

        // ❌错误：死循环
        // 每次调用iterator()，都会返回一个新的Iterator对象
        while(collection.iterator().hasNext()) {
            System.out.println(collection.iterator().next());
        }
    }

    /**
     * 增强的for-each循环：遍历数组和集合
     * 1. 对集合而言，底层使用的依然是iterator
     * 2. 增强for循环的执行过程中，是将集合或数组中的元素依次赋值给临时变量。
     *  ⚠️注意：循环体中对临时变量的修改，可能不会导致原有集合或数组中的元素的修改。
     */
    @Test
    public void test3() {
        Collection collection = getData();

        for(Object obj: collection) {
            System.out.println(obj);
        }

        int[] arr = new int[]{1, 33, 4};
        for(int i: arr) {   //
            System.out.println(i);
        }
    }

    private Collection getData() {
        Collection collection = new ArrayList();
        collection.add("AAA");
        collection.add("AAA");
        collection.add(123);    // 自动装箱
        collection.add(128);
        collection.add("String");
        collection.add(new Person("Tome", 12));
        System.out.println(collection);
        return collection;
    }

}
