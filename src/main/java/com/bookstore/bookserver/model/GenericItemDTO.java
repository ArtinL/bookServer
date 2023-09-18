package com.bookstore.bookserver.model;
import com.bookstore.bookserver.entities.FavEntity;

import java.util.Arrays;

@SuppressWarnings("unused")

public class GenericItemDTO {
    private String type;
    private String id;
    private String title;
    private String[] creators;
    private String date;
    private String thumbnail;
    private double averageRating;
    private int ratingsCount;

    public GenericItemDTO() {}

    public GenericItemDTO(String type) {
        this.type = type;
    }

    public GenericItemDTO(String type, String id, String title, String[] creators, String date, String thumbnail, double averageRating, int ratingsCount) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.creators = creators;
        this.date = date;
        this.thumbnail = thumbnail;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getCreators() {
        return creators;
    }

    public String getDate() {
        return date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreators(String[] creators) {
        this.creators = creators;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(creators) +
                ", publishedDate='" + date + '\'' +
                ", smallThumbnail='" + thumbnail + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                '}';
    }
}
