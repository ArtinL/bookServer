package com.bookstore.bookserver.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BookDetailDeserializer extends JsonDeserializer<BookDetailDTO> {

    @Override
    public BookDetailDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = objectMapper.readTree(jsonParser);

        BookDetailDTO bookDetailDTO = new BookDetailDTO();
        bookDetailDTO._id = node.get("id").asText();
        bookDetailDTO.title = node.get("volumeInfo").get("title").asText();
        bookDetailDTO.publishedDate = node.get("volumeInfo").get("publishedDate").asText();
        bookDetailDTO.publisher = node.get("volumeInfo").get("publisher").asText();
        bookDetailDTO.description = node.get("volumeInfo").get("description").asText();
        bookDetailDTO.pageCount = node.get("volumeInfo").get("pageCount").asInt();
        bookDetailDTO.averageRating = node.get("volumeInfo").get("averageRating").asDouble();
        bookDetailDTO.ratingsCount = node.get("volumeInfo").get("ratingsCount").asInt();
        bookDetailDTO.language = node.get("volumeInfo").get("language").asText();
        bookDetailDTO.previewLink = node.get("volumeInfo").get("previewLink").asText();

        JsonNode authorsNode = node.get("volumeInfo").get("authors");
        if (authorsNode == null) bookDetailDTO.authors = null;
        bookDetailDTO.authors = objectMapper.treeToValue(authorsNode, String[].class);

        JsonNode categoriesNode = node.get("volumeInfo").get("categories");
        if (categoriesNode == null) bookDetailDTO.categories = null;
        bookDetailDTO.categories = objectMapper.treeToValue(categoriesNode, String[].class);

        JsonNode imageLinksNode = node.get("volumeInfo").get("imageLinks");
        if (imageLinksNode == null) {
            bookDetailDTO.thumbnail = null;

        } else {
            JsonNode smallThumbnailNode = imageLinksNode.get("smallThumbnail");
            if (smallThumbnailNode != null) bookDetailDTO.thumbnail = smallThumbnailNode.asText();
            else bookDetailDTO.thumbnail = null;

        }


        return bookDetailDTO;
    }
}
