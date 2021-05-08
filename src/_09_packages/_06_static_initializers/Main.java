package _09_packages._06_static_initializers;

public class Main {
    static {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        System.out.println("0. Main method called");
        System.out.println(SIBTest.owner);
        // SIBTest test = new SIBTest();
        // test.someMethod();
        // System.out.println("5. Owner is " + SIBTest.owner);
    }
}
