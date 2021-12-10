package com.rest;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LanguageVsCodes {
    public static void main(String[] args) {
        SupportedLanguage[] supportedLanguage = SupportedLanguage.values();
//        HashMap<String , List<HashMap<String,String>>> lanvscode = new HashMap<>();
        List<HashMap<String,String>> finalList = new ArrayList<>();

        for(SupportedLanguage ss : supportedLanguage){
            HashMap<String,String> newmap = new HashMap<>();
            newmap.put("name" , ss.getLabel());
            newmap.put("value" , ss.getLangCode());
            finalList.add(newmap);
        }

        Gson gson = new Gson();
        String json = gson.toJson(finalList);
        System.out.println(json);
    }
}
