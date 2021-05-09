package _11_input_output._20_file_system.files;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintNamesSimpleFileVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("--");
        System.out.println("preVisitDirectory()  " + dir.toAbsolutePath());
        if (dir.toAbsolutePath().toString().contains("_16_writing_binary_files")) {
            return FileVisitResult.SKIP_SUBTREE;
            // return FileVisitResult.SKIP_SIBLINGS;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("visitFile()          " + file.toAbsolutePath());
        if (file.toAbsolutePath().toString().contains("_05_try_with_resources/Main.java"))
            return FileVisitResult.SKIP_SIBLINGS;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error while accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("postVisitDirectory() " + dir.toAbsolutePath());
        return super.postVisitDirectory(dir, exc);
    }
}
