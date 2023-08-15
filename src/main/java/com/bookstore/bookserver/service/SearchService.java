package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.*;
import com.bookstore.bookserver.providers.BooksAPIClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final BooksAPIClient booksAPIClient;

    public SearchService() {
        this.booksAPIClient = new BooksAPIClient();
    }

    public ResponseEntity<BookBriefDTO[]> searchBooks(String query) {
        BookBriefDTO[] objects = booksAPIClient.searchBooks(query);
        System.out.println(objects[0]);
        return ResponseEntity.ok(objects);
    }

    public ResponseEntity<BookDetailDTO> getBookDetails(String id) {
        BookDetailDTO object = booksAPIClient.getBookDetails(id);
        return ResponseEntity.ok(object);
    }

}
