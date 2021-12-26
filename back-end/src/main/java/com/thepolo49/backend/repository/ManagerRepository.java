package com.thepolo49.backend.repository;

import com.thepolo49.backend.model.ContactInfo;
import com.thepolo49.backend.model.Manager;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends
        MongoRepository<Manager, ObjectId> {
}
