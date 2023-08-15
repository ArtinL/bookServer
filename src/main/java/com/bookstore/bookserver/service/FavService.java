package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.BookBriefDTO;
import com.bookstore.bookserver.entities.FavEntity;
import com.bookstore.bookserver.repository.FavRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class FavService {

    @SuppressWarnings("unused")
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

    private final FavRepository favRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public FavService(FavRepository favRepository) {
        this.restTemplate = new RestTemplate();
        this.favRepository = favRepository;
    }


    public boolean createBook(String bookJson, String userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        FavTemplate bookObj;
        try {
            bookObj = objectMapper.readValue(bookJson, FavTemplate.class);
        } catch (Exception e) {
            System.out.println("FavOldService.createBook() exception: " + e.getMessage());
            return false;
        }

        FavEntity favEntity = convertToDataEntity(bookObj, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FavEntity> request = new HttpEntity<>(favEntity, headers);


        String serviceUrl = "https://ys67ilhostajlhkurd4czfisvu0foqtw.lambda-url.us-east-2.on.aws/new-book";
        restTemplate.postForObject(serviceUrl, request, FavEntity.class);

        return true;

    }



    public BookBriefDTO[] retrieveBooks(String userId) {

        Optional<List<FavEntity>> optionalFavEntities = favRepository.findByUserID(userId);
        if (optionalFavEntities.isPresent()) {
            List<FavEntity> favEntities = optionalFavEntities.get();
            return favEntities.stream().map(this::convertToDTO).toArray(BookBriefDTO[]::new);

        } else return null;

    }

    public boolean deleteBook(String userID, String bookID) {
        Optional<FavEntity> optionalFavEntity = favRepository.findByUserIDAndBookID(userID, bookID);
        if (optionalFavEntity.isPresent()) {
            FavEntity favEntity = optionalFavEntity.get();
            favRepository.delete(favEntity);
            return true;
        } else return false;

    }

    private BookBriefDTO convertToDTO(FavEntity favEntity) {
        return new BookBriefDTO(
                favEntity.getBookID(),
                favEntity.getTitle(),
                favEntity.getAuthors(),
                favEntity.getPublishedDate(),
                favEntity.getSmallThumbnail()
        );
    }

    private FavEntity convertToDataEntity(FavTemplate bookBriefDTO, String userID) {
        return new FavEntity(
                bookBriefDTO.id,
                userID,
                bookBriefDTO.title,
                bookBriefDTO.authors,
                bookBriefDTO.publishedDate,
                bookBriefDTO.smallThumbnail
        );
    }



}
