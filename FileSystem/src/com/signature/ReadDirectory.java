package com.signature;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

public class ReadDirectory {
    public static void main(String[] args) {
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isRegularFile(entry);
            }
        };

        Path dir = FileSystems.getDefault().getPath("Example" + File.separator + "Dir1");
//        Path dir = FileSystems.getDefault().getPath("Example");
        try (DirectoryStream<Path> list = Files.newDirectoryStream(dir, filter)) {
            for (Path files : list) {
                System.out.println(files.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        try (DirectoryStream<Path> list = Files.newDirectoryStream(dir, "*.txt")) {
//            for (Path files : list) {
//                System.out.println(files.getFileName());
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

        System.out.println(File.separator);
        System.out.println(FileSystems.getDefault().getSeparator());

        try {
            Path path = Files.createTempFile("noUse", ".temp");
            System.out.println(path.toAbsolutePath());

            path = Files.createTempDirectory("tempJava");
            System.out.println(path.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore store : stores) {
            System.out.println("Volume name/drive letter : " + store.name());
            System.out.println("File Store : " + store);
        }

        System.out.println("*****************************");
        Iterable<Path> paths = FileSystems.getDefault().getRootDirectories();
        for (Path path : paths) {
            System.out.println(path);
        }

        System.out.println("---------------- Walking Tree ---------------");
        Path dirPath = FileSystems.getDefault().getPath("Example" + File.separator + "Dir2");
        try {
            Files.walkFileTree(dirPath, new PrintNames());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("------------------ Copy Directory --------------");
        Path copyPath = FileSystems.getDefault().getPath("Example" + File.separator + "Dir4" + File.separator + "Dir2Copy");
        try {
            Files.walkFileTree(dirPath, new CopyFiles(dirPath, copyPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----- Working with file {java.io} ---------");

        File temp = new File("/test/nothing.txt");
        System.out.println(temp.toPath());

        File rootDir = new File("").getAbsoluteFile();
        System.out.println(rootDir.getAbsolutePath());

        File printDir = new File(rootDir, "/Example/Dir2");
        String[] files = printDir.list();

        for (String file : files) {
            System.out.println(file);
        }

        File[] list = printDir.listFiles();
        for (File file : list) {
            System.out.println(file + "   " + file.getName());
        }
    }
}
