package com.cng.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.cng.app.repository.StationRepository;
import com.cng.app.model.Station;
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
            System.out.println("Seeding Strategic Hubs (60+ Hubs Across TN, AP, KA, KL, TS)...");

            List<Station> stations = List.of(
                // --- CHENNAI HUB (Strategic & Local) ---
                createStation("Torrent Gas - Kohinoor (Mount Road)", 13.0642, 80.2642, "Available", 5, "CNG", null, null, null, true, "ADMIN"),
                createStation("Torrent Gas - Adyar (LB Road)", 12.9916, 80.2541, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Sholinganallur (OMR)", 12.9030, 80.2270, "Queue", 25, "CNG", null, null, null, true, "ADMIN"),
                createStation("AG&P Pratham - Egattur (IT Park)", 12.8350, 80.2220, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Kelambakkam Jct", 12.7912, 80.2110, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Tambaram GST Road", 12.9242, 80.1165, "Available", 15, "CNG", null, null, null, true, "ADMIN"),
                createStation("Torrent Gas - Porur Jct", 13.0330, 80.1600, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("IndianOil - Manickam & Co (Iyankulam)", 13.1197, 80.1065, "Available", 8, "CNG", null, null, null, false, "USER"),
                createStation("Torrent Gas - Anna Nagar", 13.0850, 80.2100, "Available", 5, "CNG", null, null, null, true, "ADMIN"),
                createStation("Torrent Gas - T.Nagar", 13.0418, 80.2341, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Guindy", 13.0067, 80.2206, "Available", 15, "CNG", null, null, null, true, "ADMIN"),
                createStation("AG&P Pratham - Velachery", 12.9791, 80.2185, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Tata Power EZ - OMR Siruseri", 12.8310, 80.2210, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2 (Dual)", "Tata Power", true, "ADMIN"),

                // --- PARUTHIPATTU & PONNERI AREA ---
                createStation("Torrent Gas - Paruthipattu High Rd", 13.1150, 80.0950, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Avadi Paruthipattu", 13.1080, 80.0880, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("Torrent Gas - Ponneri Town Hub", 13.3250, 80.1950, "Available", 15, "CNG", null, null, null, true, "ADMIN"),
                createStation("THINK Gas - Ponneri Bypass", 13.3350, 80.1850, "Available", 5, "CNG", null, null, null, false, "USER"),

                // --- COIMBATORE HUB ---
                createStation("Torrent Gas - Saravanampatti Hub", 11.0770, 77.0000, "Long Queue", 90, "CNG", null, null, null, true, "ADMIN"),
                createStation("Torrent Gas - Ondipudur (Trichy Rd)", 11.0020, 77.0420, "Out of Stock", 0, "CNG", null, null, null, false, "USER"),
                createStation("IndianOil - Pollachi Road Hub", 10.9560, 77.0050, "Available", 15, "CNG", null, null, null, true, "ADMIN"),
                createStation("Torrent Gas - Peelamedu", 11.0250, 77.0130, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Torrent Gas - Gandhipuram", 11.0180, 76.9680, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("Torrent Gas - Periyanaickenpalayam", 11.1350, 76.9530, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("IndianOil - Singanallur", 11.0020, 77.0210, "Available", 5, "CNG", null, null, null, false, "USER"),

                // --- MADURAI HUB ---
                createStation("Adani Total - TPK Road", 9.9080, 78.1050, "Available", 0, "CNG", null, null, null, true, "ADMIN"),
                createStation("IOCL - Uthangudi Melur Rd", 9.9520, 78.1710, "Queue (30m)", 30, "CNG", null, null, null, false, "USER"),
                createStation("Adani Total - Anna Nagar (Madurai)", 9.9190, 78.1400, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Mattuthavani", 9.9320, 78.1560, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Periyar Bus Stand Area", 9.9160, 78.1130, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("Adani Total - Ellis Nagar", 9.9180, 78.1090, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Zeon Charging - Madurai South", 9.8850, 78.1200, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Zeon Charging", true, "ADMIN"),

                // --- TRICHY HUB ---
                createStation("AG&P Pratham - Thanjavur Rd", 10.8050, 78.7180, "Available", 10, "CNG", null, null, null, true, "ADMIN"),
                createStation("IOCL - Trichy Bypass Hub", 10.7820, 78.6750, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("AG&P Pratham - Srirangam", 10.8650, 78.6920, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Chatram Bus Stand Area", 10.8300, 78.6910, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("AG&P Pratham - Thuvakudi", 10.7550, 78.8050, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Lalgudi Road", 10.8650, 78.7500, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Relux - Trichy West Hub", 10.8120, 78.6450, "Available", 0, "EV_CHARGER", "60kW DC", "CCS2", "Relux", true, "ADMIN"),

                // --- SALEM HUB ---
                createStation("IOCL - Attayampatti CNG", 11.5300, 78.0470, "Queue (1HR+)", 60, "CNG", null, null, null, true, "ADMIN"),
                createStation("IOCL - Edanganasalai CNG", 11.5810, 78.0100, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Kondalampatti", 11.6250, 78.1300, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Ammapet", 11.6580, 78.1880, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Suramangalam", 11.6850, 78.1200, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Omalur Road", 11.7000, 78.1350, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("TATA Power - Salem Central", 11.6640, 78.1460, "Available", 0, "EV_CHARGER", "50kW DC", "CCS2", "Tata Power", true, "ADMIN"),

                // --- VELLORE HUB ---
                createStation("THINK Gas - Officers Line", 12.9165, 79.1325, "Available", 15, "CNG", null, null, null, true, "ADMIN"),
                createStation("AG&P Pratham - Katpadi Hub", 12.9680, 79.1410, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Sathuvachari", 12.9250, 79.1550, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("AG&P Pratham - Gudiyatham Road", 12.9450, 79.0800, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Ranipet Bypass", 12.9280, 79.3300, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("AG&P Pratham - Arcot Road", 12.9100, 79.2000, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Statiq - Vellore Highway Hub", 12.9500, 79.1800, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Statiq", true, "ADMIN"),

                // --- TIRUPPUR & HOSUR HUB ---
                createStation("Adani Gas - Tiruppur Ring Rd", 11.1085, 77.3411, "Available", 20, "CNG", null, null, null, false, "USER"),
                createStation("Adani Gas - Avinashi Road", 11.1250, 77.3000, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Adani Gas - Palladam Road", 11.0800, 77.3500, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("Adani Gas - Dharapuram Road", 11.0500, 77.3700, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("Adani Gas - Kangayam Road", 11.0900, 77.3800, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("GAIL Gas - Hosur Bangalore Rd", 12.7236, 77.8263, "Out of Stock", 0, "CNG", null, null, null, true, "ADMIN"),
                createStation("GAIL Gas - SIPCOT Phase I", 12.7150, 77.8000, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("GAIL Gas - Attibele Border", 12.7750, 77.7700, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("GAIL Gas - Bagalur Road", 12.7500, 77.8600, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Ather Grid - Hosur Central", 12.7350, 77.8320, "Available", 0, "EV_CHARGER", "Fast", "Ather Hub", "Ather", true, "ADMIN"),

                // --- ANDHRA PRADESH CORRIDOR ---
                createStation("AG&P Pratham - Sri City (Tada)", 13.5500, 80.0500, "Available", 5, "CNG", null, null, null, true, "ADMIN"),
                createStation("THINK Gas - Nellore Bypass", 14.4426, 79.9865, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("AG&P Pratham - Tirupati NH71", 13.6288, 79.4192, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Vijayawada Highway", 16.5062, 80.6480, "Available", 20, "CNG", null, null, null, false, "USER"),
                createStation("THINK Gas - Chittoor Town", 13.2172, 79.1003, "Available", 5, "CNG", null, null, null, false, "USER"),

                // --- KARNATAKA CORRIDOR ---
                createStation("GAIL Gas - Bangalore Central", 12.9716, 77.5946, "Queue", 40, "CNG", null, null, null, true, "ADMIN"),
                createStation("GAIL Gas - Electronic City Hub", 12.8399, 77.6770, "Available", 10, "CNG", null, null, null, false, "USER"),
                createStation("IndianOil - Kolar Highway", 13.1363, 78.1291, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("AGP - Mysore Road CNG", 12.2958, 76.6394, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("GAIL Gas - Tumkur Junction", 13.3392, 77.1140, "Available", 5, "CNG", null, null, null, false, "USER"),

                // --- KERALA CORRIDOR ---
                createStation("IOCL - Palakkad ByePass", 10.7867, 76.6547, "Available", 10, "CNG", null, null, null, true, "ADMIN"),
                createStation("IOCL - Kochi Pullepady", 9.9312, 76.2673, "Long Queue", 50, "CNG", null, null, null, false, "USER"),
                createStation("AG&P - Thrissur City Hub", 10.5276, 76.2144, "Available", 15, "CNG", null, null, null, false, "USER"),
                createStation("Adani Total - Trivandrum Central", 8.5241, 76.9366, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Kozhikode NH", 11.2588, 75.7804, "Available", 10, "CNG", null, null, null, false, "USER"),

                // --- TELANGANA CORRIDOR ---
                // --- TELANGANA HUB (Expanded) ---
                createStation("Bhagyanagar Gas - Hyderabad Gachibowli", 17.4400, 78.3489, "Queue", 30, "CNG", null, null, null, true, "ADMIN"),
                createStation("Adani Total - Jubilee Hills Rd No 36", 17.4325, 78.4071, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("IOCL - Secunderabad Club Area", 17.4447, 78.4983, "Available", 10, "CNG", null, null, null, true, "ADMIN"),
                createStation("Megha City Gas - Warangal NH", 17.9689, 79.5941, "Available", 5, "CNG", null, null, null, false, "USER"),
                createStation("Bhagyanagar Gas - Hitech City", 17.4483, 78.3915, "Available", 5, "CNG", null, null, null, true, "ADMIN"),
                createStation("Statiq - Hyderabad Banjara Hills", 17.4156, 78.4522, "Available", 0, "EV_CHARGER", "100kW DC", "CCS2", "Statiq", true, "ADMIN"),

                // --- MAHARASHTRA HUB (New) ---
                createStation("MGL - BKC South Mumbai", 19.0607, 72.8633, "Available", 15, "CNG", null, null, "Mahanagar Gas", true, "ADMIN"),
                createStation("MGL - Goregaon West", 19.1634, 72.8412, "Queue (20m)", 20, "CNG", null, null, "Mahanagar Gas", false, "USER"),
                createStation("MGL - Borivali East", 19.2290, 72.8574, "Available", 5, "CNG", null, null, "Mahanagar Gas", true, "ADMIN"),
                createStation("MGL - Pune Hinjewadi IT Park", 18.5913, 73.7389, "Available", 10, "CNG", null, null, "Mahanagar Gas", true, "ADMIN"),
                createStation("Adani Total - Pune Kothrud", 18.5074, 73.8077, "Available", 5, "CNG", null, null, "Adani Gas", false, "USER"),
                createStation("MNGL - Pune Wakad Hub", 18.5987, 73.7707, "Available", 15, "CNG", null, null, "MNGL", true, "ADMIN"),
                createStation("Adani Total - Nagpur Shankar Nagar", 21.1388, 79.0667, "Available", 5, "CNG", null, null, "Adani Gas", false, "USER"),
                createStation("IOCL - Nagpur Wardha Road", 21.1000, 79.0800, "Available", 10, "CNG", null, null, "IOCL", true, "ADMIN"),
                createStation("Tata Power - Mumbai Worli Sea Face", 19.0118, 72.8147, "Available", 0, "EV_CHARGER", "60kW DC", "CCS2", "Tata Power", true, "ADMIN"),
                createStation("Jio-bp pulse - Pune Mall Rd", 18.5204, 73.8567, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Jio-bp", true, "ADMIN"),

                // --- ODISHA HUB (New) ---
                createStation("GAIL Gas - Bhubaneswar Patia", 20.3533, 85.8366, "Available", 5, "CNG", null, null, "GAIL Gas", true, "ADMIN"),
                createStation("GAIL Gas - Bhubaneswar Jayadev Vihar", 20.3010, 85.8310, "Available", 10, "CNG", null, null, "GAIL Gas", false, "USER"),
                createStation("BPCL - Bhubaneswar Master Canteen", 20.2721, 85.8400, "Available", 5, "CNG", null, null, "BPCL", true, "ADMIN"),
                createStation("GAIL Gas - Cuttack Link Road", 20.4500, 85.8700, "Available", 15, "CNG", null, null, "GAIL Gas", true, "ADMIN"),
                createStation("IOCL - Cuttack Badambadi", 20.4630, 85.8820, "Available", 5, "CNG", null, null, "IOCL", false, "USER"),
                createStation("Statiq - Bhubaneswar Highway Hub", 20.2961, 85.8245, "Available", 0, "EV_CHARGER", "30kW DC", "CCS2", "Statiq", true, "ADMIN"),

                // --- ANDHRA PRADESH HUB (Expanded) ---
                createStation("THINK Gas - Vijayawada Benz Circle", 16.5000, 80.6400, "Available", 10, "CNG", null, null, "THINK Gas", true, "ADMIN"),
                createStation("AG&P Pratham - Vizag Rk Beach Area", 17.7140, 83.3235, "Available", 5, "CNG", null, null, "AG&P Pratham", false, "USER"),
                createStation("IOCL - Guntur Etukuru Rd", 16.3067, 80.4365, "Available", 15, "CNG", null, null, "IOCL", true, "ADMIN"),
                createStation("Adani Total - Nellore Bypass", 14.4426, 79.9865, "Available", 5, "CNG", null, null, "Adani Gas", false, "USER"),
                createStation("Tata Power - Vizag Port Area", 17.6868, 83.2185, "Available", 0, "EV_CHARGER", "50kW DC", "CCS2", "Tata Power", true, "ADMIN"),

                // --- KARNATAKA HUB (Expanded) ---
                createStation("GAIL Gas - Bangalore Whitefield", 12.9698, 77.7500, "Queue", 25, "CNG", null, null, "GAIL Gas", true, "ADMIN"),
                createStation("GAIL Gas - Bangalore Koramangala", 12.9352, 77.6245, "Available", 5, "CNG", null, null, "GAIL Gas", false, "USER"),
                createStation("AG&P Pratham - Mysore Hinkal", 12.3210, 76.6110, "Available", 10, "CNG", null, null, "AG&P Pratham", true, "ADMIN"),
                createStation("GAIL Gas - Mangalore Bejai", 12.8942, 74.8450, "Available", 15, "CNG", null, null, "GAIL Gas", false, "USER"),
                createStation("Zeon Charging - Bangalore Hebbal", 13.0350, 77.5970, "Available", 0, "EV_CHARGER", "120kW DC", "CCS2", "Zeon", true, "ADMIN"),

                // --- KERALA HUB (Expanded) ---
                createStation("AG&P Pratham - Kochi Vytilla", 9.9670, 76.3150, "Available", 10, "CNG", null, null, "AG&P Pratham", true, "ADMIN"),
                createStation("IOCL - Kochi Edappally", 10.0260, 76.3080, "Available", 5, "CNG", null, null, "IOCL", false, "USER"),
                createStation("Adani Total - TVM Kazhakkoottam", 8.5680, 76.8730, "Available", 15, "CNG", null, null, "Adani Gas", true, "ADMIN"),
                createStation("Relux - Kochi Highway Hub", 9.9312, 76.2673, "Available", 0, "EV_CHARGER", "60kW DC", "CCS2", "Relux", true, "ADMIN"),

                // --- DELHI HUB (New) ---
                createStation("IGL - Near AIIMS Delhi", 28.5672, 77.2100, "Available", 5, "CNG", null, null, "IGL", true, "ADMIN"),
                createStation("IGL - Dwarka Sector 10", 28.5833, 77.0667, "Queue", 20, "CNG", null, null, "IGL", true, "ADMIN"),

                // --- UTTAR PRADESH HUB (New) ---
                createStation("Green Gas - Lucknow Gomti Nagar", 26.8467, 80.9461, "Available", 10, "CNG", null, null, "Green Gas", true, "ADMIN"),
                createStation("Adani Total - Noida Sector 62", 28.6253, 77.3685, "Available", 5, "CNG", null, null, "Adani Total", true, "ADMIN")
            );

            repository.saveAll(stations);
            System.out.println("Data Seeding Complete. Ready for Nationwide launch (9 Regions).");
        };
    }

    private Station createStation(String name, double lat, double lon, String status, int wait, String fuel, String power, String connectors, String operator, boolean verified, String role) {
        Station s = new Station();
        s.setName(name);
        s.setLocation(new Station.Location(lat, lon));
        s.setStatus(status);
        s.setQueueTime(wait);
        s.setFuelType(fuel);
        s.setEvPowerKW(power);
        s.setConnectorTypes(connectors);
        s.setOperator(operator);
        s.setVerified(verified);
        s.setLastUpdatedByRole(role);
        s.setLastUpdated(new java.util.Date());
        return s;
    }
}
