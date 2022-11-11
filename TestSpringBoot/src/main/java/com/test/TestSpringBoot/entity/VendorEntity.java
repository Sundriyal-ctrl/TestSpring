package com.test.TestSpringBoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class VendorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;
    String name;
    String address;
    String email;
    long phone;
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "Vendorcreateproduct",joinColumns = {@JoinColumn(name = "vendorid")},inverseJoinColumns = {@JoinColumn(name = "productid")})
    List<ProductEntity> list=new ArrayList<>();
}
