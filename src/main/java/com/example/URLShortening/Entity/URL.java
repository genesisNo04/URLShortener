package com.example.URLShortening.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originURL;

    @Column(nullable = false)
    private String shortCode;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int accessCount;

    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updatedAt() {
        updatedAt = LocalDateTime.now();
    }

    public URL(String originURL, String shortCode) {
        this.originURL = originURL;
        this.shortCode = shortCode;
        accessCount = 0;
    }
}
