package _11_input_output._07_buffer_reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")));
            // scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                // int loc = scanner.nextInt();
                // scanner.skip(scanner.delimiter());
                // String description = scanner.nextLine();
                String[] data = scanner.nextLine().split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
                System.out.println("Imported loc: " + loc + " description: " + description);
                locations.put(loc, new Location(loc, description, new HashMap<>()));
            }

            System.out.println("--");

            for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
                System.out.println(entry.getValue());
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
