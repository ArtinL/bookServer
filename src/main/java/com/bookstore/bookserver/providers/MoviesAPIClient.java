package com.bookstore.bookserver.providers;

import com.bookstore.bookserver.model.GenericItemDTO;
import com.bookstore.bookserver.model.movies.MovieBrief;
import com.bookstore.bookserver.model.movies.MovieDetailDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MoviesAPIClient {

    private RestTemplate restTemplate;

    public MoviesAPIClient() {
        this.restTemplate = new RestTemplate();
    }

    private static final String API_URL = "http://www.omdbapi.com/?apikey=520a7bb9&type=movie";

    public GenericItemDTO[] searchMovies(String query, String page) {

        String queryStr = API_URL + "&s=" + query + "&page=" + page;

        String jsonResponse = restTemplate.getForObject(queryStr, String.class);

        List<String> imdbIds = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode searchArray = rootNode.get("Search");

            if (searchArray == null) return new GenericItemDTO[0];

            for (JsonNode movieNode : searchArray) {
                JsonNode imdbIdNode = movieNode.get("imdbID");
                if (imdbIdNode != null) imdbIds.add(imdbIdNode.asText());
            }

        } catch (IOException e) {
            System.out.println("Error parsing JSON response");
        }

        List<GenericItemDTO> items = new ArrayList<>();

        for (String id : imdbIds) {
            String movieJson = restTemplate.getForObject(API_URL + "&i=" + id, String.class);
            MovieBrief movie = null;
            try {
                movie = objectMapper.readValue(movieJson, MovieBrief.class);
            } catch (Exception e) {
                System.out.println("Movies - Error parsing JSON response: " + e.getMessage());
                return new GenericItemDTO[0];
            }

            items.add(new GenericItemDTO(movie));
        }

        return items.toArray(new GenericItemDTO[0]);
    }

    public MovieDetailDTO getMovieDetails(String id) {
        return null;
    }

}
