package com.test.TestSpringBoot.exception;

public class AucationNotExitsException extends Throwable {
    public AucationNotExitsException(String this_aucation_not_exits) {
        super(this_aucation_not_exits);
    }
}
