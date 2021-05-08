package _05_oop._09_abstract_classes_challenge;

public class MyLinkedList implements NodeList {

    private Item root = null;

    public MyLinkedList(Item root) {
        this.root = root;
    }

    @Override
    public Item getRoot() {
        return this.root;
    }

    @Override
    public boolean addItem(Item newItem) {
        if (this.root == null) {
            this.root = newItem;
            return true;
        }

        Item currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(newItem);
            if (comparison < 0) {
                if (currentItem.hasNext()) {
                    currentItem = currentItem.next();
                } else {
                    currentItem.setNext(newItem).setPrevious(currentItem);
                    return true;
                }
            } else if (comparison > 0) {
                if (currentItem.hasPrevious()) {
                    currentItem.previous().setNext(newItem);
                    newItem.setPrevious(currentItem.previous());
                    newItem.setNext(currentItem);
                    currentItem.setPrevious(newItem);
                } else {
                    newItem.setNext(this.root);
                    this.root.setPrevious(newItem);
                    this.root = newItem;
                }
                return true;
            } else {
                System.out.println(newItem.getValue() + " has already been added!");
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean removeItem(Item item) {
        if (item != null) {
            System.out.println("Deleting item " + item.getValue());
        }

        Item currentItem = this.root;
        while (currentItem != null) {
            int comparison = currentItem.compareTo(item);
            if (comparison == 0) {
                if (currentItem == this.root) {
                    this.root = currentItem.next();
                    currentItem.setPrevious(null);
                } else {
                    currentItem.previous().setNext(currentItem.next());
                    if (currentItem.hasNext()) {
                        currentItem.next().setPrevious(currentItem.previous());
                    }
                }
                return true;
            } else if (comparison < 0) {
                currentItem = currentItem.next();
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public void traverse(Item root) {
        if (root == null) {
            System.out.println("The list is empty.");
        }

        while (root != null) {
            System.out.println("Value: " + root.getValue());
            root = root.next();
        }
    }
}
