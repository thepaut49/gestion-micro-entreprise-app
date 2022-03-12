package com.thepolo49.backend.repository.invoice.revenue;

import com.thepolo49.backend.model.invoice.revenue.RevenueInvoiceLine;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueInvoiceLineRepository extends
        MongoRepository<RevenueInvoiceLine, ObjectId> {
}
