package _05_oop._09_abstract_classes_challenge;

public interface NodeList {
    Item getRoot();

    boolean addItem(Item item);

    boolean removeItem(Item item);

    void traverse(Item root);
}
