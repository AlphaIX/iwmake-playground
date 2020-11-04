package com.iwmake.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan
 * @since 2020-11-04
 */
public class Demeter01 {
    // 客户端
    public static void main(String[] args) {
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }

    // 学校总部员工类
    static class Employee {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    // 学院员工类
    static class CollegeEmployee {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    // 管理学院员工 管理类
    static class CollegeManager {
        public List<CollegeEmployee> getAllEmployee() {
            List<CollegeEmployee> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                CollegeEmployee emp = new CollegeEmployee();
                emp.setId("学院员工id=" + i);
                list.add(emp);
            }
            return list;
        }
    }


    /**
     * 学校管理类
     * 分析：直接朋友==>Employee CollegeManager
     * 不是直接朋友==>CollegeEmployee陌生类 违反迪米特法则
     */
    static class SchoolManager {
        public List<Employee> getAllEmployee() {
            List<Employee> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Employee emp = new Employee();
                emp.setId("学校总部员工id=" + i);
                list.add(emp);
            }
            return list;
        }

        /**
         * CollegeEmployee 以局部变量的方式出现在SchoolManager违反迪米特法则
         * @param sub
         */
        public void printAllEmployee(CollegeManager sub) {
            List<CollegeEmployee> list1 = sub.getAllEmployee();
            System.out.println("------------学院员工-----------");
            for (CollegeEmployee e : list1) {
                System.out.println(e.getId());
            }

            List<Employee> list2 = this.getAllEmployee();
            System.out.println("------------总部员工-----------");
            for (Employee e : list2) {
                System.out.println(e.getId());
            }
        }
    }
}
