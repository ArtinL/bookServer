package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.bookdtos.BookBriefDTO;
import com.bookstore.bookserver.entities.FavEntity;
import com.bookstore.bookserver.repository.FavRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FavService {
    private final FavRepository favRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public FavService(FavRepository favRepository) {
        this.restTemplate = new RestTemplate();
        this.favRepository = favRepository;
    }


    public boolean createBook(String bookJson, String userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        BookBriefDTO bookObj;
        try {
            bookObj = objectMapper.readValue(bookJson, BookBriefDTO.class);
        } catch (Exception e) {
            System.out.println("FavOldService.createBook() exception: " + e.getMessage());
            return false;
        }

        if (!isUnique(bookObj.getId(), userId)) return false;
        FavEntity favEntity = convertToDataEntity(bookObj, userId);
        favRepository.save(favEntity);


//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<FavEntity> request = new HttpEntity<>(favEntity, headers);
//
//        String serviceUrl = "https://ys67ilhostajlhkurd4czfisvu0foqtw.lambda-url.us-east-2.on.aws/new-book";
//        restTemplate.postForObject(serviceUrl, request, FavEntity.class);

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

    public String[] matchAgainstDB(String[] bookIds, String userId) {
        Optional<List<FavEntity>> optionalFavEntities = favRepository.findByUserID(userId);
        if (optionalFavEntities.isPresent()) {
            List<FavEntity> favEntities = optionalFavEntities.get();
            return favEntities.stream().map(FavEntity::getBookID).filter(bookId -> {
                for (String id : bookIds) {
                    if (id.equals(bookId)) return true;
                }
                return false;
            }).toArray(String[]::new);

        } else return null;
    }

    private BookBriefDTO convertToDTO(FavEntity favEntity) {
        return new BookBriefDTO(
                favEntity.getBookID(),
                favEntity.getTitle(),
                favEntity.getAuthors(),
                favEntity.getPublishedDate(),
                favEntity.getSmallThumbnail(),
                favEntity.getAverageRating(),
                favEntity.getRatingsCount()
        );
    }

    private FavEntity convertToDataEntity(BookBriefDTO bookBriefDTO, String userID) {
        return new FavEntity(
                bookBriefDTO.getId(),
                userID,
                bookBriefDTO.getTitle(),
                bookBriefDTO.getAuthors(),
                bookBriefDTO.getPublishedDate(),
                bookBriefDTO.getSmallThumbnail(),
                bookBriefDTO.getAverageRating(),
                bookBriefDTO.getRatingsCount()
        );
    }

    private boolean isUnique(String bookID, String userID) {
        return true;
        //Optional<FavEntity> optionalFavEntity = favRepository.findByUserIDAndBookID(userID, bookID);
        //return optionalFavEntity.isEmpty();
    }



}
