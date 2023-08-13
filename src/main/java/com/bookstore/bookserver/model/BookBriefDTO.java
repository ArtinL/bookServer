package com.bookstore.bookserver.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BookBriefDeserializer.class)
public class BookBriefDTO {
    String _id;
    String title;
    String[] authors;
    String publishedDate;
    String smallThumbnail;

    public BookBriefDTO() {}

    public BookBriefDTO(String id, String title, String[] authors, String publishedDate, String smallThumbnail) {
        this._id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.smallThumbnail = smallThumbnail;
    }

    public String getId() {
        return _id;
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

    public void setId(String _id) {
        this._id = _id;
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
}
