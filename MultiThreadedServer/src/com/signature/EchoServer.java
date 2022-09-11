package com.signature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoServer extends Thread{
    private Socket socket;
    private int thread;

    public EchoServer(Socket socket) {
        this.socket = socket;
        this.thread = Main.threadNo;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client " + thread + " Connected.");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String clientMsg = input.readLine();
                if (clientMsg == null || clientMsg.equals("exit")) {
                    System.out.println("Client " + thread + " Disconnected.");
                    break;
                }

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
//                System.out.println("Message form client : " + clientMsg);
                output.println("Echo from server : " + clientMsg);
            }
        } catch (IOException e) {
            System.out.println("Oops : " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
