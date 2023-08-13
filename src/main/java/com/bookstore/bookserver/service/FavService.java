package com.bookstore.bookserver.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.bookstore.bookserver.model.BookBriefDTO;

import java.util.Arrays;

@Component
public class FavService {
    private  RestTemplate restTemplate;
    public FavService() {
        this.restTemplate = new RestTemplate();
    }
    private static class JSONTemplate {
        public String _id;
        public String userID;
        public String title;
        public String[] authors;
        public String publishedDate;
        public String smallThumbnail;

        public JSONTemplate() {}

        public JSONTemplate(String _id, String userID, String title, String[] authors, String publishedDate, String smallThumbnail) {
            this._id = _id;
            this.userID = userID;
            this.title = title;
            this.authors = authors;
            this.publishedDate = publishedDate;
            this.smallThumbnail = smallThumbnail;
        }

    }
    public void createBook(String book, String userId) {
        System.out.println("FavService.createBook() called");
        System.out.println("book = " + book);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(book, headers);
        restTemplate.postForObject("http://localhost:8090/books/" + userId, request, String.class);


    }


    public ResponseEntity<BookBriefDTO[]> retrieveBooks(String userId) {
        System.out.println("FavService.retrieveBooks() called");
        String url = "http://localhost:8090/books/" + userId;

        ResponseEntity<JSONTemplate[]> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JSONTemplate[]>() {}
        );

        System.out.println(Arrays.toString(responseEntity.getBody()));

        JSONTemplate[] jsonTemplates = responseEntity.getBody();
        if (jsonTemplates == null) {
            return null;
        }

        // Convert JSONTemplate objects to an array of BookBriefDTO
        BookBriefDTO[] bookBriefDTOs = new BookBriefDTO[jsonTemplates.length];
        for (int i = 0; i < jsonTemplates.length; i++) {
            JSONTemplate jsonTemplate = jsonTemplates[i];
            BookBriefDTO bookBriefDTO = new BookBriefDTO();
            bookBriefDTO.setId(jsonTemplate._id);
            bookBriefDTO.setTitle(jsonTemplate.title);
            bookBriefDTO.setAuthors(jsonTemplate.authors);
            bookBriefDTO.setPublishedDate(jsonTemplate.publishedDate);
            bookBriefDTO.setSmallThumbnail(jsonTemplate.smallThumbnail);
            // Set other fields as needed

            bookBriefDTOs[i] = bookBriefDTO;
        }

        System.out.println(bookBriefDTOs[0].getId());

        return ResponseEntity.ok(bookBriefDTOs);
    }

    public void updateBook(String id, BookBriefDTO book) {
        // TODO Auto-generated method stub
    }

    public void deleteBook(String id) {
        // TODO Auto-generated method stub
    }

}

