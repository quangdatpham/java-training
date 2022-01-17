package _13_lambda_expressions._01_lambda_expressions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        new Thread(new CodeToRun()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing from Runnable");
            }
        }).start();

        new Thread(() -> {
            System.out.println("Printing from a anonymous functional expression");
        }).start();

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        // Collections.sort(employees, new Comparator<Employee>() {
        //     @Override
        //     public int compare(Employee employee1, Employee employee2) {
        //         employee1.getName().compareTo(employee2.getName());
        //     }
        // });

        Collections.sort(employees, (employee1, employee2) ->
                employee1.getName().compareTo(employee2.getName()));

        for (Employee employee : employees) {
            System.out.println(employee.getName());
        }

        UpperConcat uc = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase();
        System.out.println(doStringStuff(uc, "John", "Tim"));
    }

    public static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

class CodeToRun implements Runnable {
    @Override
    public void run() {
        System.out.println("Printing from CodeToRun");
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

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}
