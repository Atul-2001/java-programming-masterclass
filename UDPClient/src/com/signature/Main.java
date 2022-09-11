package com.signature;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoMsg;

            do {
                System.out.println("Enter msg to be echoed : ");
                echoMsg = scanner.nextLine();

                byte[] outputBuffer = new byte[50];
                outputBuffer = echoMsg.getBytes();

                DatagramPacket packet = new DatagramPacket(outputBuffer, outputBuffer.length, address, 5000);
                socket.send(packet);

                byte[] inputBuffer = new byte[50];
                packet = new DatagramPacket(inputBuffer, inputBuffer.length);
                socket.receive(packet);
                System.out.println("Text received : " + new String(inputBuffer, 0, packet.getLength())); //0, packet.getLength() to extract the data only available in buffer not null chars.
            } while (!echoMsg.equals("exit"));
        } catch (SocketTimeoutException e) {
            System.out.println("Server socket time out.");
        } catch (IOException e) {
            System.out.println("Client IOException : " + e.getMessage());
        }
    }
}
