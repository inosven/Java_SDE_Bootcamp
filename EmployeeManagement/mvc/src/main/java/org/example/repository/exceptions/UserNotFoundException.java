package org.example.repository.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {super();}
    public UserNotFoundException(String message, Throwable cause) {super(message, cause);}
    public UserNotFoundException(Throwable cause) {super(cause);}
    public UserNotFoundException() {super();}
}
