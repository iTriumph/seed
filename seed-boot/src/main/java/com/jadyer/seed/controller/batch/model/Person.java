package com.jadyer.seed.controller.batch.model;

import javax.validation.constraints.Size;

public class Person {
    @Size(min = 2, max = 3)
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}