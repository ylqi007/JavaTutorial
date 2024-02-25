package com.atguigu.collection.list.exer2;

import java.util.ArrayList;
import java.util.Collection;

public class ListTest {
    public static void main(String[] args) {
        // 需求1: 随机生成30个字符，存放在ArrayList中
        ArrayList list = new ArrayList();
        for(int i=0; i<30; i++) {
            String s = "" + (char) ('a' + (int)(Math.random() * (122 - 97 + 1)));
            list.add(s);
        }
        System.out.println(list);

        // 需求2: 遍历ArrayList，查找制定元素出现的次数
        int aCount = count(list, "a");
        int bCount = count(list, "b");
        int cCount = count(list, "c");
        int xCount = count(list, "x");

        System.out.println("aCount = " + aCount);
        System.out.println("bCount = " + bCount);
        System.out.println("cCount = " + cCount);
        System.out.println("xCount = " + xCount);
    }

    public static int count(Collection list, String s) {
        int count = 0;
        for(Object obj: list) {
            if(s.equals(obj)) {
                count++;
            }
        }
        return count;
    }
}
