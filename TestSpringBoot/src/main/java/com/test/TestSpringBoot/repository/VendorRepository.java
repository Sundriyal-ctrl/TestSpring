package com.test.TestSpringBoot.repository;

import com.test.TestSpringBoot.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity,Integer> {
    boolean existsByEmailAndPhone(String email, long phone);

    @Override
    boolean existsById(Integer integer);
}
