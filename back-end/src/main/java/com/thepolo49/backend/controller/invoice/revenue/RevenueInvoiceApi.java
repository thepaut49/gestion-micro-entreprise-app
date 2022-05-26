package com.thepolo49.backend.controller.invoice.revenue;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.invoice.revenue.RevenueInvoiceDto;
import com.thepolo49.backend.service.invoice.revenue.RevenueInvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "RevenueInvoice")
@RestController
@RequestMapping(path = "api/revenue-invoices")
@RolesAllowed({"USER_ADMIN","SIMPLE_USER"})
@RequiredArgsConstructor
public class RevenueInvoiceApi {

    private final RevenueInvoiceService revenueInvoiceService;

    @PostMapping
    public RevenueInvoiceDto create(@RequestBody @Valid RevenueInvoiceDto revenueInvoiceDto) {
        return revenueInvoiceService.create(revenueInvoiceDto);
    }

    @PutMapping("{id}")
    public RevenueInvoiceDto update(@RequestBody @Valid RevenueInvoiceDto revenueInvoiceDto) {
        return revenueInvoiceService.update(revenueInvoiceDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        revenueInvoiceService.delete(new ObjectId(id));
        return new ResponseEntity<>("Ligne supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public RevenueInvoiceDto get(@PathVariable String id) {
        return revenueInvoiceService.getRevenueInvoice(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<RevenueInvoiceDto> findAll() {
        return new ListResponse<>(revenueInvoiceService.findAll());
    }

    @GetMapping("/me")
    public ListResponse<RevenueInvoiceDto> findMyRevenue() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return new ListResponse<>(revenueInvoiceService.findMyRevenue(userDetails.getUsername()));
    }

}
