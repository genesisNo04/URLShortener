package com.example.URLShortening.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class URLRequestDTO {

    @NotBlank
    @URL(message = "Invalid URL format")
    public String originalURL;
}
