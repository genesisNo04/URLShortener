package com.example.URLShortening.Service;

import com.example.URLShortening.Entity.URL;

public interface URLService {

    URL saveShorten(URL url);

    URL getShorten(String shortCode);

    void deleteShorten(String shortCode);

    URL updateShorten(String shortCode);


}
