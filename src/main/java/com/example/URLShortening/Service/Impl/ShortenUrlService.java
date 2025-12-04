package com.example.URLShortening.Service.Impl;

import com.example.URLShortening.Repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShortenUrlService {

    @Autowired
    private URLRepository urlRepository;

    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    Random r = new Random();

    public String generateShortCode() {
        String code;

        do {
            code = randomShortCode();
        } while (urlRepository.existsByShortCode(code));

        return code;
    }

    public String randomShortCode() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(r.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
