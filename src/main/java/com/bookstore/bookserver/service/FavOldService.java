package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.FavModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FavOldService {

    private static class FavTemplate {
        String id;
        String title;
        String[] authors;
        String publishedDate;
        String smallThumbnail;

        public void setId(String id) {
            this.id = id;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public void setAuthors(String[] authors) {
            this.authors = authors;
        }
        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }
        public void setSmallThumbnail(String smallThumbnail) {
            this.smallThumbnail = smallThumbnail;
        }
    }

    private final String URL = "http://localhost:8090/books/";
    private final RestTemplate restTemplate;
    public FavOldService() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<String> retrieveBooks(String userId) {
        System.out.println("FavOldService.retrieveBooks() called");
        String url = URL + userId;

        return restTemplate.getForEntity(url, String.class);

    }

    public void deleteBook(String userID, String id) {
        restTemplate.delete(URL + userID + "/" + id);
    }

    private FavModel convertToDataModel (FavTemplate bookBriefDTO, String userID) {
        return new FavModel(
                bookBriefDTO.id,
                userID,
                bookBriefDTO.title,
                bookBriefDTO.authors,
                bookBriefDTO.publishedDate,
                bookBriefDTO.smallThumbnail
        );
    }

}

