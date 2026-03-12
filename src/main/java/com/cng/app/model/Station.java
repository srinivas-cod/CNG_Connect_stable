package com.cng.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "stations")
public class Station {

    @Id
    private String id;
    private String name;
    private Location location;
    private String status;
    private int queueTime;
    private Date lastUpdated;
    private String adminId;
    private String fuelType; // CNG, EV_CHARGER, etc.
    
    // EV Specific Fields
    private String evPowerKW;      // e.g., "60kW DC"
    private String connectorTypes;  // e.g., "CCS2, Type 2"
    private String operator;        // e.g., "Tata Power", "Jio-bp"
    private boolean verified;       // True if updated by Admin
    private String lastUpdatedByRole; // "ADMIN" or "USER"

    public Station() {
        this.fuelType = "CNG"; 
        this.verified = false;
    }

    // Inner class for coordinates
    public static class Location {
        private double lat;
        private double lng;

        public Location() {}

        public Location(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() { return lat; }
        public void setLat(double lat) { this.lat = lat; }
        public double getLng() { return lng; }
        public void setLng(double lng) { this.lng = lng; }
    }



    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getQueueTime() { return queueTime; }
    public void setQueueTime(int queueTime) { this.queueTime = queueTime; }

    public Date getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public String getEvPowerKW() { return evPowerKW; }
    public void setEvPowerKW(String evPowerKW) { this.evPowerKW = evPowerKW; }

    public String getConnectorTypes() { return connectorTypes; }
    public void setConnectorTypes(String connectorTypes) { this.connectorTypes = connectorTypes; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public String getLastUpdatedByRole() { return lastUpdatedByRole; }
    public void setLastUpdatedByRole(String lastUpdatedByRole) { this.lastUpdatedByRole = lastUpdatedByRole; }
}
