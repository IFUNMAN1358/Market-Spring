package ru.nag.spring.exception.UserException;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
