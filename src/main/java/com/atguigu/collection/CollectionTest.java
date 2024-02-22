package com.atguigu.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionTest {

    /**
     * 添加
     * 1. boolean add(E e); 添加元素对象到当前集合中
     * 2. boolean addAll(Collection<? extends E> c); 将集合c中的所有元素对象添加到当前集合中
     */
    @Test
    public void test() {
        Collection collection = new ArrayList();

        // add()
        collection.add("AAA");
        collection.add(123);    // 自动装箱
        collection.add("String");
        collection.add(new Object());
        collection.add(new Person("Tome", 12));
        System.out.println(collection);

        Collection collection1 = new ArrayList();
        collection1.add(456);
        collection1.addAll(collection);
        System.out.println(collection1);
        System.out.println(collection1.size());

        collection1.add(collection);
        System.out.println(collection1);
    }

    /**
     * 1. boolean isEmpty();
     * 2. boolean contains(Object o);
     * 3. boolean contains(Object o);
     * 4. boolean containsAll(Collection<?> c);
     */
    @Test
    public void test2() {
        Collection collection = new ArrayList();
        collection.add("AAA");
        collection.add(123);    // 自动装箱
        collection.add(128);
        collection.add("String");
        collection.add(new Person("Tome", 12));

        System.out.println(collection.isEmpty());

        // boolean contains(Object o);
        System.out.println(collection.contains("AAA"));
        System.out.println(collection.contains(123));
        System.out.println(collection.contains(128));
        System.out.println(collection.contains(new Person("Tome", 12)));    // 重写equals方法

        // boolean containsAll(Collection<?> c);
        Collection collection1 = new ArrayList();
        collection1.add("AA");
        collection1.add("BB");
        System.out.println(collection.containsAll(collection));
    }

    @Test
    public void test3() {
        Collection collection = new ArrayList();
        collection.add("AAA");
        collection.add("AAA");
        collection.add(123);    // 自动装箱
        collection.add(128);
        collection.add("String");
        collection.add(new Person("Tome", 12));
        System.out.println(collection);
        //collection.clear();
        //System.out.println(collection.size());

        // public E remove(int index)
        collection.remove(new Person("Tome", 12));
        collection.remove("AAA");
        System.out.println(collection);

        // boolean removeAll(Collection<?> c);

        // boolean retainAll(Collection<?> c);
    }

    @Test
    public void test4() {
        Collection collection = new ArrayList();
        collection.add("AAA");
        collection.add("AAA");
        collection.add(123);    // 自动装箱
        collection.add(128);
        collection.add("String");
        collection.add(new Person("Tome", 12));
        System.out.println(collection);

        // Object[] toArray();  集合-->数组
        Object[] array = collection.toArray();
        System.out.println(Arrays.toString(array));

        // <T> T[] toArray(T[] a);  范型未讲，暂时skip

        // int hashCode();

        // Iterator<E> iterator(); 返回迭代对象，用于集合遍历

    }

    @Test
    public void test5() {
        String[] arr = new String[]{"AA", "BB", "CCC"};
        Collection list = Arrays.asList(arr);
        System.out.println(list);

        List<String> list1 = Arrays.asList("AAA", "BBB");
        System.out.println(list1);
    }

    @Test
    public void test6() {
        Integer[] arr = new Integer[]{1, 2, 3};
        List list = Arrays.asList(arr); // Integer 1, 2, 3被自动装箱，所以是三个元素
        System.out.println(list.size());    // 3
        System.out.println(list);

        int[] arr1 = new int[]{1, 2, 3};
        List list1 = Arrays.asList(arr1);   // int 1,2,3是基本元素，List中只能方Object，因此arr1被视为一个Object
        System.out.println(list1.size());   // 1
        System.out.println(list1);
    }
}

