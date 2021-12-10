package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RestTestExample {

    @Test
    public void cc() {
        Response res = given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-619d3d74048b09003b2112cd-16e0a583b9f836894ee48ec02ab5bab2dd").
        when()
                .get("/workspaces").
        then()
                .log().all()
                .assertThat().statusCode(200)
                .body("workspaces.name",hasItems("My Workspace" , "myworkspace_01" , "Team Workspace" , "teamworkspace"))
                .extract().response();


        JsonPath path = new JsonPath(res.asString());
        System.out.println("workspaces - " + path.getString("workspaces[0].name"));
        assertThat(path.getString("workspaces[0].name") , equalTo("My Workspacevv"));
//        System.out.println("workspaces - " + res.path("workspaces[0].name"));
//        System.out.println("****" + res.asString());
    }


    @Test
    public void validate_hamcrest_learnings() {
        Response res = given()
                .baseUri("https://api.postman.com")
                .header("X-Api-Key", "PMAK-619d3d74048b09003b2112cd-16e0a583b9f836894ee48ec02ab5bab2dd")
                        .log().all().
                when()
                .get("/workspaces").
                then()
                .log().all()
                .assertThat().statusCode(200)
                .body("workspaces.name",containsInAnyOrder("My Workspace" , "myworkspace_01" , "teamworkspace" , "Team Workspace"))
                .extract().response();

        assertThat(res.path("workspaces.name") , containsInAnyOrder("My Workspace" , "myworkspace_01" , "teamworkspace" , "Team Workspace"));
    }

}
