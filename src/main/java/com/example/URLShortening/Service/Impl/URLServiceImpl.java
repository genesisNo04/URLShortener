package com.example.URLShortening.Service.Impl;

import com.example.URLShortening.Entity.URL;
import com.example.URLShortening.Exception.ResourceNotFoundException;
import com.example.URLShortening.Repository.URLRepository;
import com.example.URLShortening.Service.URLService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLServiceImpl implements URLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private ShortenUrlService shortenUrlService;

    @Override
    public URL saveShorten(URL url) {
        return urlRepository.save(url);
    }

    @Override
    @Transactional
    public URL getOrigin(String shortCode) {
        URL url = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new ResourceNotFoundException("Shortcode does not exists."));
        urlRepository.incrementAccessCount(shortCode);
        return urlRepository.findByShortCode(shortCode).get();
    }

    @Override
    public void deleteShorten(String shortCode) {
        URL currentURL = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new ResourceNotFoundException("Shortcode does not exists."));
        urlRepository.delete(currentURL);
    }

    @Override
    public URL updateShortenCode(String shortCode) {
        URL currentURL = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new ResourceNotFoundException("Shortcode does not exists."));
        currentURL.setShortCode(shortenUrlService.generateShortCode());
        return urlRepository.save(currentURL);
    }

    @Override
    public URL updateURL(String shortCode, String newUrl) {
        URL currentURL = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new ResourceNotFoundException("Shortcode does not exists."));
        currentURL.setOriginURL(newUrl);
        return urlRepository.save(currentURL);
    }
}
