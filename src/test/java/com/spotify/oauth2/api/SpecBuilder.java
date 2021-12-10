package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Routes.BASE_PATH;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class SpecBuilder {

//    static String access_token = "BQCvBMG5rT-5ghRJAA8QOvGafC1ptbtbyHZDdvBy_e8eHNUkMHmFeQ41NLMJd19dazxk_sTlt9r9s8AV-iRcHMp_5sw137J69u6NJIrSHNi3hMmeYRjKbGf8xSN1vCQuwsaMumOlzDrwuDtDmOOLrwmEeqK3atXxpB4Raj6OXWISN7PUV0b2aIrU9EgrVGQ9JaTarftaCTBFumWJ7mKczvhvqzjcAoNrGM9-p_OLK5Wy40br";

    public static RequestSpecification getRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.
                setBaseUri(System.getProperty("BASE_URI")).
//                setBaseUri("https://api.spotify.com").
                setBasePath(BASE_PATH).
                addHeader("Authorization", "Bearer " + getToken()).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }

    public static RequestSpecification getAccountRequestSpec(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
//                setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }

    public static ResponseSpecification getResponseSpec(){
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.
        log(LogDetail.ALL);
        return responseSpecBuilder.build();
    }

}
