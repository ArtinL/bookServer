package com.bookstore.bookserver.model.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = MovieBriefDeserializer.class)
public class MovieBrief {
    String id;
    String title;
    String[] directors;
    String date;
    String posterLink;
    double imdbRating;
    int imdbVotes;

    public MovieBrief() {}

    public MovieBrief(String id, String title, String[] directors, String date, String posterLink, double imdbRating, int imdbVotes) {
        this.id = id;
        this.title = title;
        this.directors = directors;
        this.date = date;
        this.posterLink = posterLink;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @Override
    public String toString() {
        return "MovieBrief{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", director='" + directors + '\'' +
                ", date='" + date + '\'' +
                ", posterLink='" + posterLink + '\'' +
                ", imdbRating=" + imdbRating +
                ", imdbVotes=" + imdbVotes +
                '}';
    }
}
