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
                createStation("IndianOil - Manickam & Co (Iyankulam)", 13.1197, 80.1065, "Available", 8, "CNG", null, null, null),
                createStation("Torrent Gas - Anna Nagar", 13.0850, 80.2100, "Available", 5, "CNG", null, null, null),
                createStation("Torrent Gas - T.Nagar", 13.0418, 80.2341, "Available", 10, "CNG", null, null, null),
                createStation("THINK Gas - Guindy", 13.0067, 80.2206, "Available", 15, "CNG", null, null, null),
                createStation("AG&P Pratham - Velachery", 12.9791, 80.2185, "Available", 5, "CNG", null, null, null),
                createStation("Tata Power EZ - OMR Siruseri", 12.8310, 80.2210, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2 (Dual)", "Tata Power"),

                // --- PARUTHIPATTU & PONNERI AREA ---
                createStation("Torrent Gas - Paruthipattu High Rd", 13.1150, 80.0950, "Available", 5, "CNG", null, null, null),
                createStation("THINK Gas - Avadi Paruthipattu", 13.1080, 80.0880, "Available", 10, "CNG", null, null, null),
                createStation("Torrent Gas - Ponneri Town Hub", 13.3250, 80.1950, "Available", 15, "CNG", null, null, null),
                createStation("THINK Gas - Ponneri Bypass", 13.3350, 80.1850, "Available", 5, "CNG", null, null, null),

                // --- COIMBATORE HUB ---
                createStation("Torrent Gas - Saravanampatti Hub", 11.0770, 77.0000, "Long Queue", 90, "CNG", null, null, null),
                createStation("Torrent Gas - Ondipudur (Trichy Rd)", 11.0020, 77.0420, "Out of Stock", 0, "CNG", null, null, null),
                createStation("IndianOil - Pollachi Road Hub", 10.9560, 77.0050, "Available", 15, "CNG", null, null, null),
                createStation("Torrent Gas - Peelamedu", 11.0250, 77.0130, "Available", 5, "CNG", null, null, null),
                createStation("Torrent Gas - Gandhipuram", 11.0180, 76.9680, "Available", 10, "CNG", null, null, null),
                createStation("Torrent Gas - Periyanaickenpalayam", 11.1350, 76.9530, "Available", 15, "CNG", null, null, null),
                createStation("IndianOil - Singanallur", 11.0020, 77.0210, "Available", 5, "CNG", null, null, null),

                // --- MADURAI HUB ---
                createStation("Adani Total - TPK Road", 9.9080, 78.1050, "Available", 0, "CNG", null, null, null),
                createStation("IOCL - Uthangudi Melur Rd", 9.9520, 78.1710, "Queue (30m)", 30, "CNG", null, null, null),
                createStation("Adani Total - Anna Nagar (Madurai)", 9.9190, 78.1400, "Available", 5, "CNG", null, null, null),
                createStation("IOCL - Mattuthavani", 9.9320, 78.1560, "Available", 10, "CNG", null, null, null),
                createStation("IOCL - Periyar Bus Stand Area", 9.9160, 78.1130, "Available", 15, "CNG", null, null, null),
                createStation("Adani Total - Ellis Nagar", 9.9180, 78.1090, "Available", 5, "CNG", null, null, null),
                createStation("Zeon Charging - Madurai South", 9.8850, 78.1200, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Zeon Charging"),

                // --- TRICHY HUB ---
                createStation("AG&P Pratham - Thanjavur Rd", 10.8050, 78.7180, "Available", 10, "CNG", null, null, null),
                createStation("IOCL - Trichy Bypass Hub", 10.7820, 78.6750, "Available", 5, "CNG", null, null, null),
                createStation("AG&P Pratham - Srirangam", 10.8650, 78.6920, "Available", 5, "CNG", null, null, null),
                createStation("IOCL - Chatram Bus Stand Area", 10.8300, 78.6910, "Available", 10, "CNG", null, null, null),
                createStation("AG&P Pratham - Thuvakudi", 10.7550, 78.8050, "Available", 15, "CNG", null, null, null),
                createStation("IOCL - Lalgudi Road", 10.8650, 78.7500, "Available", 5, "CNG", null, null, null),
                createStation("Relux - Trichy West Hub", 10.8120, 78.6450, "Available", 0, "EV_CHARGER", "60kW DC", "CCS2", "Relux"),

                // --- SALEM HUB ---
                createStation("IOCL - Attayampatti CNG", 11.5300, 78.0470, "Queue (1HR+)", 60, "CNG", null, null, null),
                createStation("IOCL - Edanganasalai CNG", 11.5810, 78.0100, "Available", 10, "CNG", null, null, null),
                createStation("IOCL - Kondalampatti", 11.6250, 78.1300, "Available", 5, "CNG", null, null, null),
                createStation("IOCL - Ammapet", 11.6580, 78.1880, "Available", 10, "CNG", null, null, null),
                createStation("IOCL - Suramangalam", 11.6850, 78.1200, "Available", 15, "CNG", null, null, null),
                createStation("IOCL - Omalur Road", 11.7000, 78.1350, "Available", 5, "CNG", null, null, null),
                createStation("TATA Power - Salem Central", 11.6640, 78.1460, "Available", 0, "EV_CHARGER", "50kW DC", "CCS2", "Tata Power"),

                // --- VELLORE HUB ---
                createStation("THINK Gas - Officers Line", 12.9165, 79.1325, "Available", 15, "CNG", null, null, null),
                createStation("AG&P Pratham - Katpadi Hub", 12.9680, 79.1410, "Available", 5, "CNG", null, null, null),
                createStation("THINK Gas - Sathuvachari", 12.9250, 79.1550, "Available", 5, "CNG", null, null, null),
                createStation("AG&P Pratham - Gudiyatham Road", 12.9450, 79.0800, "Available", 10, "CNG", null, null, null),
                createStation("THINK Gas - Ranipet Bypass", 12.9280, 79.3300, "Available", 15, "CNG", null, null, null),
                createStation("AG&P Pratham - Arcot Road", 12.9100, 79.2000, "Available", 5, "CNG", null, null, null),
                createStation("Statiq - Vellore Highway Hub", 12.9500, 79.1800, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Statiq"),

                // --- TIRUPPUR & HOSUR HUB ---
                createStation("Adani Gas - Tiruppur Ring Rd", 11.1085, 77.3411, "Available", 20, "CNG", null, null, null),
                createStation("Adani Gas - Avinashi Road", 11.1250, 77.3000, "Available", 5, "CNG", null, null, null),
                createStation("Adani Gas - Palladam Road", 11.0800, 77.3500, "Available", 10, "CNG", null, null, null),
                createStation("Adani Gas - Dharapuram Road", 11.0500, 77.3700, "Available", 15, "CNG", null, null, null),
                createStation("Adani Gas - Kangayam Road", 11.0900, 77.3800, "Available", 5, "CNG", null, null, null),
                createStation("GAIL Gas - Hosur Bangalore Rd", 12.7236, 77.8263, "Out of Stock", 0, "CNG", null, null, null),
                createStation("GAIL Gas - SIPCOT Phase I", 12.7150, 77.8000, "Available", 5, "CNG", null, null, null),
                createStation("GAIL Gas - Attibele Border", 12.7750, 77.7700, "Available", 10, "CNG", null, null, null),
                createStation("GAIL Gas - Rayakottai Road", 12.7000, 77.8500, "Available", 15, "CNG", null, null, null),
                createStation("GAIL Gas - Bagalur Road", 12.7500, 77.8600, "Available", 5, "CNG", null, null, null),
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
