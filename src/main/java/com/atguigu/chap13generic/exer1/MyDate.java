package com.atguigu.chap13generic.exer1;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName: MyDate
 * Description:
 * MyDate类包含:
 * private成员变量year,month,day；并为每一个属性定义 getter, setter 方法；
 *
 * @Author 尚硅谷-宋红康
 * @Create 17:04
 * @Version 1.0
 */
@Setter
@Getter
public class MyDate implements Comparable<MyDate>{
    private int year;
    private int month;
    private int day;

    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "年" + month + "月" + day + "日";
    }

    //按照生日从小到大排列
    @Override
    public int compareTo(MyDate o) {
        int yearDistince = this.getYear() - o.getYear();
        if(yearDistince != 0){
            return yearDistince;
        }

        int monthDistince = this.getMonth() - o.getMonth();
        if(monthDistince != 0){
            return monthDistince;
        }

        return this.getDay() - o.getDay();
    }
}
