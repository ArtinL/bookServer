package com.bookstore.bookserver.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDetailDTO {
    public String _id;
    public String title;
    public String[] authors;
    public String publishedDate;
    public String publisher;
    public String description;
    public int pageCount;
    public String[] categories;
    public double averageRating;
    public int ratingsCount;
    public String thumbnail;
    public String language;
    public String previewLink;

    public BookDetailDTO() {}

    public BookDetailDTO(String id, String title, String[] authors, String publishedDate, String publisher,
                         String description, int pageCount, String[] categories, double averageRating,
                         int ratingsCount, String thumbnail, String language, String previewLink) {
        this._id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
        this.thumbnail = thumbnail;
        this.language = language;
        this.previewLink = previewLink;
    }


}


