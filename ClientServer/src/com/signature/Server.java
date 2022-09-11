package com.signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            while (true) {
                if (input.readLine().equalsIgnoreCase("exit")) {
                    System.out.println("Client Disconnected");
                    break;
                }

                output.println("Echo form server : " + input.readLine());
            }
        } catch (IOException e) {
            System.out.println("Server error : " + e.getMessage());
        }
    }
}
