package com.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.jsonparsing.pojo.AuthorPojo;
import com.jsonparsing.pojo.BookPojo;
import com.jsonparsing.pojo.DayPojo;
import com.jsonparsing.pojo.SimpleTestCaseJsonPOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String jsonSource = "{\n" +
            "  \"title\":\"Coder from scratch\",\n" +
            "  \"author\":\"ABC\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\": \"2019-12-25\",\n" +
            "  \"name\": \"Christmas Day\"\n" +
            "}";

    private String authorBookScenario = "{\n" +
            " \"authorName\" : \"Rui\",\n" +
            "  \"books\":[\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2019-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\" : \"title2\",\n" +
            "      \"inPrint\" : false,\n" +
            "      \"publishDate\" : \"2019-01-01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    @org.junit.jupiter.api.Test
    void parse() throws IOException {
        JsonNode node = Json.parse(jsonSource);
        assertEquals(node.get("title").asText(),"Coder from scratch");
    }

    @Test
    void formJson() throws IOException {
        JsonNode node = Json.parse(jsonSource);
        SimpleTestCaseJsonPOJO jsonPOJO = Json.fromJson(node, SimpleTestCaseJsonPOJO.class);
        assertEquals(jsonPOJO.getTitle(),"Coder from scratch");
       // System.out.println("POJO title"+ jsonPOJO.title);
    }

    @Test
    void toJson(){
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        assertEquals(node.get("title").asText(),"Testing 123");
    }

    @Test
    void stringyFy() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");
        JsonNode node = Json.toJson(pojo);
        System.out.println(Json.stringFy(node));
        System.out.println(Json.prettyPrint(node));
    }

    @Test
    void dayTestScenario1() throws IOException {
        JsonNode node = Json.parse(dayScenario1);
        DayPojo jsonPOJO = Json.fromJson(node, DayPojo.class);
        assertEquals(jsonPOJO.getDate().toString(),"2019-12-25");
//         System.out.println("Date: " + jsonPOJO.getDate());
    }

    @Test
    void authorBookScenario1() throws IOException {
        JsonNode node = Json.parse(authorBookScenario);
        AuthorPojo jsonPOJO = Json.fromJson(node, AuthorPojo.class);

         System.out.println("Author : " + jsonPOJO.getAuthorName());
         for(BookPojo bP:jsonPOJO.getBooks()){
             System.out.println("Book "+bP.getTitle());
             System.out.println("Date "+bP.getPublishDate());
             System.out.println("Is In print? "+bP.isInPrint());
         }
    }
}