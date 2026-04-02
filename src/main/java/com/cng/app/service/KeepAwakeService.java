package com.cng.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableScheduling
public class KeepAwakeService {

    private final RestTemplate restTemplate;

    @Value("${RENDER_EXTERNAL_URL:}")
    private String renderExternalUrl;

    public KeepAwakeService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // Runs every 10 minutes (600,000 milliseconds) to prevent Render from sleeping
    @Scheduled(fixedRate = 600000)
    public void pingSelf() {
        if (renderExternalUrl != null && !renderExternalUrl.isEmpty()) {
            try {
                String pingUrl = renderExternalUrl + "/api/ping";
                System.out.println("Keep-Awake Ping sent to: " + pingUrl);
                restTemplate.getForObject(pingUrl, String.class);
            } catch (Exception e) {
                System.err.println("Keep-Awake Ping Failed: " + e.getMessage());
            }
        }
    }
}
