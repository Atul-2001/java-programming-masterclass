package com.signature;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;

public class TestIO {

    public static void main(String[] args) {
        int[] values = new int[50000000];

        long start, end;

        start = System.currentTimeMillis();
        writeBuffered(values);
        end = System.currentTimeMillis();
        System.out.println("buffered: " + (end - start));

        start = System.currentTimeMillis();
        writeChunk(values);
        end = System.currentTimeMillis();
        System.out.println("one chunk: " + (end - start));

        start = System.currentTimeMillis();
        writeNoBuffer(values);
        end = System.currentTimeMillis();
        System.out.println("non-buffered: " + (end - start));

    }

    /**
     * This will write the bytes of an integer array to a file. It uses a
     * data output stream w/out a buffered stream.
     *
     * @param values the array of ints that will be written to a file.
     **/
    static void writeNoBuffer(int[] values) {
        try {
            DataOutputStream stream = new DataOutputStream(
                    Files.newOutputStream(Paths.get("non-buffered.bin"))
            );
            for (int i = 0; i < values.length; i++) {
                stream.writeInt(values[i]);
            }
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Writes the bytes of an integer array, this uses a buffered output
     * stream.
     *
     * @param values the array of ints that will be written to a file.
     **/
    static void writeBuffered(int[] values) {
        try {
            DataOutputStream stream = new DataOutputStream(
                    new BufferedOutputStream(
                            Files.newOutputStream(Paths.get("buffered.bin"))
                    )
            );
            for (int i = 0; i < values.length; i++) {
                stream.writeInt(values[i]);
            }
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Writes the bytes of an integer array, this uses a non-buffered
     * output stream, but it writes all of the data in one array of bytes.
     *
     * @param values the array of ints that will be written to a file.
     **/
    static void writeChunk(int[] values) {
        byte[] bytes = new byte[4 * values.length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < 4; j++) {
                bytes[4 * i + j] = (byte) (values[i] >> (8 * (3 - j)));
            }
        }
        try {

            OutputStream stream = Files.newOutputStream(Paths.get("chunk.bin"));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}