package com.thepolo49.backend.repository;

import com.thepolo49.backend.model.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends
        MongoRepository<Person, ObjectId> {
}
