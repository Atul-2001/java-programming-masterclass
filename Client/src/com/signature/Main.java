package com.signature;

import javax.naming.TimeLimitExceededException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try (Socket socket = new Socket("localhost", 5000)) {
	        socket.setSoTimeout(10000);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String msg;

            do {
                System.out.print("Enter msg for server : ");
                msg = scanner.nextLine();
                output.println(msg);

                if (!msg.equals("exit")) {
                    System.out.println(input.readLine());
                }
            } while (!msg.equals("exit"));
        } catch (SocketTimeoutException e) {
            System.out.println("Server timeout: " + e.getMessage());
        }catch (IOException e) {
            System.out.println("Client error : " + e.getMessage());
        }
    }
}
