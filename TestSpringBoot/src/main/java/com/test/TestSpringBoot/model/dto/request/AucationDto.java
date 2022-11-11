package com.test.TestSpringBoot.model.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AucationDto {
    int productid;
    int userid;
    @Min(value = 1,message = "Minimum value should be one")
    float bidmoney;
}
