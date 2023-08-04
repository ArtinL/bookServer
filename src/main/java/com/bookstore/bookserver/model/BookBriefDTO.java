package com.bookstore.bookserver.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BookBriefDeserializer.class)
public class BookBriefDTO {
    public String _id;
    public String title;
    public String[] authors;
    public String publishedDate;

    public String smallThumbnail;

    public BookBriefDTO() {}

    public BookBriefDTO(String id, String title, String[] authors, String publishedDate, String smallThumbnail) {
        this._id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.smallThumbnail = smallThumbnail;
    }

}
