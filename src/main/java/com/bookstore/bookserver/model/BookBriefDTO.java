package com.bookstore.bookserver.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BookBriefDeserializer.class)
public class BookBriefDTO {
    @JsonProperty("id")
    String id;
    @JsonProperty("title")
    String title;
    @JsonProperty("authors")
    String[] authors;
    @JsonProperty("publishedDate")
    String publishedDate;
    @JsonProperty("smallThumbnail")
    String smallThumbnail;

    public BookBriefDTO() {}

    public BookBriefDTO(String id, String title, String[] authors, String publishedDate, String smallThumbnail) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.smallThumbnail = smallThumbnail;
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

    public String toString() {
        return "BookBriefDTO{" +
                "bookID='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", publishedDate='" + publishedDate + '\'' +
                ", smallThumbnail='" + smallThumbnail + '\'' +
                '}';
    }
}
