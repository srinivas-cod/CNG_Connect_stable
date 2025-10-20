package com.cng.app.repository;
import com.cng.app.model.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StationRepository extends MongoRepository<Station, String> {
    Station findByName(String name);
}
