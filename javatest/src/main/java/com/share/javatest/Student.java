package com.share.javatest;

/**
 * Created by jack on 17/2/28
 */

public class Student {
    private int id;
    private String name;
    private int age;
    private String classes;

    public Student(Builder b) {
        id = b.id;
        age = b.age;
        name = b.name;
        classes = b.classes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getClasses() {
        return classes;
    }

    public static class Builder {
        private int id = 0;
        private String name = null;
        private int age = 0;
        private String classes = null;

        public Builder() {
        }

        public Builder ids(int id) {
            this.id = id;
            return this;
        }

        public Builder names(String name) {
            this.name = name;
            return this;
        }

        public Builder ages(int age) {
            this.age = age;
            return this;
        }

        public Builder classess(String classes) {
            this.classes = classes;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}