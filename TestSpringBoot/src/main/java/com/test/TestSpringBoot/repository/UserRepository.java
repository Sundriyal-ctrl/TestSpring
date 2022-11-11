package com.test.TestSpringBoot.repository;

import com.test.TestSpringBoot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    boolean existsByEmailAndPhone(String email, long phone);
}
