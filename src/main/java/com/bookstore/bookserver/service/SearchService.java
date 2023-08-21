package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.bookdtos.BookBriefDTO;
import com.bookstore.bookserver.model.bookdtos.BookDetailDTO;
import com.bookstore.bookserver.providers.BooksAPIClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final BooksAPIClient booksAPIClient;

    public SearchService() {
        this.booksAPIClient = new BooksAPIClient();
    }

    public ResponseEntity<BookBriefDTO[]> searchBooks(String query, String page) {
        BookBriefDTO[] objects = booksAPIClient.searchBooks(query, page);
        return ResponseEntity.ok(objects);
    }

    public ResponseEntity<BookDetailDTO> getBookDetails(String id) {
        BookDetailDTO object = booksAPIClient.getBookDetails(id);
        return ResponseEntity.ok(object);
    }

}
