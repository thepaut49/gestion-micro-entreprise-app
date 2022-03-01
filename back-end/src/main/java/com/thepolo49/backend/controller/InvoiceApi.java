package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.InvoiceDto;
import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "Invoice")
@RestController
@RequestMapping(path = "api/invoices")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class InvoiceApi {

    private final InvoiceService invoiceService;

    @PostMapping
    public InvoiceDto create(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto);
    }

    @PutMapping("{id}")
    public InvoiceDto update(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.update(invoiceDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        invoiceService.delete(new ObjectId(id));
        return new ResponseEntity<>("Code comptable supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public InvoiceDto get(@PathVariable String id) {
        return invoiceService.getInvoice(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<InvoiceDto> findAll() {
        return new ListResponse<>(invoiceService.findAll());
    }

}
