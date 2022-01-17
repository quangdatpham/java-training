package _13_lambda_expressions._02_scope;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee jack = new Employee("Jack Hill", 40);
        Employee john = new Employee("John Doe", 30);
        Employee snow = new Employee("Snow White", 22);
        Employee tim = new Employee("Tim Buchalka", 21);

        List<Employee> employees = new ArrayList<>();
        employees.add(jack);
        employees.add(john);
        employees.add(snow);
        employees.add(tim);

        // Employee employee;
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            new Thread(() -> System.out.println(employee.getName())).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(employee.getName());
                }
            }).start();
        }

        employees.forEach(employee -> {
            System.out.println(employee.getName());
        });
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
