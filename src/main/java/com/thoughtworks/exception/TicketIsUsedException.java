package com.thoughtworks.exception;

public class TicketIsUsedException extends Exception{
    public TicketIsUsedException(String message) {
        super(message);
    }
}
