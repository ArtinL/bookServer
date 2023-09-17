package com.bookstore.bookserver.service;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.model.books.BookDetailDTO;
import com.bookstore.bookserver.model.movies.MovieDetailDTO;
import com.bookstore.bookserver.providers.BooksAPIClient;
import com.bookstore.bookserver.providers.MoviesAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final BooksAPIClient booksAPIClient;
    private final MoviesAPIClient moviesAPIClient;

    @Autowired
    public SearchService(BooksAPIClient booksAPIClient, MoviesAPIClient moviesAPIClient) {
        this.booksAPIClient = booksAPIClient;
        this.moviesAPIClient = moviesAPIClient;
    }

    public GenericItemDTO[] searchBooks(String query, String page) {
        return booksAPIClient.searchBooks(query, page);
    }

    public BookDetailDTO getBookDetails(String id) {
        return booksAPIClient.getBookDetails(id);
    }

    public GenericItemDTO[] searchMovies(String query, String page) {
        return moviesAPIClient.searchMovies(query, page);
    }

    public MovieDetailDTO getMovieDetails(String id) {
        return moviesAPIClient.getMovieDetails(id);
    }

}
