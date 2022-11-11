package com.test.TestSpringBoot.exception;

import javax.validation.constraints.Email;

public class AlreadyExistException extends Throwable {
    public AlreadyExistException( String s) {
        super(s);
    }
}
