package com.example.URLShortening.Repository;

import com.example.URLShortening.Entity.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    Optional<URL> findByShortCode(String shortCode);

    void deleteByShortCode(String shortCode);

    Optional<URL> updateByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
}
