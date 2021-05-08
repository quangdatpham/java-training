package _11_input_output._11_serialization;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Location> locations = new HashMap<>();

    // public static void main(String[] args) throws IOException {
    //     try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
    //         for (Location location : locations.values()) {
    //             locFile.writeObject(location);
    //         }
    //     }
    // }
    //
    // static {
    //     try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations.txt")))) {
    //         scanner.useDelimiter(",");
    //         while (scanner.hasNextLine()) {
    //             int loc = scanner.nextInt();
    //             scanner.skip(scanner.delimiter());
    //             String description = scanner.nextLine();
    //             System.out.println("Imported loc: " + loc + " description: " + description);
    //             locations.put(loc, new Location(loc, description, new HashMap<>()));
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // deserialization
    public static void main(String[] args) {
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (InvalidClassException e) {
            System.out.println("InvalidClassException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        }

        for (Location location : locations.values()) {
            System.out.println(location);
        }
    }
}
