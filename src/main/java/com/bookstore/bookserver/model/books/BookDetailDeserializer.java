package com.bookstore.bookserver.model.books;

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

        bookDetailDTO.setId(DeserializerUtility.safeGetText(node, "id"));

        JsonNode volumeInfo = node.get("volumeInfo");

        bookDetailDTO.setTitle(DeserializerUtility.safeGetText(volumeInfo, "title"));
        bookDetailDTO.setDate(DeserializerUtility.safeGetText(volumeInfo, "publishedDate"));
        bookDetailDTO.setPublisher(DeserializerUtility.safeGetText(volumeInfo, "publisher"));
        bookDetailDTO.setDescription(DeserializerUtility.safeGetText(volumeInfo, "description"));
        bookDetailDTO.setPageCount(DeserializerUtility.safeGetInt(volumeInfo, "pageCount"));
        bookDetailDTO.setAverageRating(DeserializerUtility.safeGetDouble(volumeInfo, "averageRating"));
        bookDetailDTO.setRatingsCount(DeserializerUtility.safeGetInt(volumeInfo, "ratingsCount"));
        bookDetailDTO.setLanguage(DeserializerUtility.safeGetText(volumeInfo, "language"));
        bookDetailDTO.setPreviewLink(DeserializerUtility.safeGetText(volumeInfo, "previewLink"));
        bookDetailDTO.setCreators(DeserializerUtility.safeGetArray(volumeInfo, "authors", objectMapper));
        bookDetailDTO.setCategories(DeserializerUtility.safeGetArray(volumeInfo, "categories", objectMapper));
        bookDetailDTO.setThumbnail(DeserializerUtility.safeGetOutOfObject(volumeInfo, "imageLinks", "thumbnail"));

        JsonNode saleInfo = node.get("saleInfo");
        bookDetailDTO.setSaleInfo(new BookDetailDTO.SaleInfo());
        bookDetailDTO.getSaleInfo().country = DeserializerUtility.safeGetText(saleInfo, "country");
        bookDetailDTO.getSaleInfo().saleability = saleInfo.get("saleability").asText().equals("FOR_SALE");

        if (bookDetailDTO.getSaleInfo().saleability) {
            String price = DeserializerUtility.safeGetOutOfObject(saleInfo, "retailPrice", "amount");
            bookDetailDTO.getSaleInfo().retailPrice = price == null ? null : Double.parseDouble(price);
            bookDetailDTO.getSaleInfo().buyLink = DeserializerUtility.safeGetText(saleInfo, "buyLink");
        }
        else {
            bookDetailDTO.getSaleInfo().retailPrice = null;
            bookDetailDTO.getSaleInfo().buyLink = null;
        }

        return bookDetailDTO;
    }



}
