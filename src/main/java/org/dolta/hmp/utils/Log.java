package org.dolta.hmp.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    public static final Logger logger = LogManager.getRootLogger();


    public static void debug(Object msg) {
        logger.debug(msg);
    }

    public static void info(Object msg) {
        logger.info(msg);
    }

    public static void warn(Object msg) {
        logger.warn(msg);
    }

    public static void error(Object msg) {
        logger.error(msg);
    }

    public static void fatal(Object msg) {
        logger.fatal(msg);
    }

    public static void diag(Object msg) {
        logger.log(Level.forName("DIAG", 100), msg);
    }

    public static void fine(Object msg) {
        logger.log(Level.forName("FINE", 100), msg);
    }

    public static void bad(Object msg) {
        logger.log(Level.forName("BAD", 100), msg);
    }

}
