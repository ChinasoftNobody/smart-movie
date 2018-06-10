package com.chinasoft.lgh.movies.datacollector.common;

/**
 * @author Administrator
 */
public class CollectionException extends Exception {
    public CollectionException(String message) {
        super(message);
    }

    public CollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
