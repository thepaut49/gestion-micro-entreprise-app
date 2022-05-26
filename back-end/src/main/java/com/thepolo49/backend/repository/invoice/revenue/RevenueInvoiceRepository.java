package com.thepolo49.backend.repository.invoice.revenue;

import com.thepolo49.backend.model.invoice.revenue.RevenueInvoice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueInvoiceRepository extends
        MongoRepository<RevenueInvoice, ObjectId> {
    @Query("{ 'microCompany.id' : ?0 }")
    List<RevenueInvoice> findByMicroCompanyId(ObjectId microCompanyId);
}
