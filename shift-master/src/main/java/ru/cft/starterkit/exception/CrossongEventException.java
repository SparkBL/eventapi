package ru.cft.starterkit.exception;

public class CrossongEventException extends Exception {
    public CrossongEventException() {
    }

    public CrossongEventException(String message) {
        super(message);
    }

    public CrossongEventException(String message, Throwable cause) {
        super(message, cause);
    }

}
