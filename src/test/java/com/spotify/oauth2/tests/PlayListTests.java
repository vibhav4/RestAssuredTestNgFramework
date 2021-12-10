package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationAPI.PlayListApi;
import com.spotify.oauth2.pojo.PlayList;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests extends BaseTest{

    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("123")
    @Issue("321")
    @Description("create case description")
    @Test(description = "Check play list creation")
    public void createPlayListTest() {
//        PlayList requestPlayList = new PlayList().
//                setName("New Playlist 02").
//                setDescription("New playlist description two").
//                setPublic(false);

//        PlayList requestPlayList = new PlayList();
//        requestPlayList.setName("New Playlist 02");
//        requestPlayList.setDescription("New playlist description two");
//        requestPlayList.set_public(false);

        PlayList requestPlayList = PlayList.builder().name("New Playlist 02").description("New playlist description two")._public(false).build();

        Response res = PlayListApi.post(requestPlayList);
        assertThat(res.statusCode(), equalTo(201));


        PlayList responsePlayList = res.as(PlayList.class);
        assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
    }

    @Test(description = "Check play list fecthing")
    public void getPlayListTest() {

        String playlistid = "2Sd2MCQZyGQIMBnmczhdfo";

        Response res = PlayListApi.get(playlistid);
        assertThat(res.statusCode(), equalTo(200));
    }

    @Test(description = "Check play list updation")
    public void updatePlayListTest() {
//        PlayList requestUpdatePlayList = new PlayList();
//        requestUpdatePlayList.setName("Update New Playlist 02");
//        requestUpdatePlayList.setDescription("New playlist description");
//        requestUpdatePlayList.set_public(false);
//
//        String playlistid = "2Sd2MCQZyGQIMBnmczhdfo";
//        Response res = PlayListApi.put(requestUpdatePlayList, playlistid);
//        assertThat(res.statusCode(), equalTo(200));
    }
}
