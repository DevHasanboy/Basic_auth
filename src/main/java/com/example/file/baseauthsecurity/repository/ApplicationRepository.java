package com.example.file.baseauthsecurity.repository;

import com.example.file.baseauthsecurity.entity.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Applications, Long> {

    @Query(value = "SELECT * FROM aplication WHERE user_id = :userId", nativeQuery = true)
    List<Applications> findByUserId(@Param("userId") Long userId);
}
