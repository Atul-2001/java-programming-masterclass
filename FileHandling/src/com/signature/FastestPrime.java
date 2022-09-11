package com.signature;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Timer;

public class FastestPrime {

    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println("Memory before run : " + beforeUsedMem);
        boolean[] prime = generatePrime(100000000);
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println("Memory after generating prime : " + afterUsedMem);
        System.out.println(afterUsedMem - beforeUsedMem);
        end = System.currentTimeMillis();
        System.out.println("PNC creation end : " + (end-start));
        System.out.println("All prime are generated...");
        System.out.println("Starting writing of data..");
        writeData(prime);
    }

    private static boolean[] generatePrime(int limit) {
        boolean[] prime = new boolean[limit];

        prime[0] = true;
        for (int i = 1; i < limit; i++) {
            if (!prime[i]) {
                for (int j = i+1; j <= limit; j+=(i+1)) {
                    if (!(j == (i+1))) {
                        prime[j-1] = true;
                    }
                }
            }

            if (!((int)Math.pow(i, 2) < limit)) {
                break;
            }
        }

        return prime;
    }

    private static void writeData(boolean[] prime) {
        long start, end;
        try (BufferedWriter file = new BufferedWriter(new FileWriter("prime.txt"))) {
            start = System.currentTimeMillis();
            for (int i = 0; i < prime.length; i++) {
                if (!prime[i]) {
                    file.write(i+1 + "\n");
                }
            }
            end = System.currentTimeMillis();
            System.out.println("File creation completed : " + (end-start));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}