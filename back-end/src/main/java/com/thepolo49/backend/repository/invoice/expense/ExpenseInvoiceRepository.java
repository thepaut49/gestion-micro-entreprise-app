package com.thepolo49.backend.repository.invoice.expense;

import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseInvoiceRepository extends
        MongoRepository<ExpenseInvoice, ObjectId> {
}
