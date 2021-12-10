package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AutomatePost {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClas(){
        // Using requestSpecBuider

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key", "PMAK-619d3d74048b09003b2112cd-16e0a583b9f836894ee48ec02ab5bab2dd");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();


        // Using responsespecBuilder

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePostBDD(){

        String payload = "{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"TestWS_01\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Reast assured test workspace\"\n" +
                "    }\n" +
                "}";

        given().
                body(payload).
                when().
                post("/workspaces").
                then().
        assertThat().
        body("workspace.name" , equalTo("TestWS_01"));
    }


    @Test
    public void validatePostNonBDD(){

        String payload = "{\n" +
                "    \"workspace\":{\n" +
                "        \"name\":\"TestWS_01\",\n" +
                "        \"type\":\"personal\",\n" +
                "        \"description\":\"Reast assured test workspace\"\n" +
                "    }\n" +
                "}";

        Response res = given().spec(requestSpecification).body(payload).post("/workspaces");

        assertThat(res.path("workspace.name") , equalTo("TestWS_01"));
    }

    @Test
    public void validatePostNonBDDFile(){

        File file = new File("src/main/resources/payload.json");

        Response res = given().spec(requestSpecification).body(file).post("/workspaces");

        assertThat(res.path("workspace.name") , equalTo("TestWS_01"));
    }


    @Test
    public void validatePostNonBDDHashMap(){

        HashMap<String , Object> rootObject = new HashMap<>();
        HashMap<String , String> nestedObject = new HashMap<>();

        nestedObject.put("name" , "TestWS_01");
        nestedObject.put("type" , "personal");
        nestedObject.put("description" , "Reast");

        rootObject.put("workspace" , nestedObject);

        Response res = given().spec(requestSpecification).body(rootObject).post("/workspaces").then().spec(responseSpecification).extract().response();

        assertThat(res.path("workspace.name") , equalTo("TestWS_01"));
    }

}
