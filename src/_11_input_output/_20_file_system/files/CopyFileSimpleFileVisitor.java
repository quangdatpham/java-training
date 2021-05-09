package _11_input_output._20_file_system.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFileSimpleFileVisitor extends SimpleFileVisitor<Path> {

    private Path sourcePath;
    private Path destPath;

    public CopyFileSimpleFileVisitor(Path sourcePath, Path destPath) {
        this.sourcePath = sourcePath;
        this.destPath = destPath;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("--");
        System.out.println("preVisitDirectory()  " + dir);

        Path relativizedPath = sourcePath.relativize(dir);
        System.out.println("RelativizedPath        = " + relativizedPath);
        Path copyDir = destPath.resolve(relativizedPath);
        System.out.println("Resolved path for copy = " + copyDir);

        if (Files.exists(copyDir)) {
            System.out.println("Directory exists.");
            return FileVisitResult.CONTINUE;
        }

        try {
            Files.copy(dir, copyDir);
        } catch (IOException e) {
            e.printStackTrace();
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("visitFile()          " + file);
        Path relativizedPath = sourcePath.relativize(file);
        System.out.println("RelativizedPath        = " + relativizedPath);
        Path copyFile = destPath.resolve(relativizedPath);
        System.out.println("Resolved path for copy = " + copyFile);
        try {
            Files.copy(file, copyFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error while accessing file: " + file.toAbsolutePath() + " " + exc.getMessage());
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println("postVisitDirectory() " + dir);
        return super.postVisitDirectory(dir, exc);
    }
}
