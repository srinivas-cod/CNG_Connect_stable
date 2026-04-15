package com.cng.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "pilot_requests")
@Data
public class PilotRequest {
    @Id
    private String id;
    private String companyName;
    private String workEmail;
    private String fleetSize;
    private LocalDateTime submittedAt;

    public PilotRequest() {
        this.submittedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getFleetSize() {
        return fleetSize;
    }

    public void setFleetSize(String fleetSize) {
        this.fleetSize = fleetSize;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
