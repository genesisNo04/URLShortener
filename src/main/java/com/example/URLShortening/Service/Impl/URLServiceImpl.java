package com.example.URLShortening.Service.Impl;

import com.example.URLShortening.Entity.URL;
import com.example.URLShortening.Exception.ResourceNotFoundException;
import com.example.URLShortening.Repository.URLRepository;
import com.example.URLShortening.Service.URLService;
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
    public URL getOrigin(String shortCode) {
        return urlRepository.findByShortCode(shortCode).orElseThrow(() -> new ResourceNotFoundException("Shortcode does not exists."));
    }

    @Override
    public void deleteShorten(String shortCode) {
        urlRepository.deleteByShortCode(shortCode);
    }

    @Override
    public URL updateShorten(String shortCode) {
        URL currentURL = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new ResourceNotFoundException("Shortcode does not exists."));
        currentURL.setShortCode(shortenUrlService.generateShortCode());
        return urlRepository.save(currentURL);
    }
}
