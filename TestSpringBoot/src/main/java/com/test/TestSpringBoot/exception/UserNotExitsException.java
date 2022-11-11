package com.test.TestSpringBoot.exception;

public class UserNotExitsException extends Throwable {
    public UserNotExitsException(String s) {
        super(s);
    }
}
