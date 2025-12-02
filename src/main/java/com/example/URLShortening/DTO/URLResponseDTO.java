package com.example.URLShortening.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URLResponseDTO {

    private Long id;

    private String url;

    private String shortCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
