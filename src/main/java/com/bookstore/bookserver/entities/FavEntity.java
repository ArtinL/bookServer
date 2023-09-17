package com.bookstore.bookserver.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@SuppressWarnings("unused")
@Document(collection = "books")
public class FavEntity {
    @Id
    private String _id; // MongoDB's _id
    private String type;
    private String entryID;
    private String userID;
    private String displayName;
    private String[] creators;
    private String date;
    private String thumbnail;
    private int ratingsCount;
    private double averageRating;

    public FavEntity() {}

    public FavEntity(String type, String entryID, String userID, String displayName,
                     String[] creators, String date, String thumbnail, double averageRating, int ratingsCount) {
        this.type = type;
        this.entryID = entryID;
        this.userID = userID;
        this.displayName = displayName;
        this.creators = creators;
        this.date = date;
        this.thumbnail = thumbnail;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntryID() {
        return entryID;
    }

    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String[] getCreators() {
        return creators;
    }

    public void setCreators(String[] creators) {
        this.creators = creators;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "FavEntity{" +
                "bookID='" + entryID + '\'' +
                ", userID='" + userID + '\'' +
                ", title='" + displayName + '\'' +
                ", authors=" + Arrays.toString(creators) +
                ", publishedDate='" + date + '\'' +
                ", smallThumbnail='" + thumbnail + '\'' +
                '}';
    }
}
