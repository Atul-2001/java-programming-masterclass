package com.signature;

import static com.signature.ThreadColor.ANSI_RED;

public class RunnableThread implements Runnable {

    @Override
    public void run() {
        System.out.println(ANSI_RED + "Message from Runnable Thread");
    }
}
