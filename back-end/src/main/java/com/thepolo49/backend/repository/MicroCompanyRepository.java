package com.thepolo49.backend.repository;

import com.thepolo49.backend.model.MicroCompany;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroCompanyRepository extends
        MongoRepository<MicroCompany, ObjectId> {

    List<MicroCompany> findByAccessibleByAdminTrue();

    @Query("{ 'user.id' : ?0 }")
    List<MicroCompany> findByUserId(ObjectId userId);
}
