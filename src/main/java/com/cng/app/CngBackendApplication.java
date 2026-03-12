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
            repository.deleteAll(); 
            System.out.println("Seeding State-Wide Strategic Hubs (35+ Hubs Across TN)...");

            List<Station> stations = List.of(
                // --- CHENNAI HUB (Strategic & Local) ---
                createStation("Torrent Gas - Kohinoor (Mount Road)", 13.0642, 80.2642, "Available", 5, "CNG", null, null, null),
                createStation("Torrent Gas - Adyar (LB Road)", 12.9916, 80.2541, "Available", 10, "CNG", null, null, null),
                createStation("THINK Gas - Sholinganallur (OMR)", 12.9030, 80.2270, "Queue", 25, "CNG", null, null, null),
                createStation("AG&P Pratham - Egattur (IT Park)", 12.8350, 80.2220, "Available", 5, "CNG", null, null, null),
                createStation("THINK Gas - Kelambakkam Jct", 12.7912, 80.2110, "Available", 10, "CNG", null, null, null),
                createStation("IOCL - Tambaram GST Road", 12.9242, 80.1165, "Available", 15, "CNG", null, null, null),
                createStation("Torrent Gas - Porur Jct", 13.0330, 80.1600, "Available", 5, "CNG", null, null, null),
                createStation("IndianOil - Manickam & Co (Iyankulam)", 12.8372, 79.7063, "Available", 8, "CNG", null, null, null),
                createStation("Tata Power EZ - OMR Siruseri", 12.8310, 80.2210, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2 (Dual)", "Tata Power"),

                // --- COIMBATORE HUB ---
                createStation("Torrent Gas - Saravanampatti Hub", 11.0770, 77.0000, "Long Queue", 90, "CNG", null, null, null),
                createStation("Torrent Gas - Ondipudur (Trichy Rd)", 11.0020, 77.0420, "Out of Stock", 0, "CNG", null, null, null),
                createStation("IndianOil - Pollachi Road Hub", 10.9560, 77.0050, "Available", 15, "CNG", null, null, null),

                // --- MADURAI HUB ---
                createStation("Adani Total - TPK Road", 9.9080, 78.1050, "Available", 0, "CNG", null, null, null),
                createStation("IOCL - Uthangudi Melur Rd", 9.9520, 78.1710, "Queue (30m)", 30, "CNG", null, null, null),
                createStation("Zeon Charging - Madurai South", 9.8850, 78.1200, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Zeon Charging"),

                // --- TRICHY HUB ---
                createStation("AG&P Pratham - Thanjavur Rd", 10.8050, 78.7180, "Available", 10, "CNG", null, null, null),
                createStation("IOCL - Trichy Bypass Hub", 10.7820, 78.6750, "Available", 5, "CNG", null, null, null),
                createStation("Relux - Trichy West Hub", 10.8120, 78.6450, "Available", 0, "EV_CHARGER", "60kW DC", "CCS2", "Relux"),

                // --- SALEM HUB ---
                createStation("IOCL - Attayampatti CNG", 11.5300, 78.0470, "Queue (1HR+)", 60, "CNG", null, null, null),
                createStation("IOCL - Edanganasalai CNG", 11.5810, 78.0100, "Available", 10, "CNG", null, null, null),
                createStation("TATA Power - Salem Central", 11.6640, 78.1460, "Available", 0, "EV_CHARGER", "50kW DC", "CCS2", "Tata Power"),

                // --- VELLORE HUB ---
                createStation("THINK Gas - Officers Line", 12.9165, 79.1325, "Available", 15, "CNG", null, null, null),
                createStation("AG&P Pratham - Katpadi Hub", 12.9680, 79.1410, "Available", 5, "CNG", null, null, null),
                createStation("Statiq - Vellore Highway Hub", 12.9500, 79.1800, "Available", 0, "EV_CHARGER", "60kW DC", "CCS2", "Statiq"),

                // --- TIRUPPUR & HOSUR HUB ---
                createStation("Adani Gas - Tiruppur Ring Rd", 11.1085, 77.3411, "Available", 20, "CNG", null, null, null),
                createStation("GAIL Gas - Hosur Bangalore Rd", 12.7236, 77.8263, "Out of Stock", 0, "CNG", null, null, null),
                createStation("Ather Grid - Hosur Central", 12.7350, 77.8320, "Available", 0, "EV_CHARGER", "Fast", "Ather Hub", "Ather")
            );

            repository.saveAll(stations);
            System.out.println("Data Seeding Complete. Ready for TN-wide launch.");
        };
    }

    private Station createStation(String name, double lat, double lon, String status, int wait, String fuel, String power, String connectors, String operator) {
        Station s = new Station();
        s.setName(name);
        s.setLocation(new Station.Location(lat, lon));
        s.setStatus(status);
        s.setQueueTime(wait);
        s.setFuelType(fuel);
        s.setEvPowerKW(power);
        s.setConnectorTypes(connectors);
        s.setOperator(operator);
        s.setLastUpdated(new java.util.Date());
        return s;
    }
}
