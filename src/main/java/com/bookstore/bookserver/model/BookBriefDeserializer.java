package com.bookstore.bookserver.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookBriefDeserializer extends JsonDeserializer<BookBriefDTO[]> {

    @Override
    public BookBriefDTO[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = objectMapper.readTree(jsonParser);
        JsonNode itemsNode = rootNode.get("items");

        List<BookBriefDTO> bookBriefDTOList = new ArrayList<>();

        if (itemsNode == null || !itemsNode.isArray()) throw new IOException("Invalid JSON response");

        for (JsonNode itemNode : itemsNode) {
            JsonNode idNode = itemNode.get("id");
            JsonNode volumeInfoNode = itemNode.get("volumeInfo");

            if (idNode == null || volumeInfoNode == null) throw new IOException("Invalid JSON response");

            BookBriefDTO bookBriefDTO = new BookBriefDTO();

            bookBriefDTO._id = idNode.asText();
            bookBriefDTO.title = volumeInfoNode.get("title").asText();
            bookBriefDTO.publishedDate = volumeInfoNode.get("publishedDate").asText();

            JsonNode authorsNode = volumeInfoNode.get("authors");

            if (authorsNode == null) bookBriefDTO.authors = null;

            bookBriefDTO.authors = objectMapper.treeToValue(authorsNode, String[].class);
            JsonNode imageLinksNode = volumeInfoNode.get("imageLinks");

            if (imageLinksNode == null) {
                bookBriefDTO.thumbnail = null;

            } else {
                JsonNode smallThumbnailNode = imageLinksNode.get("smallThumbnail");
                if (smallThumbnailNode != null) bookBriefDTO.thumbnail = smallThumbnailNode.asText();
                else bookBriefDTO.thumbnail = null;

            }


            bookBriefDTOList.add(bookBriefDTO);
        }

        return bookBriefDTOList.toArray(new BookBriefDTO[0]);
    }
}
