package com.ylqi007.chap09exception;

import com.ylqi007.chap09exception.exceptions.MyException;

public class Demo06ManuallyThrowExceptionTest {
    public static void main(String[] args) {
        try {
            Student student = new Student();
            student.register(-1);
            System.out.println(student);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


class Student {
    int id;

    public void register(int id) throws Exception {
        if(id > 0) {
            this.id = id;
        } else {
            // System.out.println("Invalid id");
            // 手动抛出异常
            // throw new RuntimeException("Invalid id:" + id); // 运行时异常

            // throw new Exception("Invalid id:" + id);

            throw new MyException("不能输入负数ID: Invalid Id=" + id);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                '}';
    }
}