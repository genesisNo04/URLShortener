package com.example.URLShortening.Controller;

import com.example.URLShortening.DTO.URLRequestDTO;
import com.example.URLShortening.DTO.URLResponseDTO;
import com.example.URLShortening.DTO.URLStatsResponseDTO;
import com.example.URLShortening.Entity.URL;
import com.example.URLShortening.Service.Impl.ShortenUrlService;
import com.example.URLShortening.Service.URLService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/shorten")
public class URLController {

    @Autowired
    private URLService urlService;

    @Autowired
    private ShortenUrlService shortenUrlService;

    @PostMapping
    private ResponseEntity<URLResponseDTO> createShortenURL(@Valid @RequestBody URLRequestDTO urlRequestDTO) {
        URL newURL = new URL(urlRequestDTO.getOriginalURL(), shortenUrlService.generateShortCode());
        urlService.saveShorten(newURL);
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    private ResponseEntity<URLResponseDTO> retrieveOriginalURL(@PathVariable  String shortCode) {
        URL newURL = urlService.getOrigin(shortCode);
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}/redirect")
    private ResponseEntity<Void> retrieveAndRedirectOriginalURL(@PathVariable  String shortCode) {
        URL newURL = urlService.getOrigin(shortCode);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).header(HttpHeaders.LOCATION, newURL.getOriginURL()).build();
    }

    @PutMapping("/{shortCode}")
    private ResponseEntity<URLResponseDTO> updateShortenURL(@PathVariable  String shortCode) {
        URL newURL = urlService.updateShortenCode(shortCode);
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/url/{shortCode}")
    private ResponseEntity<URLResponseDTO> updateNewURL(@PathVariable String shortCode, @Valid @RequestBody URLRequestDTO urlRequestDTO) {
        URL newURL = urlService.updateURL(shortCode, urlRequestDTO.getOriginalURL());
        URLResponseDTO response = new URLResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{shortCode}")
    private ResponseEntity<URLResponseDTO> deleteShortenURL(@PathVariable  String shortCode) {
        urlService.deleteShorten(shortCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{shortCode}/stats")
    private ResponseEntity<URLStatsResponseDTO> getURLStats(@PathVariable  String shortCode) {
        URL newURL = urlService.getOrigin(shortCode);
        URLStatsResponseDTO response = new URLStatsResponseDTO(newURL.getId(), newURL.getOriginURL(), newURL.getShortCode(), newURL.getCreatedAt(), newURL.getUpdatedAt(), newURL.getAccessCount());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
