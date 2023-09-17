package com.bookstore.bookserver.model;
import com.bookstore.bookserver.entities.FavEntity;
import com.bookstore.bookserver.model.books.BookBrief;
import com.bookstore.bookserver.model.movies.MovieBrief;

import java.util.Arrays;

@SuppressWarnings("unused")

public class GenericItemDTO {
    String type;
    String id;
    String displayName;
    String[] creators;
    String date;
    String thumbnail;
    double averageRating;
    int ratingsCount;

    public GenericItemDTO() {}

    public GenericItemDTO(String type, String id, String displayName, String[] creators, String date, String thumbnail, double averageRating, int ratingsCount) {
        this.type = type;
        this.id = id;
        this.displayName = displayName;
        this.creators = creators;
        this.date = date;
        this.thumbnail = thumbnail;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    public GenericItemDTO(BookBrief book) {
        this.type = "book";
        this.id = book.getId();
        this.displayName = book.getTitle();
        this.creators = book.getAuthors();
        this.date = book.getPublishedDate();
        this.thumbnail = book.getSmallThumbnail();
        this.averageRating = book.getAverageRating();
        this.ratingsCount = book.getRatingsCount();
    }

    public GenericItemDTO(MovieBrief movie) {
        this.type = "movie";
        this.id = movie.getId();
        this.displayName = movie.getTitle();
        this.creators = movie.getDirectors();
        this.date = movie.getDate();
        this.thumbnail = movie.getPosterLink();
        this.averageRating = movie.getImdbRating();
        this.ratingsCount = movie.getImdbVotes();
    }

    public GenericItemDTO(FavEntity fav) {
        this.type = fav.getType();
        this.id = fav.getEntryID();
        this.displayName = fav.getDisplayName();
        this.creators = fav.getCreators();
        this.date = fav.getDate();
        this.thumbnail = fav.getThumbnail();
        this.averageRating = fav.getAverageRating();
        this.ratingsCount = fav.getRatingsCount();
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
                ", title='" + displayName + '\'' +
                ", authors=" + Arrays.toString(creators) +
                ", publishedDate='" + date + '\'' +
                ", smallThumbnail='" + thumbnail + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", ratingsCount='" + ratingsCount + '\'' +
                '}';
    }
}
