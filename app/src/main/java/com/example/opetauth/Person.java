package com.example.opetauth;

import java.util.Date;
import java.util.List;

public class Person {

    public String name;
    public int child;
    public double salary;
    public boolean active;
    public List<String> pets;
    public Date birthday;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", child=" + child +
                ", salary=" + salary +
                ", active=" + active +
                ", pets=" + pets +
                ", birthday=" + birthday +
                '}';
    }
}
