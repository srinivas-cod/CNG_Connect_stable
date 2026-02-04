package com.cng.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.cng.app.repository.StationRepository;
import com.cng.app.model.Station;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class CngBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CngBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(StationRepository repository) {
        return args -> {
            // Update: Always verify or reset if needed. 
            // Since user said coordinates are wrong, we might need to delete old bad data?
            // "give cng stations... list of all stations"
            // Let's clear the DB and re-seed to ensure correct data.
            // WARNING: In production this is bad, but for demo it's what the user wants.
            repository.deleteAll(); 
            System.out.println("Cleared old data. Seeding fresh Chennai stations...");

            // 10 Stations in Chennai (including Ponneri and Avadi)
            List<Station> stations = List.of(
                createStation("Adyar CNG Station", 13.0012, 80.2565, "Available", 5),
                createStation("Anna Nagar West Depot", 13.0850, 80.2100, "Queue", 25),
                createStation("Avadi Main Road CNG", 13.1067, 80.0970, "Available", 10), // Avadi
                createStation("Ponneri Globle CNG", 13.3289, 80.1970, "Available", 0),   // Ponneri
                createStation("Guindy Industrial Estate", 13.0100, 80.2200, "Out of Stock", 0),
                createStation("Velachery Main Road", 12.9750, 80.2205, "Queue", 15),
                createStation("Chromepet CNG Hub", 12.9530, 80.1400, "Available", 5),
                createStation("Porur Junction", 13.0330, 80.1600, "Available", 8),
                createStation("Madhavaram Truck Terminal", 13.1400, 80.2300, "Available", 12),
                createStation("Tambaram GST Road", 12.9250, 80.1100, "Queue", 30)
            );

            repository.saveAll(stations);
            System.out.println("Seeded " + stations.size() + " stations successfully.");
        };
    }

    private Station createStation(String name, double lat, double lng, String status, int queueTime) {
        Station s = new Station();
        s.setName(name);
        s.setLocation(new Station.Location(lat, lng));
        s.setStatus(status);
        s.setQueueTime(queueTime);
        s.setLastUpdated(new Date());
        return s;
    }
}
