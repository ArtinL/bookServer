package com.bookstore.bookserver.repository;

import com.bookstore.bookserver.entities.FavEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FavRepository extends MongoRepository<FavEntity, String> {
    Optional<List<FavEntity>> findByUserID(String userId);
    Optional<FavEntity> findByUserIDAndId(String userId, String entryId);

}
