package com.thepolo49.backend.controller.invoice.expense;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.invoice.expense.ExpenseInvoiceDto;
import com.thepolo49.backend.service.invoice.expense.ExpenseInvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "ExpenseInvoice")
@RestController
@RequestMapping(path = "api/expense-invoices")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class ExpenseInvoiceApi {

    private final ExpenseInvoiceService expenseInvoiceService;

    @PostMapping
    public ExpenseInvoiceDto create(@RequestBody @Valid ExpenseInvoiceDto expenseInvoiceDto) {
        return expenseInvoiceService.create(expenseInvoiceDto);
    }

    @PutMapping("{id}")
    public ExpenseInvoiceDto update(@RequestBody @Valid ExpenseInvoiceDto expenseInvoiceDto) {
        return expenseInvoiceService.update(expenseInvoiceDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        expenseInvoiceService.delete(new ObjectId(id));
        return new ResponseEntity<>("Code comptable supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ExpenseInvoiceDto get(@PathVariable String id) {
        return expenseInvoiceService.getExpenseInvoice(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<ExpenseInvoiceDto> findAll() {
        return new ListResponse<>(expenseInvoiceService.findAll());
    }

}
