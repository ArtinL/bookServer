package com.bookstore.bookserver.providers;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.model.books.BookBrief;
import com.bookstore.bookserver.model.books.BookDetailDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;


@SuppressWarnings("SpellCheckingInspection")
@Component
public class BooksAPIClient {

    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String API_KEY = "AIzaSyCQAK-HfYpmCljoCDooRvPIPQcL7MNRTFk";
    private final RestTemplate restTemplate;

    public BooksAPIClient() {
        this.restTemplate = new RestTemplate();
    }

    public GenericItemDTO[] searchBooks(String query, String page) {
        int index = ((Integer.parseInt(page))-1) * 10;
        String apiUrlWithQuery = API_URL + "?q=" + query + "&printType=books&startIndex=" + index + "&key=" + API_KEY;

        String jsonResponse = restTemplate.getForObject(apiUrlWithQuery, String.class);

        ObjectMapper objectMapper = new ObjectMapper();


        try {
            JsonNode itemsNode = objectMapper.readTree(jsonResponse).get("items");
            ArrayList<GenericItemDTO> bookList = new ArrayList<>();
            for (JsonNode item : itemsNode) {
                BookBrief book = objectMapper.readValue(item.toString(), BookBrief.class);
                bookList.add(book);
            }
            return bookList.toArray(new GenericItemDTO[0]);
        } catch (Exception e) {
            System.out.println("Deserialization failed");
            System.out.println(e.getMessage());
            return new GenericItemDTO[0];
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
            System.out.println("Deserialization failed");
            System.out.println(e.getMessage());
            return new BookDetailDTO();
        }

    }
}
