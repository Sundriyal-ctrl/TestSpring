package com.test.TestSpringBoot.model.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class VendorDto {
    @NotNull
    String name;
    @NotNull
    String address;
    @Email(regexp = "^[\\w.+\\-]+@gmail\\.com$",message = "not a valid email address")
    String email;
    long phone;
}
