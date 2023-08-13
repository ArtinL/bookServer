package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.BookBriefDTO;
import com.bookstore.bookserver.model.FavModel;
import com.bookstore.bookserver.repository.FavRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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


    public boolean createBook(String book, String userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        FavTemplate bookObj;
        try {
            bookObj = objectMapper.readValue(book, FavTemplate.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FavOldService.createBook() exception");
            return false;
        }

        FavModel favModel = convertToDataModel(bookObj, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FavModel> request = new HttpEntity<>(favModel, headers);
        String SERVICE_URL = "https://ys67ilhostajlhkurd4czfisvu0foqtw.lambda-url.us-east-2.on.aws/new-book";
        restTemplate.postForObject(SERVICE_URL, request, FavModel.class);

        return true;

    }

    public BookBriefDTO[] retrieveBooks(String userId) {
        return favRepository.findByUserID(userId)
                .stream()
                .map(this::convertToBriefDTO)
                .toArray(BookBriefDTO[]::new);
    }

    public boolean deleteBook(String userID, String bookID) {
        FavModel bookToDelete = favRepository.findByUserIDAndBookID(userID, bookID);

        if (bookToDelete != null) {
            favRepository.delete(bookToDelete);
            return true;
        } else return false;

    }

    private BookBriefDTO convertToBriefDTO(FavModel favModel) {
        BookBriefDTO briefDTO = new BookBriefDTO();
        briefDTO.setId(favModel.getBookID()); // Use your application's identifier
        briefDTO.setTitle(favModel.getTitle());
        briefDTO.setAuthors(favModel.getAuthors());
        briefDTO.setPublishedDate(favModel.getPublishedDate());
        briefDTO.setSmallThumbnail(favModel.getSmallThumbnail());
        return briefDTO;
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
