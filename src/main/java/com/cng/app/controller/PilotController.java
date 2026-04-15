package com.cng.app.controller;

import com.cng.app.model.PilotRequest;
import com.cng.app.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pilot")
public class PilotController {

    @Autowired
    private PilotService pilotService;

    @PostMapping("/request")
    public PilotRequest submitPilotRequest(@RequestBody PilotRequest request) {
        return pilotService.saveRequest(request);
    }
}
