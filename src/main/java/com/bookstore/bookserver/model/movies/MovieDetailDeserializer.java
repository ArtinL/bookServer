package com.bookstore.bookserver.model.movies;

import com.bookstore.bookserver.util.DeserializerUtility;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetailDeserializer extends JsonDeserializer<MovieDetailDTO> {
    @Override
    public MovieDetailDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = jsonParser.readValueAsTree();
        MovieDetailDTO movieDetail = new MovieDetailDTO();

        movieDetail.setId(DeserializerUtility.safeGetText(rootNode, "imdbID"));
        movieDetail.setTitle(DeserializerUtility.safeGetText(rootNode, "Title"));
        String directorStr = DeserializerUtility.safeGetText(rootNode, "Director");
        movieDetail.setCreators(directorStr.split(", "));
        String releasedDateStr = DeserializerUtility.safeGetText(rootNode, "Released");
        movieDetail.setDate(formatDate(releasedDateStr));
        movieDetail.setThumbnail(DeserializerUtility.safeGetText(rootNode, "Poster"));
        movieDetail.setAverageRating(Math.floor((DeserializerUtility.safeGetDouble(rootNode, "imdbRating"))) / 2.0);
        movieDetail.setRatingsCount(parseImdbVotes(DeserializerUtility.safeGetText(rootNode, "imdbVotes")));
        String genresStr = DeserializerUtility.safeGetText(rootNode, "Genre");
        movieDetail.setGenres(genresStr.split(", "));
        movieDetail.setMpaaRated(DeserializerUtility.safeGetText(rootNode, "Rated"));
        String runtimeStr = DeserializerUtility.safeGetText(rootNode, "Runtime");
        movieDetail.setRuntime(Integer.parseInt(runtimeStr.split(" ")[0]));
        String writersStr = DeserializerUtility.safeGetText(rootNode, "Writer");
        movieDetail.setWriters(writersStr.split(", "));
        String starringStr = DeserializerUtility.safeGetText(rootNode, "Actors");
        movieDetail.setStarring(starringStr.split(", "));
        movieDetail.setAwards(DeserializerUtility.safeGetText(rootNode, "Awards"));
        movieDetail.setPlot(DeserializerUtility.safeGetText(rootNode, "Plot"));
        String languagesStr = DeserializerUtility.safeGetText(rootNode, "Language");
        movieDetail.setLanguages(languagesStr.split(", "));


        return movieDetail;
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
