package com.bookstore.bookserver.repository;

import com.bookstore.bookserver.model.FavModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavRepository extends MongoRepository<FavModel, String> {
    List<FavModel> findByUserID(String userId);
    FavModel findByUserIDAndBookID(String userId, String bookId);

}
