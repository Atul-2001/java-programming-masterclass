package com.signature;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CreateFileAndDirectory {
    public static void main(String[] args) {
        try {
            Path file = FileSystems.getDefault().getPath("Example/Dir1/file1.txt");
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("Size : " + attr.size());
            System.out.println("Last Modified : " + attr.lastModifiedTime());
            System.out.println("Last Access Time : " + attr.lastAccessTime());
            System.out.println("Is Directory : " + attr.isDirectory());
            System.out.println("Is regular file : " + attr.isRegularFile());
            System.out.println("Created : " + attr.creationTime());

//            Path file = FileSystems.getDefault().getPath("Example/Dir1/file1.txt");
//            System.out.println("Size : " + Files.size(file));
//            System.out.println("Last Modified : " + Files.getLastModifiedTime(file));
//            System.out.println("Is Directory : " + Files.isDirectory(file));
//            System.out.println("Is Executable : " + Files.isExecutable(file));

//            Path directoriesToCreate = FileSystems.getDefault().getPath("Example", "Dir2/Dir3/Dir4/Dir5/Dir6");
//            Path directoriesToCreate = FileSystems.getDefault().getPath("Example/Dir2/Dir3/Dir4/Dir5/Dir6/Dir7");
//            Files.createDirectories(directoriesToCreate);

//            Path directoryToCreate = FileSystems.getDefault().getPath("Example", "Dir4");
//            Files.createDirectory(directoryToCreate);

//            Path fileToCreate = FileSystems.getDefault().getPath("Example", "file2.txt");
//            Files.createFile(fileToCreate);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
