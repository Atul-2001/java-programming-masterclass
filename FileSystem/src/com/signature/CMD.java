package com.signature;

import java.io.IOException;
import java.nio.file.*;

public class CMD {
    public static void main(String[] args) {
        try {
            Path file = FileSystems.getDefault().getPath("file.txt");
            System.out.println("Exists : " + Files.exists(file));

            file = FileSystems.getDefault().getPath("NoSuchFile.txt");
            System.out.println(file.toAbsolutePath());
            System.out.println("Exists : " + Files.exists(file));

            file = FileSystems.getDefault().getPath("Example");
            System.out.println("Exists : " + Files.exists(file));

            file = FileSystems.getDefault().getPath("Example", "Dir4");
            System.out.println(file.toAbsolutePath());
            System.out.println("Exists : " + Files.exists(file));

            Path fileToDelete = FileSystems.getDefault().getPath("Example", "Dir1", "file1_Copy.txt");
            Files.deleteIfExists(fileToDelete);

//            Path oldName = FileSystems.getDefault().getPath("Example", "file1.txt");
//            Path newName = FileSystems.getDefault().getPath("Example", "file2.txt");
//            Files.move(oldName, newName);

//            Path source = FileSystems.getDefault().getPath("Example", "file1_Copy.txt");
//            Path destination = FileSystems.getDefault().getPath("Example", "Dir1", "file1_Copy.txt");
//            Files.move(source, destination);

//            Path source = FileSystems.getDefault().getPath("Example", "Dir1");
//            Path destination = FileSystems.getDefault().getPath("Example", "Dir4");
//            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

//            Path source = FileSystems.getDefault().getPath("Example", "Dir1", "file1.txt");
//            Path destination = FileSystems.getDefault().getPath("Example", "file1_Copy.txt");
//            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
