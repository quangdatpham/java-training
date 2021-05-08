package _09_packages._06_static_initializers;

public class SIBTest {
    public static final String owner;

    static {
        owner = "tim";
        System.out.println("1. SIBTest static initialization block called");
    }

    static {
        System.out.println("2. 2nd initialization block called");
    }

    public SIBTest() {
        System.out.println("3. SIB constructor called");
    }

    public void someMethod() {
        System.out.println("4. someMethod called");
    }
}
