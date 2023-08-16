package com.bookstore.bookserver.providers;

import com.bookstore.bookserver.model.*;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.ArrayList;


@Component
public class BooksAPIClient {

    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String API_KEY = "AIzaSyCQAK-HfYpmCljoCDooRvPIPQcL7MNRTFk";
    private final RestTemplate restTemplate;

    public BooksAPIClient() {
        this.restTemplate = new RestTemplate();
    }

    public BookBriefDTO[] searchBooks(String query) {
        String apiUrlWithQuery = API_URL + "?q=" + query + "&key=" + API_KEY;

        String jsonResponse = restTemplate.getForObject(apiUrlWithQuery, String.class);

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            JsonNode itemsNode = objectMapper.readTree(jsonResponse).get("items");
            ArrayList<BookBriefDTO> bookList = new ArrayList<>();
            for (JsonNode item : itemsNode) {
                BookBriefDTO book = objectMapper.readValue(item.toString(), BookBriefDTO.class);
                bookList.add(book);
            }
            return bookList.toArray(new BookBriefDTO[0]);
            //return objectMapper.readValue(jsonResponse, BookBriefDTO[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return new BookBriefDTO[0];
        }
    }

    public BookDetailDTO getBookDetails(String id) {
        String apiUrlWithId = API_URL + "/" + id + "?key=" + API_KEY;
        //System.out.println(apiUrlWithId);


        String jsonResponse = restTemplate.getForObject(apiUrlWithId, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonResponse, BookDetailDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new BookDetailDTO();
        }

    }
}
