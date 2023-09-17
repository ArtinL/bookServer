package com.bookstore.bookserver.model.movies;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieBriefDeserializer extends JsonDeserializer<MovieBrief> {
    @Override
    public MovieBrief deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode rootNode = jsonParser.readValueAsTree();
        MovieBrief movieBrief = new MovieBrief();

        movieBrief.id = rootNode.get("imdbID").asText();
        movieBrief.title = rootNode.get("Title").asText();
        String directorStr = rootNode.get("Director").asText();
        movieBrief.directors = directorStr.split(", ");

        String releasedDateStr = rootNode.get("Released").asText();
        movieBrief.date = formatDate(releasedDateStr);

        movieBrief.posterLink = rootNode.get("Poster").asText();
        movieBrief.imdbRating = Math.floor((rootNode.get("imdbRating").asDouble())) / 2.0;
        movieBrief.imdbVotes = parseImdbVotes(rootNode.get("imdbVotes").asText());

        return movieBrief;
    }

    private String formatDate(String inputDateStr) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy");
            Date inputDate = inputFormat.parse(inputDateStr);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            return outputFormat.format(inputDate);
        } catch (ParseException e) {
            return null;
        }
    }

    private int parseImdbVotes(String imdbVotesStr) {
        try {
            // Remove commas and whitespace from the string and then parse it as an integer
            String cleanedImdbVotesStr = imdbVotesStr.replaceAll("[,\\s]+", "");
            return Integer.parseInt(cleanedImdbVotesStr);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing imdbVotes: " + e.getMessage());
            return 0; // Handle the parsing error
        }
    }

}
