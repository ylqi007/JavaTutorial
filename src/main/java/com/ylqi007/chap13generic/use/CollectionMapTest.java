package com.ylqi007.chap13generic.use;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description: 范型在集合中的使用
 *  List<String>
 *  Map<String, Integer>
 *  Set<Map.Entry<String, Integer>>: Entry是Map的内部类，因此引用时要用Map.Entry
 *
 * @Author: ylqi007
 * @Create: 3/23/24 22:49
 */
public class CollectionMapTest {
    // 未在集合中使用范型前存在的问题
    // 问题1: 类型不安全
    // 问题2: 需要强制类型转换
    @Test
    public void test1() {
        List list = new ArrayList();
        list.add(67);
        // list.add("a");  // 问题1: 类型不安全。因为add()的参数是Object类型，意味着任何类型的对象都可以添加成功。

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = (Integer)iterator.next(); // 问题2: 需要强制类型转换。繁琐，还可能导致ClassCastException
            int score = next;
            System.out.println(score);
        }
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        // list.add("a");  // 编译报错，保证类型的安全: java: incompatible types: java.lang.String cannot be converted to java.lang.Integer

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) { // Hints: 'while' loop can be replaced with enhanced 'for'
            Integer next = iterator.next(); // 因为添加的都是Integer类型，避免了强制类型转换
            System.out.println(next);
        }
    }

    @Test
    public void test3() {
        // Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> map = new HashMap<>(); // 类型推断, Type Inference, since JDK7
        map.put("Tom", 27);
        map.put("Jerry", 28);

        // Set<Map.Entry<String, Integer>> entries = map.entrySet();   // Entry是Map的内部类，因此要用Map.Entry; Entry本身也接受范型
        // Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();

        var entries = map.entrySet();   // var, since JDK10
        var iterator = entries.iterator();
        while(iterator.hasNext()) {
            // Map.Entry<String, Integer> entry = iterator.next();
            Map.Entry<String, Integer> next = iterator.next();
            System.out.println(next.getKey() + "-->" + next.getValue());
        }
    }
}
