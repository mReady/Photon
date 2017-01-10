package net.mready.photon;

public class InjectorException extends RuntimeException {
    InjectorException(String message) {
        super(message);
    }

    InjectorException(String message, Throwable cause) {
        super(message, cause);
    }
}
