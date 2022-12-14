package org.signature;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Log setup working");
        LOGGER.log(Level.ERROR, "It's working");
        LOGGER.log(Level.TRACE, "worked......................");
        System.out.println("Hello world!");
    }
}
