package com.bookstore.bookserver.model.books;

import com.bookstore.bookserver.util.DeserializerUtility;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class BookBriefDeserializer extends JsonDeserializer<BookBrief> {

    @Override
    public BookBrief deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = objectMapper.readTree(jsonParser);

        BookBrief book = new BookBrief();

        boolean isGoogle = node.get("volumeInfo") != null;

        book.id = DeserializerUtility.safeGetText(node, "id");

        JsonNode parent =  isGoogle ? node.get("volumeInfo") : node;

        book.title = DeserializerUtility.safeGetText(parent, "title");
        book.publishedDate = DeserializerUtility.safeGetText(parent, "publishedDate");
        book.authors = DeserializerUtility.safeGetArray(parent, "authors", objectMapper);
        if (isGoogle) book.smallThumbnail = DeserializerUtility.safeGetOutOfObject(parent, "imageLinks", "smallThumbnail");
        else book.smallThumbnail = DeserializerUtility.safeGetText(parent, "smallThumbnail");
        book.averageRating = DeserializerUtility.safeGetDouble(parent, "averageRating");
        book.ratingsCount = DeserializerUtility.safeGetInt(parent, "ratingsCount");

        return book;

    }






}
