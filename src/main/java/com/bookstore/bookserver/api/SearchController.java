package com.bookstore.bookserver.api;

import com.bookstore.bookserver.service.BookService;
import com.bookstore.bookserver.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    private final BookService bookService;

    @Autowired
    public SearchController() {
        this.bookService = new BookService();
    }

    @GetMapping
    public ResponseEntity<BookBriefDTO[]> getBooks(@RequestParam(name = "title") String title) {
        return bookService.searchBooks(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> getBookDetails(@PathVariable(name = "id") String id) {
        return bookService.getBookDetails(id);
    }
}
