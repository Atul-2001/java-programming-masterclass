package com.signature;

import static com.signature.ThreadColor.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(ANSI_GREEN + "Message from main thread.");

        final Thread anotherThread = new AnotherThread();
        anotherThread.setName("== Another Thread ==");
        anotherThread.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Message from anonymous thread.");
            }
        }.start();

        Thread runnableThread = new Thread(new RunnableThread());
        runnableThread.start();

        Thread runnableThread2 = new Thread(new RunnableThread() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous runnable thread.");
            }
        });
        runnableThread2.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ANSI_BLACK + "Message from anonymous runnable thread from main");

                try {
                    anotherThread.join(2000);
                    System.out.println("AnotherThread terminated, or timeout, so i'm running again.");
                } catch (InterruptedException e) {
                    System.out.println("I couldn't wait after all. I'm was interrupted.");
                }
            }
        }).start();

//        anotherThread.interrupt();
        System.out.println(ANSI_BLUE + "Again message form main thread.");
    }
}
