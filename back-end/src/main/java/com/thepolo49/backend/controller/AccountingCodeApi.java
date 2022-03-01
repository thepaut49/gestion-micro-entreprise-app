package com.thepolo49.backend.controller;

import com.thepolo49.backend.dto.ListResponse;
import com.thepolo49.backend.dto.AccountingCodeDto;
import com.thepolo49.backend.service.AccountingCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "AccountingCode")
@RestController
@RequestMapping(path = "api/accounting-codes")
@RolesAllowed({"USER_ADMIN"})
@RequiredArgsConstructor
public class AccountingCodeApi {

    private final AccountingCodeService accountingCodeService;

    @PostMapping
    public AccountingCodeDto create(@RequestBody @Valid AccountingCodeDto accountingCodeDto) {
        return accountingCodeService.create(accountingCodeDto);
    }

    @PutMapping("{id}")
    public AccountingCodeDto update(@RequestBody @Valid AccountingCodeDto accountingCodeDto) {
        return accountingCodeService.update(accountingCodeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        accountingCodeService.delete(new ObjectId(id));
        return new ResponseEntity<>("Code comptable supprimée !", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public AccountingCodeDto get(@PathVariable String id) {
        return accountingCodeService.getAccountingCode(new ObjectId(id));
    }

    @GetMapping()
    public ListResponse<AccountingCodeDto> findAll() {
        return new ListResponse<>(accountingCodeService.findAll());
    }

}
