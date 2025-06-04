package com.chanj.autumn.beans;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
