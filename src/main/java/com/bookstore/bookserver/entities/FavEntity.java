package com.bookstore.bookserver.entities;
import com.bookstore.bookserver.model.GenericItemDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@SuppressWarnings("unused")
@Document(collection = "books")
public class FavEntity extends GenericItemDTO {
    @Id
    private String _id;
    private String userID;

    public FavEntity() {}

    public FavEntity(String type, String id, String title, String[] creators, String date, String thumbnail, double averageRating, int ratingsCount, String userID) {
        super(type, id, title, creators, date, thumbnail, averageRating, ratingsCount);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
