package org.example.service.exceptions;

public class MissingUserException extends RuntimeException{
    public MissingUserException(String message) {super();}
    public MissingUserException(String message, Throwable cause) {super(message, cause);}
    public MissingUserException(Throwable cause) {super(cause);}
    public MissingUserException() {super();}
}
