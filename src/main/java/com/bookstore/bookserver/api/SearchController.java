package com.bookstore.bookserver.api;

import com.bookstore.bookserver.service.SearchService;
import com.bookstore.bookserver.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<BookBriefDTO[]> getBooks(@RequestParam(name = "title") String title) {
        return searchService.searchBooks(title.replace(" ", "+"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> getBookDetails(@PathVariable(name = "id") String id) {
        return searchService.getBookDetails(id);
    }
}
