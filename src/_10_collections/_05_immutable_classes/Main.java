package _10_collections._05_immutable_classes;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    public static void main(String[] args) {
        locations.put(1, new Location(1, "this is a description", null));
    }
}
