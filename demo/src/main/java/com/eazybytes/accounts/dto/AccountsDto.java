package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account Number can not be empty")
    private Long accountNumber;
    @NotEmpty(message = "Branch Address can not be empty")
    private String branchAddress;
    @NotEmpty(message = "Account Type can not be empty")
    private String accountType;
}
