package com.thepolo49.backend.controller.invoice.expense;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceLineDto;
import com.thepolo49.backend.service.invoice.expense.ExpenseInvoiceLineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "ExpenseInvoiceLine")
@RestController
@RequestMapping(path = "api/expense-invoices-lines")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class ExpenseInvoiceLineApi {

    private final ExpenseInvoiceLineService expenseInvoiceLineService;

    @PostMapping
    public ExpenseInvoiceLineDto create(@RequestBody @Valid ExpenseInvoiceLineDto expenseInvoiceLineDto) {
        return expenseInvoiceLineService.create(expenseInvoiceLineDto);
    }

    @PutMapping("{id}")
    public ExpenseInvoiceLineDto update(@RequestBody @Valid ExpenseInvoiceLineDto expenseInvoiceLineDto) {
        return expenseInvoiceLineService.update(expenseInvoiceLineDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        expenseInvoiceLineService.delete(new ObjectId(id));
        return new ResponseEntity<>("Ligne supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ExpenseInvoiceLineDto get(@PathVariable String id) {
        return expenseInvoiceLineService.getExpenseInvoiceLine(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<ExpenseInvoiceLineDto> findAll() {
        return new ListResponse<>(expenseInvoiceLineService.findAll());
    }

}
