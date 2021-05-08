package _05_oop._09_abstract_classes_challenge;

public class Node extends Item {

    public Node(Object value) {
        super(value);
    }

    @Override
    Item next() {
        return this.rightItem;
    }

    @Override
    boolean hasNext() {
        return this.rightItem != null;
    }

    @Override
    Item setNext(Item item) {
        this.rightItem = item;
        return this.rightItem;
    }

    @Override
    Item previous() {
        return this.leftItem;
    }

    @Override
    boolean hasPrevious() {
        return this.leftItem != null;
    }

    @Override
    Item setPrevious(Item item) {
        this.leftItem = item;
        return this.leftItem;
    }

    @Override
    int compareTo(Item item) {
        if (item != null) {
            return ((String) this.getValue()).compareTo((String) item.getValue());
        }

        return -1;
    }

}
