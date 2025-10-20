package com.cng.app.repository;
import com.cng.app.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}

