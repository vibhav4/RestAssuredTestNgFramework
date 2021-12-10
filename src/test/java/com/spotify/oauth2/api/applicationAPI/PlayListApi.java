package com.spotify.oauth2.api.applicationAPI;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.PlayList;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Routes.PLAYLISTS;
import static com.spotify.oauth2.api.Routes.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class PlayListApi {

    public static Response post(PlayList requestPlayList){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getProperty("user_id") + "/playlists" , requestPlayList);
    }

    public static Response get(String playListid){
        return RestResource.get(PLAYLISTS + playListid);
    }

    public static Response put(PlayList requestUpdatePlayList , String playListId ){
        return RestResource.put(PLAYLISTS + playListId , requestUpdatePlayList);
    }
}
