package com.bookstore.bookserver.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializerUtility {

    public static String safeGetText(JsonNode node, String fieldName) {
        JsonNode childNode = node.get(fieldName);
        return childNode == null ? "" : childNode.asText();
    }

    public static int safeGetInt(JsonNode node, String fieldName) {
        JsonNode childNode = node.get(fieldName);
        return childNode == null ? 0 : childNode.asInt();
    }

    public static double safeGetDouble(JsonNode node, String fieldName) {
        JsonNode childNode = node.get(fieldName);
        return childNode == null ? 0 : childNode.asDouble();
    }

    public static String[] safeGetArray(JsonNode node, String fieldName, ObjectMapper objectMapper) {
        JsonNode childNode = node.get(fieldName);
        if (childNode == null) return null;
        try {
            return objectMapper.treeToValue(childNode, String[].class);
        } catch (Exception e) {
            return null;
        }
    }

    public static String safeGetOutOfObject(JsonNode node, String objectName, String fieldName) {
        JsonNode childNode = node.get(objectName);
        if (childNode == null) return null;
        JsonNode objectChildNode = childNode.get(fieldName);
        if (objectChildNode == null) return null;
        return objectChildNode.asText();

    }

}
