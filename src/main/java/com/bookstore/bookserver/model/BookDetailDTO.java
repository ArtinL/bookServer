package com.bookstore.bookserver.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDetailDTO {
    String id;
    String title;
    String[] authors;
    String publishedDate;
    String publisher;
    String description;
    int pageCount;
    String[] categories;
    double averageRating;
    int ratingsCount;
    String largeThumbnail;
    String language;
    String previewLink;

    public BookDetailDTO() {}

    public BookDetailDTO(String id, String title, String[] authors, String publishedDate, String publisher,
                         String description, int pageCount, String[] categories, double averageRating,
                         int ratingsCount, String largeThumbnail, String language, String previewLink) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.description = description;
        this.pageCount = pageCount;
        this.categories = categories;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
        this.largeThumbnail = largeThumbnail;
        this.language = language;
        this.previewLink = previewLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getLargeThumbnail() {
        return largeThumbnail;
    }

    public void setLargeThumbnail(String largeThumbnail) {
        this.largeThumbnail = largeThumbnail;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }
}


