package _05_oop._09_abstract_classes_challenge;

public abstract class Item {
    protected Item leftItem = null;
    protected Item rightItem = null;
    protected Object value;

    public Item(Object value) {
        this.value = value;
    }

    abstract Item next();

    abstract boolean hasNext();

    abstract Item setNext(Item item);

    abstract Item previous();

    abstract boolean hasPrevious();

    abstract Item setPrevious(Item item);

    abstract int compareTo(Item item);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
