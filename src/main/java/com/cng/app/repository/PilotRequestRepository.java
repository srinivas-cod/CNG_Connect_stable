package com.cng.app.repository;
import com.cng.app.model.PilotRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface PilotRequestRepository extends MongoRepository<PilotRequest, String> {
}
