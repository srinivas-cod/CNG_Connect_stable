package com.cng.app.service;

import com.cng.app.model.PilotRequest;
import com.cng.app.repository.PilotRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

    @Autowired
    private PilotRequestRepository pilotRequestRepository;

    public PilotRequest saveRequest(PilotRequest request) {
        // Just save to database and return. No automated emails.
        return pilotRequestRepository.save(request);
    }
}
