package com.bookstore.bookserver.api;
import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.service.FavService;
import com.bookstore.bookserver.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "*")
public class FavController {

    private final FavService favService;
    private final TokenService tokenService;

    @Autowired
    public FavController(FavService favService, TokenService tokenService) {
        this.favService = favService;
        this.tokenService = tokenService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createEntry(@RequestBody String book, @PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        boolean success = favService.createEntry(book, userId);

        if (success) {
            String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUriString();
            return ResponseEntity.created(URI.create(uri)).build();
        } else {
            return ResponseEntity.badRequest().body("Add failed");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GenericItemDTO[]> retrieveEntries(@PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(favService.retrieveAllEntries(userId));
    }

    @GetMapping("/books/{userId}")
    public ResponseEntity<GenericItemDTO[]> retrieveBookEntries(@PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(favService.retrieveBookEntries(userId));
    }

    @GetMapping("/movies/{userId}")
    public ResponseEntity<GenericItemDTO[]> retrieveMovieEntries(@PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(favService.retrieveMovieEntries(userId));
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable(name = "userId") String userId, @PathVariable(name = "id") String id, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        if (favService.deleteEntry(userId, id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Delete failed");
        }

    }

    @PostMapping("/match/{userId}")
    public ResponseEntity<String[]> matchAgainstDB(@RequestBody String[] bookIds, @PathVariable(name = "userId") String userId, @RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        if (!userId.equals(extractedUserId)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(favService.matchAgainstDB(bookIds, userId));
    }

    @GetMapping("/test")
    public ResponseEntity<String> testLoggedIn(@RequestHeader("Authorization") String token) {
        String extractedUserId = tokenService.getUserName(token.substring(7));
        return ResponseEntity.ok(extractedUserId);
    }



}
