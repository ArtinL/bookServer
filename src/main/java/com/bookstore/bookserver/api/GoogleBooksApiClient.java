package com.bookstore.bookserver.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GoogleBooksApiClient {

    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String API_KEY = "AIzaSyCQAK-HfYpmCljoCDooRvPIPQcL7MNRTFk";

    public String searchBooksByTitle(String title) {
        String apiUrl = API_URL + "?q=" + title + "&key=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
