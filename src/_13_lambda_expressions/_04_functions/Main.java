package _13_lambda_expressions._04_functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

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

        Function<Employee, String> getLastName = (Employee employee) -> {
            return employee.getName().substring(employee.getName().indexOf(' ') + 1);
        };

        Function<Employee, String> getFirstName = (Employee employee) -> {
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        Function<String, String> upperCase = str -> str.toUpperCase();

        // String lastName = getLastName.apply(employees.get(2));
        // System.out.println(lastName);

        System.out.println(getAName(getFirstName, employees.get(2)));
        System.out.println(getAName(getFirstName.andThen(upperCase), employees.get(2)));
        System.out.println(getAName(upperCase.compose(getFirstName), employees.get(2)));

        IntUnaryOperator incBy5 = i -> i + 5;
        System.out.println(incBy5.applyAsInt(10));

        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("Hello, World!");
    }

    private static String getAName(Function<Employee, String> getName, Employee employee) {
        return getName.apply(employee);
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
