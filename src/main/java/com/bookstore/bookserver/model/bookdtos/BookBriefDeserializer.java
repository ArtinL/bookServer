package com.bookstore.bookserver.model.bookdtos;

import com.bookstore.bookserver.util.DeserializerUtility;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class BookBriefDeserializer extends JsonDeserializer<BookBriefDTO> {

    @Override
    public BookBriefDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = objectMapper.readTree(jsonParser);

        BookBriefDTO bookBriefDTO = new BookBriefDTO();

        boolean isGoogle = node.get("volumeInfo") != null;

        bookBriefDTO.id = DeserializerUtility.safeGetText(node, "id");

        JsonNode parent =  isGoogle ? node.get("volumeInfo") : node;

        bookBriefDTO.title = DeserializerUtility.safeGetText(parent, "title");
        bookBriefDTO.publishedDate = DeserializerUtility.safeGetText(parent, "publishedDate");
        bookBriefDTO.authors = DeserializerUtility.safeGetArray(parent, "authors", objectMapper);
        if (isGoogle) bookBriefDTO.smallThumbnail = DeserializerUtility.safeGetOutOfObject(parent, "imageLinks", "smallThumbnail");
        else bookBriefDTO.smallThumbnail = DeserializerUtility.safeGetText(parent, "smallThumbnail");
        bookBriefDTO.averageRating = DeserializerUtility.safeGetDouble(parent, "averageRating");
        bookBriefDTO.ratingsCount = DeserializerUtility.safeGetInt(parent, "ratingsCount");

        return bookBriefDTO;

    }






}
