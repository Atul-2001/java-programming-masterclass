package com.signature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import static com.signature.Main.EOF;

public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> futureTask = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_RED + "I'm being printed form callable class.");
                return "This is the callable result.";
            }
        });

        try {
            System.out.println(futureTask.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong.");
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interrupted.");
        }

        executorService.shutdown();

//        new Thread(producer).start();
//        new Thread(consumer1).start();
//        new Thread(consumer2).start();
    }
}

class MyProducer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5", "6"};

        for (String num : nums) {
            try {
                bufferLock.lock();
                System.out.println(color + "Adding..." + num);
                try {
                    buffer.add(num);
                } finally {
                    bufferLock.unlock();
                }

//                bufferLock.lock();
//                System.out.println(color + "Adding..." + num);
//                buffer.add(num);
//                bufferLock.unlock();

//                synchronized (buffer) {
//                    System.out.println(color + "Adding..." + num);
//                    buffer.add(num);
//                }

                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                System.out.println("Producer Interrupted.");
            }
        }
        System.out.println(color + "Adding EOF and existing...");
        bufferLock.lock();
        try {
            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }

//        buffer.lock();
//        buffer.add("EOF");
//        bufferLock.unlock();

//        synchronized (buffer) {
//            buffer.add("EOF");
//        }
    }
}

class MyConsumer implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            if (bufferLock.tryLock()) {
//                bufferLock.lock();
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    System.out.println(color + "The Counter : " + count);
                    count = 0;

                    if (buffer.get(0).equals(EOF)) {
                        System.out.println(color + "Exiting...");
                        break;
                    } else {
                        System.out.println(color + "Reading..." + buffer.remove(0));
                    }
                } finally {
                    bufferLock.unlock();
                }
            } else {
                count++;
            }

//            bufferLock.lock();
//            if (buffer.isEmpty()) {
//                bufferLock.unlock();
//                continue;
//            }
//
//            if (buffer.get(0).equals(EOF)) {
//                System.out.println(color + "Exiting...");
//                bufferLock.unlock();
//                break;
//            } else {
//                System.out.println(color + "Reading..." + buffer.remove(0));
//            }
//            bufferLock.unlock();

//            synchronized (buffer) {
//                if (buffer.isEmpty()) {
//                    continue;
//                }
//
//                if (buffer.get(0).equals(EOF)) {
//                    System.out.println(color + "Exiting...");
//                    break;
//                } else {
//                    System.out.println(color + "Reading..." + buffer.remove(0));
//                }
//            }
        }
    }
}
