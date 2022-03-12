package com.thepolo49.backend.controller.invoice.revenue;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceLineDto;
import com.thepolo49.backend.service.invoice.revenue.RevenueInvoiceLineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "RevenueInvoiceLine")
@RestController
@RequestMapping(path = "api/revenue-invoices-lines")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class RevenueInvoiceLineApi {

    private final RevenueInvoiceLineService revenueInvoiceLineService;

    @PostMapping
    public RevenueInvoiceLineDto create(@RequestBody @Valid RevenueInvoiceLineDto revenueInvoiceLineDto) {
        return revenueInvoiceLineService.create(revenueInvoiceLineDto);
    }

    @PutMapping("{id}")
    public RevenueInvoiceLineDto update(@RequestBody @Valid RevenueInvoiceLineDto revenueInvoiceLineDto) {
        return revenueInvoiceLineService.update(revenueInvoiceLineDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        revenueInvoiceLineService.delete(new ObjectId(id));
        return new ResponseEntity<>("Ligne supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public RevenueInvoiceLineDto get(@PathVariable String id) {
        return revenueInvoiceLineService.getRevenueInvoiceLine(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<RevenueInvoiceLineDto> findAll() {
        return new ListResponse<>(revenueInvoiceLineService.findAll());
    }

}
