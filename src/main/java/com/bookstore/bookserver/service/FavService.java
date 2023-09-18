package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.entities.FavEntity;
import com.bookstore.bookserver.repository.FavRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FavService {
    private final FavRepository favRepository;
    //private final RestTemplate restTemplate;

    @Autowired
    public FavService(FavRepository favRepository) {
        //this.restTemplate = new RestTemplate();
        this.favRepository = favRepository;
    }


    public boolean createEntry(String bookJson, String userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        FavEntity entry;
        try {
            entry = objectMapper.readValue(bookJson, FavEntity.class);
        } catch (Exception e) {
            System.out.println("FavOldService.createBook() exception: " + e.getMessage());
            return false;
        }

        if (!isUnique(entry.getId(), userId)) return false;
        entry.setUserID(userId);
        favRepository.save(entry);


//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<FavEntity> request = new HttpEntity<>(favEntity, headers);
//
//        String serviceUrl = "https://ys67ilhostajlhkurd4czfisvu0foqtw.lambda-url.us-east-2.on.aws/new-book";
//        restTemplate.postForObject(serviceUrl, request, FavEntity.class);

        return true;

    }



    public GenericItemDTO[] retrieveAllEntries(String userId) {

        Optional<List<FavEntity>> optionalFavEntities = favRepository.findByUserID(userId);
        if (optionalFavEntities.isPresent()) {
            List<FavEntity> favEntities = optionalFavEntities.get();
            return favEntities.stream().map(this::convertToDTO).toArray(GenericItemDTO[]::new);

        } else return null;
    }

    public GenericItemDTO[] retrieveBookEntries(String userId) {

        Optional<List<FavEntity>> optionalFavEntities = favRepository.findByUserID(userId);
        if (optionalFavEntities.isPresent()) {
            List<FavEntity> favEntities = optionalFavEntities.get();
            return favEntities.stream().filter(favEntity -> favEntity.getType().equals("books")).map(this::convertToDTO).toArray(GenericItemDTO[]::new);

        } else return null;
    }

    public GenericItemDTO[] retrieveMovieEntries(String userId) {

        Optional<List<FavEntity>> optionalFavEntities = favRepository.findByUserID(userId);
        if (optionalFavEntities.isPresent()) {
            List<FavEntity> favEntities = optionalFavEntities.get();
            return favEntities.stream().filter(favEntity -> favEntity.getType().equals("movies")).map(this::convertToDTO).toArray(GenericItemDTO[]::new);

        } else return null;
    }

    public boolean deleteEntry(String userID, String entryID) {
        Optional<FavEntity> optionalFavEntity = favRepository.findByUserIDAndId(userID, entryID);
        if (optionalFavEntity.isPresent()) {
            FavEntity favEntity = optionalFavEntity.get();
            favRepository.delete(favEntity);
            return true;
        } else return false;

    }

    public String[] matchAgainstDB(String[] entryIDs, String userId) {
        Optional<List<FavEntity>> optionalFavEntities = favRepository.findByUserID(userId);
        if (optionalFavEntities.isPresent()) {
            List<FavEntity> favEntities = optionalFavEntities.get();
            return favEntities.stream().map(FavEntity::getId).filter(entryID -> {
                for (String id : entryIDs) {
                    if (id.equals(entryID)) return true;
                }
                return false;
            }).toArray(String[]::new);

        } else return null;
    }

    private GenericItemDTO convertToDTO(FavEntity favEntity) {
        return new GenericItemDTO(
                favEntity.getType(),
                favEntity.getId(),
                favEntity.getTitle(),
                favEntity.getCreators(),
                favEntity.getDate(),
                favEntity.getThumbnail(),
                favEntity.getAverageRating(),
                favEntity.getRatingsCount()
        );
    }

//    private FavEntity convertToDataEntity(GenericItemDTO genericItemDTO, String userID) {
//        return new FavEntity(
//                genericItemDTO.getType(),
//                genericItemDTO.getId(),
//                userID,
//                genericItemDTO.getTitle(),
//                genericItemDTO.getCreators(),
//                genericItemDTO.getDate(),
//                genericItemDTO.getThumbnail(),
//                genericItemDTO.getAverageRating(),
//                genericItemDTO.getRatingsCount()
//        );
//    }

    private boolean isUnique(String bookID, String userID) {
        return true;
        //Optional<FavEntity> optionalFavEntity = favRepository.findByUserIDAndBookID(userID, bookID);
        //return optionalFavEntity.isEmpty();
    }



}
