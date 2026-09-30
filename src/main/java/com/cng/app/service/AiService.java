package com.cng.app.service;

import com.cng.app.model.Station;
import com.cng.app.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AiService {

    @Autowired
    private StationRepository stationRepository;

    private final RestTemplate restTemplate;

    @Value("${GEMINI_API_KEY:}")
    private String geminiApiKey;

    public AiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Map<String, Object> getAiRecommendation(String userPrompt, Double userLat, Double userLng) {
        List<Station> allStations = stationRepository.findAll();
        
        // Calculate distances and sort nearest stations
        List<Map<String, Object>> nearbyStations = getNearestStations(allStations, userLat, userLng, 5);

        String aiResponseText;

        // If a free Gemini API key is provided, use Gemini API. Otherwise, use our Smart Rule-Based Engine.
        if (geminiApiKey != null && !geminiApiKey.trim().isEmpty()) {
            aiResponseText = callGeminiApi(userPrompt, nearbyStations);
        } else {
            aiResponseText = generateSmartLocalRecommendation(userPrompt, nearbyStations);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("recommendation", aiResponseText);
        result.put("nearbyStations", nearbyStations);
        return result;
    }

    private List<Map<String, Object>> getNearestStations(List<Station> stations, Double userLat, Double userLng, int limit) {
        List<Map<String, Object>> list = new ArrayList<>();
        double lat1 = (userLat != null) ? userLat : 13.0827; // Default to Chennai center if location not shared
        double lng1 = (userLng != null) ? userLng : 80.2707;

        for (Station s : stations) {
            double distKm = calculateDistanceKm(lat1, lng1, s.getLocation().getLat(), s.getLocation().getLng());
            Map<String, Object> map = new HashMap<>();
            map.put("id", s.getId());
            map.put("name", s.getName());
            map.put("status", s.getStatus());
            map.put("queueTime", s.getQueueTime());
            map.put("fuelType", s.getFuelType());
            map.put("distanceKm", Math.round(distKm * 10.0) / 10.0);
            map.put("operator", s.getOperator());
            map.put("lat", s.getLocation().getLat());
            map.put("lng", s.getLocation().getLng());
            list.add(map);
        }

        list.sort((a, b) -> Double.compare((Double) a.get("distanceKm"), (Double) b.get("distanceKm")));
        return list.subList(0, Math.min(limit, list.size()));
    }

    private double calculateDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of Earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private String callGeminiApi(String userPrompt, List<Map<String, Object>> stations) {
        try {
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + geminiApiKey;

            StringBuilder context = new StringBuilder("You are CNG Connect AI Assistant, an expert fuel routing co-pilot in India. ");
            context.append("Here is the current live station data near the user:\n");
            for (Map<String, Object> s : stations) {
                context.append(String.format("- %s: %s km away, Status: %s, Queue Wait: %d mins, Fuel: %s\n",
                        s.get("name"), s.get("distanceKm"), s.get("status"), s.get("queueTime"), s.get("fuelType")));
            }
            context.append("\nUser Request: \"").append(userPrompt).append("\"\n");
            context.append("Provide a short, direct, friendly recommendation (2-3 sentences max) highlighting the best station, estimated total time, and why.");

            Map<String, Object> textPart = Map.of("text", context.toString());
            Map<String, Object> contentsPart = Map.of("parts", List.of(textPart));
            Map<String, Object> requestBody = Map.of("contents", List.of(contentsPart));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List candidates = (List) response.getBody().get("candidates");
                if (candidates != null && !candidates.isEmpty()) {
                    Map firstCandidate = (Map) candidates.get(0);
                    Map content = (Map) firstCandidate.get("content");
                    List parts = (List) content.get("parts");
                    Map firstPart = (Map) parts.get(0);
                    return (String) firstPart.get("text");
                }
            }
        } catch (Exception e) {
            System.err.println("Gemini API error fallback: " + e.getMessage());
        }
        return generateSmartLocalRecommendation(userPrompt, stations);
    }

    private String generateSmartLocalRecommendation(String userPrompt, List<Map<String, Object>> stations) {
        if (stations.isEmpty()) {
            return "No CNG stations found in your immediate radius. Try zooming out on the map.";
        }

        // Find available station with minimum (Distance + Queue Time factor)
        Map<String, Object> bestStation = null;
        double bestScore = Double.MAX_VALUE;

        for (Map<String, Object> s : stations) {
            String status = (String) s.get("status");
            if (!"Out of Stock".equalsIgnoreCase(status)) {
                double dist = (Double) s.get("distanceKm");
                int queue = (Integer) s.get("queueTime");
                // Score = Travel time (~2 min per km) + Queue wait time
                double score = (dist * 2.0) + queue;
                if (score < bestScore) {
                    bestScore = score;
                    bestStation = s;
                }
            }
        }

        if (bestStation != null) {
            String name = (String) bestStation.get("name");
            double dist = (Double) bestStation.get("distanceKm");
            int queue = (Integer) bestStation.get("queueTime");
            String status = (String) bestStation.get("status");

            if (userPrompt.toLowerCase().contains("20%") || userPrompt.toLowerCase().contains("low") || userPrompt.toLowerCase().contains("fuel")) {
                return String.format("⚡ **Recommended:** %s is just %.1f km away with a %d-min wait status (%s). Proceed here immediately to avoid running out of fuel!",
                        name, dist, queue, status);
            } else if (userPrompt.toLowerCase().contains("queue") || userPrompt.toLowerCase().contains("crowd")) {
                return String.format("🚥 **Best Choice for Speed:** %s (%.1f km away) currently has the lowest estimated total wait time (~%d mins).",
                        name, dist, queue);
            } else {
                return String.format("💡 **Optimal Refill Stop:** %s (%.1f km away, %d min queue, Status: %s) will get you back on the road fastest.",
                        name, dist, queue, status);
            }
        }

        Map<String, Object> nearest = stations.get(0);
        return String.format("⚠️ Note: Nearest station %s (%.1f km) is currently reported Out of Stock. Please check secondary options on the map.",
                nearest.get("name"), nearest.get("distanceKm"));
    }
}
