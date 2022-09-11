package com.signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path file = FileSystems.getDefault().getPath("file.txt");
        printFile(file);

        Path file2 = Paths.get(".","resources", "subFile.txt");/*FileSystems.getDefault().getPath("resources","subFile.txt");*/
        printFile(file2);

        Path file3 = Paths.get("../outsideFile.txt");
        printFile(file3);

        System.out.println(Paths.get(".").toAbsolutePath());

        Path temp = FileSystems.getDefault().getPath(".", "resources", "..", "resources", "subFile.txt");
        System.out.println(temp.normalize().toAbsolutePath());
        printFile(temp.normalize());
    }

    public static void printFile(Path path) {
        try (BufferedReader file = Files.newBufferedReader(path)) {
            String line;
            while ((line = file.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
