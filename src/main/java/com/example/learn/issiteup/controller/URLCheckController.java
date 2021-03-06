package com.example.learn.issiteup.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLCheckController {
    private final String SITE_UP = "Site is up!";
    private final String SITE_DOWN = "site is down!";
    private final String INCORRECT_URL = "URL is Incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCodeCategory = connection.getResponseCode() / 100;
            if(responseCodeCategory == 2 || responseCodeCategory == 3){
                returnMessage = SITE_UP;
            }else{
                returnMessage = SITE_DOWN;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_DOWN;
        }
        
        
        return returnMessage;
    }
    
}
