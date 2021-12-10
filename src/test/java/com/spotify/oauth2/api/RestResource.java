package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.PlayList;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Routes.API;
import static com.spotify.oauth2.api.Routes.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path , Object requestPlayList){
        return given(getRequestSpec()).
                body(requestPlayList).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response get(String path){
        return given(getRequestSpec()).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response put(String path , Object requestUpdatePlayList){
        return given(getRequestSpec()).
                body(requestUpdatePlayList).
                when().put(path).
                then().spec(getResponseSpec()).
                extract().response();
    }

    public static Response postAccount(HashMap<String , String> formParams){
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().post(API + TOKEN).
                then().spec(getResponseSpec()).
                extract().response();
    }
}
