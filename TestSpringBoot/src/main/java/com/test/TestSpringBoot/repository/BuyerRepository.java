package com.test.TestSpringBoot.repository;

import com.test.TestSpringBoot.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity,Integer> {
}
