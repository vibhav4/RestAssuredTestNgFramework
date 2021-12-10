package com.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
//@JsonIgnoreProperties(ignoreUnknown=true) - This will ignore additions fields which are not present in pojo class
public class SerielaziExamples {

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
    public void validatePostNonBDDHashMap() throws JsonProcessingException {

        HashMap<String , Object> rootObject = new HashMap<>();
        HashMap<String , String> nestedObject = new HashMap<>();

        nestedObject.put("name" , "TestWS_02");
        nestedObject.put("type" , "personal");
        nestedObject.put("description" , "Reast");

        rootObject.put("workspace" , nestedObject);

        ObjectMapper mapper = new ObjectMapper();

        String roootObjStr = mapper.writeValueAsString(rootObject);

        Response res = given().spec(requestSpecification).body(roootObjStr).post("/workspaces").then().spec(responseSpecification).extract().response();

        assertThat(res.path("workspace.name") , equalTo("TestWS_02"));

//        JsonAsser
    }
}
