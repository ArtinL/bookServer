package com.bookstore.bookserver.api;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.model.books.BookDetailDTO;
import com.bookstore.bookserver.model.movies.MovieDetailDTO;
import com.bookstore.bookserver.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public ResponseEntity<GenericItemDTO[]> getBooks(@RequestParam(name = "title") String title,
                                                     @RequestParam(name = "page") String page,
                                                     @RequestParam(name = "type") String type) {
        try {
            GenericItemDTO[] items = type.equals("books") ?
                    searchService.searchBooks(title, page) :
                    searchService.searchMovies(title, page);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> getBookDetails(@PathVariable(name = "id") String id,
                                                        @RequestParam(name = "type") String type) {
        try {
            BookDetailDTO book = searchService.getBookDetails(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping()
//    public ResponseEntity<GenericItemDTO[]> getMovies(@RequestParam(name = "title") String title,
//                                                     @RequestParam(name = "page") String page) {
//        try {
//            GenericItemDTO[] items = searchService.searchMovies(title.replace(" ", "+"), page);
//            return ResponseEntity.ok(items);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MovieDetailDTO> getMovieDetails(@PathVariable(name = "id") String id) {
//        try {
//            MovieDetailDTO movie = searchService.getMovieDetails(id);
//            return ResponseEntity.ok(movie);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
