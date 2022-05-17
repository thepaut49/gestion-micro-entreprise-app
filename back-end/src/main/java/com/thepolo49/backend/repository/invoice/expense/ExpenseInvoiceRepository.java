package com.thepolo49.backend.repository.invoice.expense;

import com.thepolo49.backend.model.invoice.expense.ExpenseInvoice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseInvoiceRepository extends
        MongoRepository<ExpenseInvoice, ObjectId> {
    @Query("{ 'microCompany.id' : ?0 }")
    List<ExpenseInvoice> findByMicroCompanyId(ObjectId microCompanyId);
}
