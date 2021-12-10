package com.spotify.oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filepath){
        Properties properties = new Properties();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filepath));
            try{
                properties.load(reader);
                reader.close();
            } catch (IOException e){
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file - " + filepath);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Properties file not found -" + filepath);
        }
        return properties;
    }
}
