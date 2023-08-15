package com.bookstore.bookserver.api;
import com.bookstore.bookserver.model.BookBriefDTO;
import com.bookstore.bookserver.service.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:3000")
public class FavController {

    private final FavService favService;

    @Autowired
    public FavController(FavService favService) {
        this.favService = favService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createBook(@RequestBody String book, @PathVariable(name = "userId") String userId) {
        boolean success = favService.createBook(book, userId);

        if (success) {
            String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUriString();
            return ResponseEntity.created(URI.create(uri)).build();
        } else {
            return ResponseEntity.badRequest().body("Add failed");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BookBriefDTO[]> retrieveBooks(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(favService.retrieveBooks(userId));
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "userId") String userID, @PathVariable(name = "id") String id) {
        if (favService.deleteBook(userID, id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Delete failed");
        }

    }



}
