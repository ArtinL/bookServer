package com.bookstore.bookserver.api;
import com.bookstore.bookserver.model.BookBriefDTO;
import com.bookstore.bookserver.service.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/collection")
@CrossOrigin(origins = "http://localhost:3000")
public class FavController {

    private FavService favService;

    @Autowired
    public FavController(FavService favService) {
        this.favService = favService;
    }

    @PostMapping("/{userId}")
    public void createBook(@RequestBody String book, @PathVariable(name = "userId") String userId) {
        favService.createBook(book, userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BookBriefDTO[]> retrieveBooks(@PathVariable(name = "userId") String userId) {
        return favService.retrieveBooks(userId);

    }


}
