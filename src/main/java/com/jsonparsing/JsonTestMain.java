package com.jsonparsing;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class JsonTestMain {

    public static void main(String[] args) throws IOException {
        String jsonSource = "{\"title\":\"Coder from scrath\"}";

        JsonNode node = Json.parse(jsonSource);

        System.out.println(node.get("title").asText());
    }
}
