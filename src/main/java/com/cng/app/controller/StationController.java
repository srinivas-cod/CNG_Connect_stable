package com.cng.app.controller;

import com.cng.app.model.Station;
import com.cng.app.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationRepository stationRepo;

    // GET all stations
    @GetMapping
    public List<Station> getAllStations() {
        return stationRepo.findAll();
    }

    // GET station by ID
    @GetMapping("/{id}")
    public Station getStationById(@PathVariable String id) {
        return stationRepo.findById(id).orElse(null);
    }

    // POST create new station
    @PostMapping
    public Station createStation(@RequestBody Station station) {
        return stationRepo.save(station);
    }

    // PUT update station by ID
    @PutMapping("/{id}")
    public Station updateStation(@PathVariable String id, @RequestBody Station stationDetails) {
        return stationRepo.findById(id).map(station -> {
            station.setName(stationDetails.getName());
            station.setLocation(stationDetails.getLocation());
            station.setStatus(stationDetails.getStatus());
            station.setQueueTime(stationDetails.getQueueTime());
            station.setLastUpdated(stationDetails.getLastUpdated());
            station.setAdminId(stationDetails.getAdminId());
            return stationRepo.save(station);
        }).orElse(null);
    }

    // DELETE station by ID
    @DeleteMapping("/{id}")
    public String deleteStation(@PathVariable String id) {
        stationRepo.deleteById(id);
        return "Station deleted successfully";
    }
}
