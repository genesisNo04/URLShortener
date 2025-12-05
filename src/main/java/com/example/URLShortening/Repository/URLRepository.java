package com.example.URLShortening.Repository;

import com.example.URLShortening.Entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    Optional<URL> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    @Modifying
    @Query("UPDATE URL u SET u.accessCount = u.accessCount + 1 WHERE u.shortCode = :code")
    void incrementAccessCount(@Param("code") String code);
}
