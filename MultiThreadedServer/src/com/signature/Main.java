package com.signature;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    protected static int threadNo = 1;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
	   try (ServerSocket serverSocket = new ServerSocket(5000)) {

	       while (true) {
               EchoServer echoServer = new EchoServer(serverSocket.accept());
               threadPool.execute(echoServer);
               threadNo++;
           }
       } catch (IOException e) {
           System.out.println("Server error : " + e.getMessage());
       }
    }

}
