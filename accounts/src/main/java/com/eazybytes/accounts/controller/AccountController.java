package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.config.ContactInfoConfig;
import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD Rest APIs",
        description = "CRUD REST APIs for create, update, fetch and delete"
)
public class AccountController {

    private final IAccountService accountService;

    private final ContactInfoConfig infoConfig;

    @GetMapping(path = "/hello-world")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello Worlds",HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    @Operation(summary = "Create Account rest api")
    public ResponseEntity<com.eazybytes.accounts.dto.ResponseDto> createAccount(@Valid @RequestBody com.eazybytes.accounts.dto.CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDTO> fetchAccount(@RequestParam(name = "mobileNumber") String mobileNumber) {
        var customer = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        var isUpdated = accountService.updateAccount(customerDTO);
        if (isUpdated) {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.SAVINGS), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        var isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/contact-info")
    public ResponseEntity<ContactInfoConfig> getContactInfo() {
        return new ResponseEntity<>(infoConfig,HttpStatus.OK);
    }

}
