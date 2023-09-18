package com.bookstore.bookserver.model.movies;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = MovieBriefDeserializer.class)
public class MovieBrief extends GenericItemDTO {

    public MovieBrief() {
        super("movies");
    }

    public MovieBrief(String id, String displayName, String date, String[] creators, String thumbnail, double averageRating, int ratingsCount) {
        super("movies", id, displayName, creators, date, thumbnail, averageRating, ratingsCount);
    }
}
