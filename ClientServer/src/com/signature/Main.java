package com.signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String inputMsg;

            do {
                System.out.print("Enter msg to echo : ");
                inputMsg = scanner.nextLine();
                output.println(inputMsg);

                if (!inputMsg.equals("exit")) {
                    System.out.println(input.readLine());
                }
            } while (!inputMsg.equals("exit"));
        } catch (IOException e) {
            System.out.println("Client error : " + e.getMessage());
        }
    }
}
