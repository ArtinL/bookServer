package com.bookstore.bookserver.api;
import com.bookstore.bookserver.model.BookBriefDTO;
import com.bookstore.bookserver.service.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
@CrossOrigin(origins = "http://localhost:3000")
public class FavController {

    private final FavService favService;

    @Autowired
    public FavController(FavService favService) {
        this.favService = favService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createBook(@RequestBody String book, @PathVariable(name = "userId") String userId) {
        if (favService.createBook(book, userId)) {
            return ResponseEntity.ok("Book added to collection");
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
            return ResponseEntity.ok("Book deleted from collection");
        } else {
            return ResponseEntity.badRequest().body("Delete failed");
        }

    }


}
