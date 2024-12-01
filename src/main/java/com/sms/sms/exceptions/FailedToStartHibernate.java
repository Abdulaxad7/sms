package com.sms.sms.exceptions;

public class FailedToStartHibernate extends RuntimeException {
    public FailedToStartHibernate(String message) {
        super(message);
    }
}
