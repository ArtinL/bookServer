package com.bookstore.bookserver.model.books;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("unused")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BookDetailDeserializer.class)
public class BookDetailDTO extends BookBrief {

    public static class SaleInfo {
        public String country;
        public boolean saleability;
        public Double retailPrice;
        public String buyLink;
    }

    private String publisher;
    private String description;
    private int pageCount;
    private String[] categories;
    private String language;
    private String previewLink;
    private SaleInfo saleInfo;

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
    public SaleInfo getSaleInfo() {
        return saleInfo;
    }
    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }
}


