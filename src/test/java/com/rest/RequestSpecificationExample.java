package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationExample {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClas(){
//         requestSpecification = given().baseUri("https://api.postman.com")
//                .header("X-Api-Key", "PMAK-619d3d74048b09003b2112cd-16e0a583b9f836894ee48ec02ab5bab2dd")
//                 .log().all();

         // Using requestSpecBuider

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("X-Api-Key", "PMAK-619d3d74048b09003b2112cd-16e0a583b9f836894ee48ec02ab5bab2dd");
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void requestSpecExample(){

        Response res = given().spec(requestSpecification).
                when()
                .get("/workspaces").
                then()
                .log().all()
                .assertThat().statusCode(200)
                .body("workspaces.name",hasItems("My Workspace" , "myworkspace_01" , "Team Workspace" , "teamworkspace"))
                .extract().response();
    }

    @Test
    public void requestSpecExample2(){

        Response res = given().spec(requestSpecification).
                when()
                .get("/workspaces").
                then()
                .log().all()
                .assertThat().statusCode(200)
                .body("workspaces.name",hasItems("My Workspace" , "myworkspace_01" , "Team Workspace" , "teamworkspace"))
                .extract().response();
    }

    @Test
    public void bddToNonBdd(){
        Response res = given().spec(requestSpecification).get("/workspaces").then().log().all().extract().response();
        assertThat(res.statusCode() , is(equalTo(200)));
    }
}
