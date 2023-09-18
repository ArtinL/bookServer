package com.bookstore.bookserver.model.books;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = BookBriefDeserializer.class)

public class BookBrief extends GenericItemDTO {
    public BookBrief() {
        super("books");
    }

    public BookBrief(String id, String displayName, String date, String[] creators, String thumbnail, double averageRating, int ratingsCount) {
        super("books", id, displayName, creators, date, thumbnail, averageRating, ratingsCount);
    }
}
