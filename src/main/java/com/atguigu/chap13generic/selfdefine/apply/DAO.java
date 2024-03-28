package com.atguigu.chap13generic.selfdefine.apply;

import java.util.List;

/**
 * Description:
 *  DAO: Data(base) Access Object, 内部封装了操作数据库相关表的增删改查操作
 * @Author: ylqi007
 * @Create: 3/24/24 09:47
 */
public class DAO<T> {
    // 增
    public void insert(T bean) {
        // 通过相应的SQL语句，将bean对象的属性值插入到对应的table中
    }

    // 删
    public T deleteById(int id) {
        return null;
    }

    // 改
    public void update(int id) {
        // 略
    }

    // 查
    public List<T> queryForList(int id) {
        return null;
    }

    // 定义范型方法
    // 比如: 查询表中的记录数，(E: Long类型)
    // 比如: 查询表中最大的生日, (E: Date类型)
    public <E> E getValue(String sql) {
        return null;
    }
}
