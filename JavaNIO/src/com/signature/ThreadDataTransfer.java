package com.signature;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class ThreadDataTransfer {
    public static void main(String[] args) {
        try {
            Pipe pipe = Pipe.open();

            Runnable sender = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(64);

                        for (int i = 0; i < 10; i++) {
                            String currentTime = "Current Time is : " + System.currentTimeMillis();

                            buffer.put(currentTime.getBytes());
                            buffer.flip();

                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Runnable receiver = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(64);

                        for (int i = 0; i < 10; i++) {
                            int byteRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[byteRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread : " + new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            new Thread(sender).start();
            new Thread(receiver).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
