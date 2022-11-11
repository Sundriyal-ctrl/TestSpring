package com.test.TestSpringBoot.model.dto.request;

import lombok.Data;

@Data
public class ProductDto {
    String productName;
    float bid;
    String productDescription;
    int userid;
}
