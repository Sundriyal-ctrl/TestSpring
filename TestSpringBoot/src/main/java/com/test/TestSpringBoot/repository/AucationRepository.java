package com.test.TestSpringBoot.repository;

import com.test.TestSpringBoot.entity.AucationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AucationRepository  extends JpaRepository<AucationEntity,Integer> {
    AucationEntity findByUseridEqualsAndProductidEquals(int userid, int productid);

}
