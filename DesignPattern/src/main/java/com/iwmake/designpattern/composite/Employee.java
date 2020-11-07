package com.iwmake.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建Employee，该类带有Employee对象列表
 * @author Dylan
 * @since 2020-11-07
 */
public class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    // 构造函数

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        subordinates = new ArrayList<>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public String toString() {
        return "Employee[" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ']';
    }
}
