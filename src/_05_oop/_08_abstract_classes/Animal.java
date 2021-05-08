package _05_oop._08_abstract_classes;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void eat();

    public abstract void breathe();

    public String getName() {
        return name;
    }
}
