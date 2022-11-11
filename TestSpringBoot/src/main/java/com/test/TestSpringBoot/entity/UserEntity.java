package com.test.TestSpringBoot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  int id;
  String username;
  String address;
  String email;
  long number;
  @ManyToMany(cascade = CascadeType.ALL)
  List<ProductEntity> list=new ArrayList<>();

  @ManyToOne
  List<BuyerEntity> buyerEntityList=new ArrayList<>();

  @OneToMany(mappedBy = "userEntity")
  List<AucationEntity> aucationEntityList=new ArrayList<>();
}
