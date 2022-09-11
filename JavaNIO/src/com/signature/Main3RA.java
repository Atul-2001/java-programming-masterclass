package com.signature;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class Main3RA {

    public static void main(String[] args) {
        try (RandomAccessFile binFile = new RandomAccessFile("data.dat", "rwd")) {
            FileChannel binChannel = binFile.getChannel();

            RandomAccessFile copyFile = new RandomAccessFile("copyData.dat", "rw");
            FileChannel copyChannel = copyFile.getChannel();

//            long maxWritten = copyChannel.transferFrom(binChannel, 0, binChannel.size());
            long maxWritten = binChannel.transferTo(0, binChannel.size(), copyChannel);
            System.out.println("Transfered : " + maxWritten);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
