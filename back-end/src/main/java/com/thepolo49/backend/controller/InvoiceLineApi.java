package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.InvoiceDto;
import com.thepolo49.backend.dto.InvoiceLineDto;
import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.service.InvoiceLineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "InvoiceLine")
@RestController
@RequestMapping(path = "api/invoice-lines")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class InvoiceLineApi {

    private final InvoiceLineService invoiceLineService;

    @PostMapping
    public InvoiceLineDto create(@RequestBody @Valid InvoiceLineDto invoiceLineDto) {
        return invoiceLineService.create(invoiceLineDto);
    }

    @PutMapping("{id}")
    public InvoiceLineDto update(@RequestBody @Valid InvoiceLineDto invoiceLineDto) {
        return invoiceLineService.update(invoiceLineDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        invoiceLineService.delete(new ObjectId(id));
        return new ResponseEntity<>("Code comptable supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public InvoiceLineDto get(@PathVariable String id) {
        return invoiceLineService.getInvoiceLine(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<InvoiceLineDto> findAll() {
        return new ListResponse<>(invoiceLineService.findAll());
    }

}
