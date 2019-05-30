package com.example.core.domain;

import java.util.Comparator;

public class Person implements Comparable<Person>,Comparator<Person>{
    private String name;
    private Integer age;

    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public int compareTo(Person o) {
        return 0;
    }

    @Override
    public int compare(Person o1, Person o2) {
        return 0;
    }
}
