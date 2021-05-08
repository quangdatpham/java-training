package _09_packages._05_final_statement;

public class SomeClass {

    private static int classCounter = 0;
    // define
    public final int instanceNumber;
    private final String name;


    public SomeClass(String name) {
        SomeClass.classCounter++;
        // init
        this.name = name;
        this.instanceNumber = classCounter;
        System.out.println(name + " created, instance is " + this.instanceNumber);
    }

    public int getInstanceNumber() {
        return this.instanceNumber;
    }
}
