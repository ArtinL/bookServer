package com.bookstore.bookserver.api;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.model.books.BookDetailDTO;
import com.bookstore.bookserver.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookSearchController {

    private final SearchService searchService;

    @Autowired
    public BookSearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public ResponseEntity<GenericItemDTO[]> getBooks(@RequestParam(name = "title") String title,
                                                     @RequestParam(name = "page") String page) {
        try {
            GenericItemDTO[] items = searchService.searchBooks(title, page);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> getBookDetails(@PathVariable(name = "id") String id) {
        try {
            BookDetailDTO book = searchService.getBookDetails(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
