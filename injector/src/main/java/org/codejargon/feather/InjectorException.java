package org.codejargon.feather;

public class InjectorException extends RuntimeException {
    InjectorException(String message) {
        super(message);
    }

    InjectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
