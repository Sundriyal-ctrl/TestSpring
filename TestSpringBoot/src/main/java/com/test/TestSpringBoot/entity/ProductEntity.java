package com.test.TestSpringBoot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String productName;
    float bid;
    String productDescription;
    boolean productStatus;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "list")
    @JsonIgnore
    List<VendorEntity> vendorEntityList=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "list")
    @JoinTable(name = "Aucation",joinColumns = {@JoinColumn(name = "productid")},inverseJoinColumns = {@JoinColumn(name="userid")})
    List<UserEntity> list=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productEntity")
    List<AucationEntity> aucationEntityList=new ArrayList<>();
}
