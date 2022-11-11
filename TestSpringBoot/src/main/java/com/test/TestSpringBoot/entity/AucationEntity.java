package com.test.TestSpringBoot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AucationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    int productid;
    int userid;
    float bidmoney;
    @ManyToOne
    ProductEntity productEntity;

    @ManyToOne
    UserEntity userEntity;
}
