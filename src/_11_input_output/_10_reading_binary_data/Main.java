package _11_input_output._10_reading_binary_data;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
        //     boolean eof = false;
        //     while (!eof) {
        //         try {
        //             Map<String, Integer> exits = new LinkedHashMap<>();
        //             int locID = locFile.readInt();
        //             String description = locFile.readUTF();
        //             int numExits = locFile.readInt();
        //             System.out.println("Reading location " + locID + " : " + description);
        //             System.out.println("Found " + numExits + " exits");
        //             for (int i = 0; i < numExits; i++) {
        //                 String direction = locFile.readUTF();
        //                 int destination = locFile.readInt();
        //                 exits.put(direction, destination);
        //             }
        //             locations.put(locID, new Location(locID, description, exits));
        //         } catch (EOFException e) {
        //             eof = true;
        //         }
        //     }
        // }

        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            while (locFile.available() > 0) {
                Map<String, Integer> exits = new LinkedHashMap<>();
                int locID = locFile.readInt();
                String description = locFile.readUTF();
                int numExits = locFile.readInt();
                System.out.println("Reading location " + locID + " : " + description);
                System.out.println("Found " + numExits + " exits");
                for (int i = 0; i < numExits; i++) {
                    String direction = locFile.readUTF();
                    int destination = locFile.readInt();
                    exits.put(direction, destination);
                }
                locations.put(locID, new Location(locID, description, exits));
            }
        }

        System.out.println("======================================================");

        for (Location location : locations.values()) {
            System.out.println(location);
        }
    }
}
