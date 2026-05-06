package com.resumeats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobRecommendationService {

    @Value("${adzuna.app.id}")
    private String appId;

    @Value("${adzuna.app.key}")
    private String appKey;

    public String getJobRecommendations(String skills) {
        // For testing purposes, we'll return a mock string if keys aren't set yet
        if (appId.equals("your_app_id")) {
            return "[{\"title\": \"Mock Software Engineer\", \"company\": \"Tech Corp\"}]";
        }

        String apiUrl = "https://api.adzuna.com/v1/api/jobs/in/search/1?app_id="
                + appId + "&app_key=" + appKey + "&what=" + skills;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }
}