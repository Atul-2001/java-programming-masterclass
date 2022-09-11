package com.signature;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class Main2 {
    public static void main(String[] args) {
        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            ByteBuffer writeBuffer = ByteBuffer.allocate(100);
            byte[] outputByte = "Hello World!".getBytes();
            byte[] outputBuffer2 = "This new IO world!".getBytes();
            writeBuffer.put(outputByte).putInt(256).putInt(-1509).put(outputBuffer2).putInt(2701);
            /*writeBuffer.putInt(256);
            writeBuffer.putInt(-1509);
            byte[] outputBuffer2 = "This new IO world!".getBytes();
            writeBuffer.put(outputBuffer2);
            writeBuffer.putInt(2701);*/
            writeBuffer.flip();
            binChannel.write(writeBuffer);

            RandomAccessFile readFile = new RandomAccessFile("data.dat", "rwd");
            FileChannel readChannel = readFile.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            readChannel.read(readBuffer);
            readBuffer.flip();

            byte[] inputByte = new byte[outputByte.length];
            readBuffer.get(inputByte);
            System.out.println("input String:- " + new String(inputByte));
            System.out.println("int 1:- " + readBuffer.getInt());
            System.out.println("int 2:- " + readBuffer.getInt());
            byte[] inputByte2 = new byte[outputBuffer2.length];
            readBuffer.get(inputByte2);
            System.out.println("input String 2:- " + new String(inputByte2));
            System.out.println("int 3:- " + readBuffer.getInt());

            readChannel.close();
            readFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
