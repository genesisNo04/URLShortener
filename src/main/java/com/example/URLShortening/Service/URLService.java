package com.example.URLShortening.Service;

import com.example.URLShortening.Entity.URL;

public interface URLService {

    URL saveShorten(URL url);

    URL getOrigin(String shortCode);

    void deleteShorten(String shortCode);

    URL updateShortenCode(String shortCode);

    URL updateURL(String shortCode, String newUrl);
}
