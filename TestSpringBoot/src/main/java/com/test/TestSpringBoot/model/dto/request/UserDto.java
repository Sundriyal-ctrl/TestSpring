package com.test.TestSpringBoot.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDto {
    String username;
    String address;
    @Email(regexp = "^[\\w.+\\-]+@gmail\\.com$",message = "not a valid email address")
    String email;
    long number;
}
