package _05_oop._09_abstract_classes_challenge;

public class Main {

    public static void main(String[] args) {
        // NodeList list = new MyLinkedList(null);
        // list.traverse(list.getRoot());
        //
        // String stringData = "1 2 3 4 5 6 7 8 9 11";
        // String[] data = stringData.split(" ");
        // for (String s : data) {
        //     list.addItem(new Node(s));
        // }
        //
        // list.removeItem(new Node("2"));
        // list.traverse(list.getRoot());

        // ======================================================
        // SearchTree
        // ======================================================

        SearchTree tree = new SearchTree(null);
        tree.traverse(tree.getRoot());
        String stringData = "6 2 7 2 8 4 3 1 7 0";

        String[] data = stringData.split(" ");
        for (String s : data) {
            tree.addItem(new Node(s));
        }

        tree.traverse(tree.getRoot());
        tree.removeItem(new Node("3"));
        tree.traverse(tree.getRoot());

        tree.removeItem(tree.getRoot());
        tree.traverse(tree.getRoot());
    }

}
