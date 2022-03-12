package com.thepolo49.backend.repository.invoice.expense;

import com.thepolo49.backend.model.invoice.expense.ExpenseInvoiceLine;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseInvoiceLineRepository extends
        MongoRepository<ExpenseInvoiceLine, ObjectId> {
}
