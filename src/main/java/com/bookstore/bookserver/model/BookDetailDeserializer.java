package com.bookstore.bookserver.model;

import com.bookstore.bookserver.util.DeserializerUtility;
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

        bookDetailDTO.id = DeserializerUtility.safeGetText(node, "id");

        JsonNode volumeInfo = node.get("volumeInfo");

        bookDetailDTO.title = DeserializerUtility.safeGetText(volumeInfo, "title");
        bookDetailDTO.publishedDate = DeserializerUtility.safeGetText(volumeInfo, "publishedDate");
        bookDetailDTO.publisher = DeserializerUtility.safeGetText(volumeInfo, "publisher");
        bookDetailDTO.description = DeserializerUtility.safeGetText(volumeInfo, "description");
        bookDetailDTO.pageCount = DeserializerUtility.safeGetInt(volumeInfo, "pageCount");
        bookDetailDTO.averageRating = DeserializerUtility.safeGetDouble(volumeInfo, "averageRating");
        bookDetailDTO.ratingsCount = DeserializerUtility.safeGetInt(volumeInfo, "ratingsCount");
        bookDetailDTO.language = DeserializerUtility.safeGetText(volumeInfo, "language");
        bookDetailDTO.previewLink = DeserializerUtility.safeGetText(volumeInfo, "previewLink");
        bookDetailDTO.authors = DeserializerUtility.safeGetArray(volumeInfo, "authors", objectMapper);
        bookDetailDTO.categories = DeserializerUtility.safeGetArray(volumeInfo, "categories", objectMapper);
        bookDetailDTO.largeThumbnail = DeserializerUtility.safeGetOutOfObject(volumeInfo, "imageLinks", "thumbnail");

        JsonNode saleInfo = node.get("saleInfo");
        bookDetailDTO.saleInfo = new BookDetailDTO.SaleInfo();
        bookDetailDTO.saleInfo.country = DeserializerUtility.safeGetText(saleInfo, "country");
        bookDetailDTO.saleInfo.saleability = saleInfo.get("saleability").asText().equals("FOR_SALE");

        if (bookDetailDTO.saleInfo.saleability) {
            String price = DeserializerUtility.safeGetOutOfObject(saleInfo, "retailPrice", "amount");
            bookDetailDTO.saleInfo.retailPrice = price == null ? null : Double.parseDouble(price);
            bookDetailDTO.saleInfo.buyLink = DeserializerUtility.safeGetText(saleInfo, "buyLink");
        }
        else {
            bookDetailDTO.saleInfo.retailPrice = null;
            bookDetailDTO.saleInfo.buyLink = null;
        }



        return bookDetailDTO;
    }

}
