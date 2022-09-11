package com.signature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            byte[] outputByte = "Hello World!".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(outputByte);
            System.out.println("No of Bytes write :- " + binChannel.write(buffer));

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(2701);
            intBuffer.flip();
            System.out.println("No of int write :- " + binChannel.write(intBuffer));

            intBuffer.flip();
            intBuffer.putInt(1509);
            intBuffer.flip();
            System.out.println("No of int write :- " + binChannel.write(intBuffer));

            RandomAccessFile data = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = data.getChannel();
            outputByte[0] = 'a';
            outputByte[1] = 'p';
            buffer.flip();
            channel.read(buffer);
            if (buffer.hasArray()) {
                System.out.println(new String(buffer.array()));
            }

//            Absolute Method =>
            intBuffer.flip();
            channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));


//            Relative Method =>
//            intBuffer.flip();
//            channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());
//            intBuffer.flip();
//            channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());

//            RandomAccessFile data = new RandomAccessFile("data.dat", "rwd");
//            byte[] b = new byte[outputByte.length];
//            data.read(b);
//            System.out.println(new String(b));
//            System.out.println(data.readInt());
//            System.out.println(data.readInt());


//            FileInputStream file = new FileInputStream("data.txt");
//            FileChannel channel = file.getChannel();
//
//            Path file = FileSystems.getDefault().getPath("data.txt");
//            Files.write(file, "\nLine 4".getBytes(), StandardOpenOption.APPEND);
//
//            List<String> data = Files.readAllLines(file);
//            for (String line : data) {
//                System.out.println(line);
//            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
