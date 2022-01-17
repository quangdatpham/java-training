package _13_lambda_expressions._03_predicates_and_suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Employee snow = new Employee("Snow White", 22);
        Employee tim = new Employee("Tim Buchalka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee john = new Employee("John Doe", 30);

        List<Employee> employees = new ArrayList<>();
        employees.add(snow);
        employees.add(tim);
        employees.add(jack);
        employees.add(john);

        printEmployeeByAge(employees, "Employee over 30", employee -> employee.getAge() > 30);
        System.out.println();
        printEmployeeByAge(employees, "Employee younger than 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });

        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;

        System.out.println(greaterThan15.test(10));
        System.out.println(greaterThan15.and(lessThan100).test(44));

        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for (int i = 0; i < 10; i++) {
            // System.out.println(random.nextInt());
            System.out.println(randomSupplier.get());
        }
    }

    private static void printEmployeeByAge(List<Employee> employees,
                                           String ageText,
                                           Predicate<Employee> ageCondition) {
        System.out.println(ageText);
        for (Employee employee : employees)
            if (ageCondition.test(employee))
                System.out.println(employee.getName());
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
