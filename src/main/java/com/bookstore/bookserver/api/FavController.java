package com.bookstore.bookserver.api;
import com.bookstore.bookserver.model.BookBriefDTO;
import com.bookstore.bookserver.service.FavService;
import com.bookstore.bookserver.service.TokenService;
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
    private final TokenService tokenService;

    @Autowired
    public FavController(FavService favService, TokenService tokenService) {
        this.favService = favService;
        this.tokenService = tokenService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createBook(@RequestBody String book, @PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        boolean success = favService.createBook(book, userId);

        if (success) {
            String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUriString();
            return ResponseEntity.created(URI.create(uri)).build();
        } else {
            return ResponseEntity.badRequest().body("Add failed");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BookBriefDTO[]> retrieveBooks(@PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(favService.retrieveBooks(userId));
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "userId") String userId, @PathVariable(name = "id") String id, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        if (favService.deleteBook(userId, id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Delete failed");
        }

    }



}
