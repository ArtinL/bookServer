package com.bookstore.bookserver.api;

import com.bookstore.bookserver.model.bookdtos.BookBriefDTO;
import com.bookstore.bookserver.model.bookdtos.BookDetailDTO;
import com.bookstore.bookserver.service.SearchService;

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
    public ResponseEntity<BookBriefDTO[]> getBooks(@RequestParam(name = "title") String title,
                                                   @RequestParam(name = "page") String page) {
        try {
            return searchService.searchBooks(title.replace(" ", "+"), page);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> getBookDetails(@PathVariable(name = "id") String id) {
        return searchService.getBookDetails(id);
    }
}
