package com.share.javatest;

public class JavaTest {

    public static void main(String[] args) {
        Student student = new Student.Builder().ids(10)
                .ages(21)
                .names("Cyg")
                .classess("软功131").build();
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getClasses());
    }
}