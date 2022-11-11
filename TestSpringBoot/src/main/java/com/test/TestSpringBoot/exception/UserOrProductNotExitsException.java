package com.test.TestSpringBoot.exception;

public class UserOrProductNotExitsException extends Throwable {
    public UserOrProductNotExitsException(String user_or_product_not_exits) {
        super(user_or_product_not_exits);
    }
}
