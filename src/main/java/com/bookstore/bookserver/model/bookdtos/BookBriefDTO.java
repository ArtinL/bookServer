package com.bookstore.bookserver.model.bookdtos;
import com.bookstore.bookserver.entities.FavEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BookBriefDeserializer.class)
public class BookBriefDTO {
    String id;
    String title;
    String[] authors;
    String publishedDate;
    String smallThumbnail;
    double averageRating;
    int ratingsCount;

    public BookBriefDTO() {}

    public BookBriefDTO(String id, String title, String[] authors, String publishedDate, String smallThumbnail, double averageRating, int ratingsCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.smallThumbnail = smallThumbnail;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    public BookBriefDTO(FavEntity fav) {
        this.id = fav.getBookID();
        this.title = fav.getTitle();
        this.authors = fav.getAuthors();
        this.publishedDate = fav.getPublishedDate();
        this.smallThumbnail = fav.getSmallThumbnail();
        this.averageRating = fav.getAverageRating();
        this.ratingsCount = fav.getRatingsCount();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

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

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String toString() {
        return "BookBriefDTO{" +
                "bookID='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", publishedDate='" + publishedDate + '\'' +
                ", smallThumbnail='" + smallThumbnail + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                '}';
    }
}
