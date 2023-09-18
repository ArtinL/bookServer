package com.bookstore.bookserver.model.movies;

import com.bookstore.bookserver.util.DeserializerUtility;
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

        movieBrief.setId(DeserializerUtility.safeGetText(rootNode, "imdbID"));
        movieBrief.setTitle(DeserializerUtility.safeGetText(rootNode, "Title"));
        String directorStr = DeserializerUtility.safeGetText(rootNode, "Director");
        movieBrief.setCreators(directorStr.split(", "));

        String releasedDateStr = DeserializerUtility.safeGetText(rootNode, "Released");
        movieBrief.setDate(formatDate(releasedDateStr));

        movieBrief.setThumbnail(DeserializerUtility.safeGetText(rootNode, "Poster"));
        movieBrief.setAverageRating(Math.floor((DeserializerUtility.safeGetDouble(rootNode, "imdbRating"))) / 2.0);
        movieBrief.setRatingsCount(parseImdbVotes(DeserializerUtility.safeGetText(rootNode, "imdbVotes")));

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
