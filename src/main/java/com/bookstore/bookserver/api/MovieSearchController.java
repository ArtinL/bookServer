package com.bookstore.bookserver.api;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.model.books.BookDetailDTO;
import com.bookstore.bookserver.model.movies.MovieDetailDTO;
import com.bookstore.bookserver.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*")
public class MovieSearchController {

    private final SearchService searchService;

    @Autowired
    public MovieSearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping()
    public ResponseEntity<GenericItemDTO[]> getBooks(@RequestParam(name = "title") String title,
                                                     @RequestParam(name = "page") String page) {
        try {
            GenericItemDTO[] items = searchService.searchMovies(title, page);
                    searchService.searchMovies(title, page);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailDTO> getBookDetails(@PathVariable(name = "id") String id) {
        try {
            MovieDetailDTO book = searchService.getMovieDetails(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/movies")
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
