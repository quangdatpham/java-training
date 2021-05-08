package _11_input_output._06_file_reader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("locations.txt"));
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                System.out.println("Imported loc: " + loc + " description: " + description);
                locations.put(loc, new Location(loc, description, new HashMap<>()));
            }

            System.out.println("--");

            for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        // try (Scanner scanner = new Scanner(new FileReader("locations.txt"))) {
        //     scanner.useDelimiter(",");
        //     while (scanner.hasNextLine()) {
        //         int loc = scanner.nextInt();
        //         scanner.skip(scanner.delimiter());
        //         String description = scanner.nextLine();
        //         System.out.println("Imported loc: " + loc + " description: " + description);
        //         locations.put(loc, new Location(loc, description, new HashMap<>()));
        //     }
        //
        //     System.out.println("--");
        //
        //     for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
        //         System.out.println(entry.getValue());
        //     }
        // }
    }
}
