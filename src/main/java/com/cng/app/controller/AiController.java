package com.cng.app.controller;

import com.cng.app.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/recommend")
    public ResponseEntity<Map<String, Object>> getRecommendation(@RequestBody Map<String, Object> payload) {
        String prompt = (String) payload.getOrDefault("prompt", "Recommend best CNG station");
        Double lat = payload.containsKey("lat") ? Double.valueOf(payload.get("lat").toString()) : null;
        Double lng = payload.containsKey("lng") ? Double.valueOf(payload.get("lng").toString()) : null;

        Map<String, Object> response = aiService.getAiRecommendation(prompt, lat, lng);
        return ResponseEntity.ok(response);
    }
}
