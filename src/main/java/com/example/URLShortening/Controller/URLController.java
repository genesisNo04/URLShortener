package com.example.URLShortening.Controller;

import com.example.URLShortening.DTO.URLRequestDTO;
import com.example.URLShortening.DTO.URLResponseDTO;
import com.example.URLShortening.DTO.URLStatsResponseDTO;
import com.example.URLShortening.Entity.URL;
import com.example.URLShortening.Service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/shorten")
public class URLController {

    @Autowired
    private URLService urlService;

    @PostMapping
    private ResponseEntity<URLResponseDTO> createShortenURL(@RequestBody URLRequestDTO urlRequestDTO) {
        URL newURL = new URL(urlRequestDTO.getOriginalURL(), "abc123");
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    private ResponseEntity<URLResponseDTO> retrieveOriginalURL(@PathVariable  String shortCode) {
        URL newURL = urlService.getURLWithShortCode(shortCode);
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{shortCode}")
    private ResponseEntity<URLResponseDTO> updateShortenURL(@PathVariable  String shortCode) {
        URL newURL = urlService.getURLWithShortCode(shortCode);
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{shortCode}")
    private ResponseEntity<URLResponseDTO> deleteShortenURL(@PathVariable  String shortCode) {
        URL newURL = urlService.getURLWithShortCode(shortCode);
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}/stats")
    private ResponseEntity<URLStatsResponseDTO> deleteShortenURL(@PathVariable  String shortCode) {
        URL newURL = urlService.getURLWithShortCode(shortCode);
        URLStatsResponseDTO response = new URLStatsResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt(), newURL.getAccessCount());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
