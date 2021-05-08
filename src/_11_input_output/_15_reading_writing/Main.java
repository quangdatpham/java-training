package _11_input_output._15_reading_writing;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // FileInputStream file = new FileInputStream("data.txt");
            // FileChannel channel = file.getChannel();

            Path path = FileSystems.getDefault().getPath("data.txt");
            Files.write(path, "\nLine 5".getBytes("UTF-8"), StandardOpenOption.APPEND);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
