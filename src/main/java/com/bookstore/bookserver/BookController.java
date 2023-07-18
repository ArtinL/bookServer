package com.bookstore.bookserver;

import com.bookstore.bookserver.api.GoogleBooksApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final GoogleBooksApiClient googleBooksApiClient;

    @Autowired
    public BookController(GoogleBooksApiClient googleBooksApiClient) {
        System.out.println("BookController.BookController() called");
        this.googleBooksApiClient = googleBooksApiClient;
    }

    @GetMapping
    public ResponseEntity<String> searchBooksByTitle(@RequestParam("title") String title) {
        System.out.println("API called with title = " + title);
        String apiResponse = googleBooksApiClient.searchBooksByTitle(title);
        return ResponseEntity.ok(apiResponse);
    }
}
