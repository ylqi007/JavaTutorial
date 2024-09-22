package com.ylqi007.collection.list.exer1;

import java.util.Objects;

public class Student {
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // if (!(o instanceof Student student)) return false;
        // return Objects.equals(name, student.name) && Objects.equals(age, student.age);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
