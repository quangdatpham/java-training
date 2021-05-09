package _11_input_output._20_file_system.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {
        /*
            Separator
                Window: \
                Linux: /
        */

        /*
            Java introduce another class that does the same thing in Java 7. Why?
            - java.io.File.delete() --> return boolean --> could not tell if the file didn't exist, or the app didn't have permission to delete
            - java.io.File.rename() --> work differently on different platforms.
            - no support for symbolic links (points to another file, used with networks, points to a remote location)
            - can not get metadata (permissions, owners, security information)
            - many methods don't perform well when working with lot of data (request all files in a direction --> hang)
         */

        // Java NIO FileSystem

        // RELATIVE PATH
        System.out.println("\n________ RELATIVE PATH ________");
        Path workingDirectoryPath = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
        printFile(workingDirectoryPath);

        Path subdirectoryPath = FileSystems.getDefault().getPath("files", "SubdirectoryFile.txt");
        printFile(subdirectoryPath);

        Path path = Paths.get("");
        System.out.println(path.toAbsolutePath());

        path = FileSystems.getDefault().getPath(".", "files", "..", "files", "SubdirectoryFile.txt");
        System.out.println(path.toAbsolutePath());
        System.out.println(path.normalize().toAbsolutePath());

        // ABSOLUTE PATH
        System.out.println("\n________ ABSOLUTE PATH ________");
        Path absolutePath = Paths.get("/Volumes/DATA/Learnings/Java/Source code", "/MyPractices", "data.txt");
        printFile(absolutePath);

        // COPY FILES
        System.out.println("\n________ COPY FILES ________");
        try {
            Path sourceFile = FileSystems.getDefault().getPath("data.txt");
            Path copyFile = FileSystems.getDefault().getPath("data_copy.txt");
            // Files.copy(sourceFile, copyFile);
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CHECK EXISTS
        System.out.println("\n________ CHECK EXISTS ________");
        System.out.println("Exists " + Files.exists(Paths.get("/this_file_does_not_exist.txt")));
        System.out.println("Exists " + Files.exists(path));

        // MOVE
        System.out.println("\n________ MOVE ________");
        try {
            Path sourceFile = FileSystems.getDefault().getPath("data_copy.txt");
            Path destination = FileSystems.getDefault().getPath("data_move.txt");
            Files.move(sourceFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DELETE IF EXIST
        System.out.println("\n________ DELETE IF EXIST ________");
        try {
            Path fileToDelete = FileSystems.getDefault().getPath("data_move.txt");
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // FILE ATTRIBUTES
        System.out.println("\n________ FILE ATTRIBUTES ________");
        try {
            Path filePath = FileSystems.getDefault().getPath("data.txt");
            long size = Files.size(filePath);
            System.out.println("Size = " + size);
            System.out.println("Last modified =  " + Files.getLastModifiedTime(filePath));

            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size =  " + attrs.size());
            System.out.println("Last modified =  " + attrs.lastModifiedTime());
            System.out.println("Created = " + attrs.creationTime());
            System.out.println("Is directory = " + attrs.isDirectory());
            System.out.println("Is regular file = " + attrs.isRegularFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // EXISTING DIRECTORY CONTENTS
        System.out.println("\n________ EXISTING DIRECTORY CONTENTS ________");
        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);
        Path directory = FileSystems.getDefault().getPath("");

        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
            for (Path file : contents) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SEPARATORS
        System.out.println("\n________ SEPARATORS ________");
        String separator = File.separator;
        System.out.println(separator);

        // TEMP FILES
        System.out.println("\n________ TEMP FILES ________");
        try {
            Path tempFile = Files.createTempFile("temp", ".sometempfile");
            System.out.println("Temporary file path = " + tempFile.toAbsolutePath());
            // Temporary file path = /var/folders/gd/tf_39qj92vg41vmr5vznk5w00000gp/T/temp7127767030726981675.sometempfile
        } catch (IOException e) {
            e.printStackTrace();
        }

        // FILE STORES
        System.out.println("\n________ FILE STORES ________");
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores) {
            System.out.println("Volume name/Drive letter = " + store);
            System.out.println("file store               = " + store.name());
        }

        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path p : rootPaths) {
            System.out.println(p);
        }

        // WALKING TREE
        System.out.println("\n________ WALKING TREE ________");
        Path walkingPath = FileSystems.getDefault().getPath("src", "_10_collections");
        try {
            Files.walkFileTree(walkingPath, new PrintNamesSimpleFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // COPY ENTIRE TREE
        System.out.println("\n________ COPY ENTIRE TREE ________");
        Path copyPath = FileSystems.getDefault().getPath("temp");
        try {
            Files.walkFileTree(walkingPath, new CopyFileSimpleFileVisitor(walkingPath, copyPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // mapping java.io and java.nio
        System.out.println("\n________ mapping java.io and java.nio ________");
        File file = new File("files", "SubdirectoryFile.txt");
        Path convertedPath = file.toPath();
        System.out.println("Converted path = " + convertedPath);

        Path parentPath = Paths.get("files");
        Path childRelativePath = Paths.get("SubdirectoryFile.txt");
        System.out.println("Resolved path = " + parentPath.resolve(childRelativePath));

        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Working directory = " + workingDirectory.getAbsolutePath());

        String[] workingDirContents = workingDirectory.list();
        for (String content : workingDirContents) {
            System.out.println("Content: " + content);
        }

        File[] workingDirFiles = workingDirectory.listFiles();
        for (File f : workingDirFiles) {
            System.out.println("File: " + f.getName());
        }

        /*
            use java.nio when working with a file system
            and when reading and writing file contents.
            Often, the java.io streams are the better choice.
         */

    }

    private static void printFile(Path path) {
        try (BufferedReader fileReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
