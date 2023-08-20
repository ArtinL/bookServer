package com.bookstore.bookserver.entities;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@SuppressWarnings("unused")
@Document(collection = "books")
public class FavEntity {
    @Id
    private String _id; // MongoDB's _id
    private String bookID; // Your application's identifier
    private String userID;
    private String title;
    private String[] authors;
    private String publishedDate;
    private String smallThumbnail;

    public FavEntity(String bookID, String userID, String title, String[] authors, String publishedDate, String smallThumbnail) {
        this.bookID = bookID;
        this.userID = userID;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.smallThumbnail = smallThumbnail;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    @Override
    public String toString() {
        return "FavEntity{" +
                "bookID='" + bookID + '\'' +
                ", userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", publishedDate='" + publishedDate + '\'' +
                ", smallThumbnail='" + smallThumbnail + '\'' +
                '}';
    }
}
