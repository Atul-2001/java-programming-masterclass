package com.signature;

import com.sun.jdi.IncompatibleThreadStateException;

import static com.signature.ThreadColor.ANSI_BLUE;
import static com.signature.ThreadColor.ANSI_PURPLE;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        System.out.println(ANSI_PURPLE + "Message from " + currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(ANSI_BLUE + "Another Thread woke me up.");
        }

        System.out.println(ANSI_BLUE + "Three second passed and i'm wake uo again.");
    }
}
